package com.project.CineMe_BE.service.impl;

import com.project.CineMe_BE.constant.MessageKey;
import com.project.CineMe_BE.dto.request.BookingRequest;
import com.project.CineMe_BE.entity.SeatsEntity;
import com.project.CineMe_BE.entity.ShowtimeEntity;
import com.project.CineMe_BE.entity.UserEntity;
import com.project.CineMe_BE.exception.DataNotFoundException;
import com.project.CineMe_BE.listener.SeatSocketBroadcaster;
import com.project.CineMe_BE.repository.SeatsRepository;
import com.project.CineMe_BE.repository.ShowtimeRepository;
import com.project.CineMe_BE.repository.UserRepository;
import com.project.CineMe_BE.service.BookingService;
import com.project.CineMe_BE.service.PaymentService;
import com.project.CineMe_BE.utils.LocalizationUtils;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BookingServiceImpl implements BookingService {
    private final LocalizationUtils localizationUtils;
    private final UserRepository userRepository;
    private final ShowtimeRepository showtimeRepository;
    private final SeatsRepository seatsRepository;
    private final SeatSocketBroadcaster seatSocketBroadcaster;
    private final PaymentService paymentService;
    @Override
    public String createBooking(BookingRequest bookingRequest, HttpServletRequest request) {
        UserEntity user = userRepository.findById(bookingRequest.getUserId())
                .orElseThrow(() -> new DataNotFoundException(localizationUtils.getLocalizedMessage(MessageKey.USER_NOT_FOUND)));
        ShowtimeEntity showtime = showtimeRepository.findById(bookingRequest.getShowtimeId())
                .orElseThrow(() -> new DataNotFoundException(localizationUtils.getLocalizedMessage(MessageKey.SHOWTIME_NOT_FOUND)));
        Map<UUID, String> listSeats = seatsRepository.findByRoomId(showtime.getRoom().getId())
                .stream()
                .collect(Collectors.toMap(SeatsEntity::getId, SeatsEntity::getSeatNumber));
        bookingRequest.getListSeats().forEach((seatId, seatNumber) -> {
            if (!listSeats.containsKey(seatId) || !listSeats.get(seatId).equals(seatNumber)) {
                throw new DataNotFoundException(localizationUtils.getLocalizedMessage(MessageKey.SEAT_NOT_FOUND, seatNumber));
            }
        });


        Set<String> listSeatNumber = bookingRequest.getListSeats().values()
                .stream()
                .collect(Collectors.toSet());

        // Lock seats
        boolean isLocked = seatSocketBroadcaster.lockSeatAndBroadcast(
                showtime.getId(),
                listSeatNumber,
                user.getId()
        );
        return isLocked ? paymentService.createPayment(bookingRequest, request) : null;
    }
}
