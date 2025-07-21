package com.project.CineMe_BE.service.impl;

import com.project.CineMe_BE.constant.MessageKey;
import com.project.CineMe_BE.dto.request.BookingRequest;
import com.project.CineMe_BE.entity.*;
import com.project.CineMe_BE.enums.BookingStatusEnum;
import com.project.CineMe_BE.exception.DataNotFoundException;
import com.project.CineMe_BE.listener.SeatSocketBroadcaster;
import com.project.CineMe_BE.repository.BookingRepository;
import com.project.CineMe_BE.repository.SeatsRepository;
import com.project.CineMe_BE.repository.ShowtimeRepository;
import com.project.CineMe_BE.repository.UserRepository;
import com.project.CineMe_BE.service.BookingService;
import com.project.CineMe_BE.service.PaymentService;
import com.project.CineMe_BE.service.SeatService;
import com.project.CineMe_BE.utils.LocalizationUtils;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

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
//        return "123";
    }


    @Override
    public boolean confirmBooking(HttpServletRequest request) {
        String status = request.getParameter("vnp_ResponseCode");
        if(!status.equals("00")) {
            return false;
        }
        String showtime = request.getParameter("vnp_OrderInfo").split("_")[0];
        String userId = request.getParameter("vnp_OrderInfo").split("_")[1];
        String amount = request.getParameter("vnp_Amount");
        String transactionId = request.getParameter("vnp_TransactionNo");
        log.info("VnPayReturn: showtimeId: {}, userId: {}, amount: {}, transactionId: {}",
                showtime, userId, amount, transactionId);
        // Get list  seatId in Redis
        List<UUID> listSeatId = seatService.getListSeatRedis(UUID.fromString(showtime), UUID.fromString(userId));

        // Remove in redis
        boolean isDeleted = seatService.deleteBookingLockRedis(UUID.fromString(showtime), UUID.fromString(userId));
        if (!isDeleted) {
            log.error("Failed to delete booking lock for userId: {} and showtimeId: {}", userId, showtime);
            return false;
        }

        BookingEntity booking = BookingEntity.builder()
        .user(UserEntity.builder()
                .id(UUID.fromString(userId))
                .build())
        .showtime(ShowtimeEntity.builder()
                .id(UUID.fromString(showtime))
                .build())
        .totalPrice(Long.parseLong(amount) / 100L)
        .createdAt(new Date())
        .updatedAt(new Date())
        .status(BookingStatusEnum.CONFIRMED.name())
        .build();

        List<BokingSeatEntity> bookingSeats = listSeatId.stream()
                .map(seatId -> {
                    return BokingSeatEntity.builder()
                            .seat(SeatsEntity.builder()
                                    .id(seatId)
                                    .build())
                            .booking(booking)
                            .build();
                })
                .collect(Collectors.toList());

        booking.setBookingSeats(bookingSeats);
        bookingRepository.save(booking);
        return true;
    }


}
