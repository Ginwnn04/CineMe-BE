package com.project.CineMe_BE.service.impl;

import com.google.zxing.WriterException;
import com.project.CineMe_BE.repository.projection.PaymentProjection;
import com.project.CineMe_BE.constant.MessageKey;
import com.project.CineMe_BE.dto.request.BookingRequest;
import com.project.CineMe_BE.dto.response.PaymentResponse;
import com.project.CineMe_BE.entity.*;
import com.project.CineMe_BE.enums.BookingStatusEnum;
import com.project.CineMe_BE.exception.DataNotFoundException;
import com.project.CineMe_BE.listener.SeatSocketBroadcaster;
import com.project.CineMe_BE.repository.BookingRepository;
import com.project.CineMe_BE.repository.SeatsRepository;
import com.project.CineMe_BE.repository.ShowtimeRepository;
import com.project.CineMe_BE.repository.UserRepository;
import com.project.CineMe_BE.service.BookingService;
import com.project.CineMe_BE.service.MinioService;
import com.project.CineMe_BE.service.PaymentService;
import com.project.CineMe_BE.service.SeatService;
import com.project.CineMe_BE.utils.LocalizationUtils;
import com.project.CineMe_BE.utils.QrCodeUtil;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class BookingServiceImpl implements BookingService {
    private final LocalizationUtils localizationUtils;
    private final UserRepository userRepository;
    private final ShowtimeRepository showtimeRepository;
    private final SeatsRepository seatsRepository;
    private final SeatSocketBroadcaster seatSocketBroadcaster;
    private final PaymentService paymentService;
    private final BookingRepository bookingRepository;
    private final SeatService seatService;
    private final MinioService minioService;
    @Override
    public String createBooking(BookingRequest bookingRequest, HttpServletRequest request) {
        UserEntity user = userRepository.findById(bookingRequest.getUserId())
                .orElseThrow(() -> new DataNotFoundException(localizationUtils.getLocalizedMessage(MessageKey.USER_NOT_FOUND)));
        ShowtimeEntity showtime = showtimeRepository.findById(bookingRequest.getShowtimeId())
                .orElseThrow(() -> new DataNotFoundException(localizationUtils.getLocalizedMessage(MessageKey.SHOWTIME_NOT_FOUND)));
        Map<UUID, String> listSeats = seatsRepository.findByRoomId(showtime.getRoom().getId())
                .stream()
                .collect(Collectors.toMap(SeatsEntity::getId, SeatsEntity::getSeatNumber));

        List<UUID> selectedSeats = bookingRequest.getListSeatId();

        // Check ghế có tồn tại trong phòng (Khớp ID vaf seatNumber)
        for (UUID seatId : selectedSeats) {
            if (!listSeats.containsKey(seatId)) {
                throw new DataNotFoundException(localizationUtils.getLocalizedMessage(MessageKey.SEAT_NOT_FOUND, seatId));
            }
        }

        // Lock seats
        boolean isLocked = seatSocketBroadcaster.lockSeatAndBroadcast(
                showtime.getId(),
                selectedSeats,
                user.getId()
        );



        return isLocked ? paymentService.createPayment(bookingRequest, request) : null;
    }


    @Override
    public PaymentResponse confirmBooking(HttpServletRequest request) {
        String status = request.getParameter("vnp_ResponseCode");
        if(!status.equals("00")) {
            return null;
        }
        String orderInfo = request.getParameter("vnp_OrderInfo");
        String[] orderParts = orderInfo.split("_");
        UUID showtimeId = UUID.fromString(orderParts[0]);
        UUID userId = UUID.fromString(orderParts[1]);
        long amount = Long.parseLong(request.getParameter("vnp_Amount")) / 100L;
        String transactionId = request.getParameter("vnp_TransactionNo");
        log.info("VnPayReturn: showtimeId: {}, userId: {}, amount: {}, transactionId: {}",
                showtimeId, userId, amount, transactionId);
        // Get list  seatId in Redis
        List<UUID> listSeatId = seatService.getListSeatRedis(showtimeId, userId);

        // Remove in redis
        boolean isDeleted = seatService.deleteBookingLockRedis(showtimeId, userId);
        if (!isDeleted) {
            log.error("Failed to delete booking lock for userId: {} and showtimeId: {}", userId, showtimeId);
            return null;
        }

        BookingEntity booking = createBooking(userId, showtimeId, amount, listSeatId);
        bookingRepository.save(booking);

        return createPaymentResponse(booking);
    }

    private PaymentResponse createPaymentResponse(BookingEntity booking) {
        List<PaymentProjection> paymentInfo = bookingRepository.getPaymentInfoByBookingId(booking.getId());

        PaymentProjection info = paymentInfo.get(0);
        PaymentResponse response = PaymentResponse.builder()
                .movieName(info.getMovieName())
                .theaterName(info.getTheaterName())
                .duration(info.getDuration())
                .roomName(info.getRoomName())
                .showtime(info.getShowtime())
                .seatNumbers(paymentInfo.stream()
                        .map(PaymentProjection::getSeatNumber)
                        .collect(Collectors.toList()))
                .build();

        String privateKey = showtimeRepository.getPriveKey(booking.getShowtime().getId());
        if (privateKey != null) {
            MultipartFile file = null;
            try {
                file = QrCodeUtil.createQR(
                        privateKey + "_" + booking.getId(),
                        "booking_" + booking.getId()
                );
            } catch (WriterException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            String qrCodeUrl = minioService.upload(file);
            response.setQrcode(qrCodeUrl);
        }
        return response;
    }

    private BookingEntity createBooking(UUID userId, UUID showtimeId, long totalPrice, List<UUID> seatIds) {
        BookingEntity booking = BookingEntity.builder()
                .user(UserEntity.builder().id(userId).build())
                .showtime(ShowtimeEntity.builder().id(showtimeId).build())
                .totalPrice(totalPrice)
                .createdAt(new Date())
                .updatedAt(new Date())
                .status(BookingStatusEnum.CONFIRMED.name())
                .build();

        List<BokingSeatEntity> bookingSeats = seatIds.stream()
                .map(seatId -> BokingSeatEntity.builder()
                        .seat(SeatsEntity.builder().id(seatId).build())
                        .booking(booking)
                        .build())
                .collect(Collectors.toList());

        booking.setBookingSeats(bookingSeats);
        return booking;
    }

//    private String generateQrCode()

}
