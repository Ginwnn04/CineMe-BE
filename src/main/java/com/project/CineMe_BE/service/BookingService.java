package com.project.CineMe_BE.service;

import com.project.CineMe_BE.dto.request.BookingRequest;
import jakarta.servlet.http.HttpServletRequest;

public interface BookingService {

    String createBooking(BookingRequest bookingRequest, HttpServletRequest request);


}
