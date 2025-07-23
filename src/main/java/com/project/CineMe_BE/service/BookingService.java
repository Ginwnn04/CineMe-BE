package com.project.CineMe_BE.service;

import com.project.CineMe_BE.dto.request.BookingRequest;
import com.project.CineMe_BE.dto.response.PaymentResponse;
import jakarta.servlet.http.HttpServletRequest;

public interface BookingService {

    String createBooking(BookingRequest bookingRequest, HttpServletRequest request);

    PaymentResponse confirmBooking(HttpServletRequest request);



}
