package com.project.CineMe_BE.controller;

import com.project.CineMe_BE.constant.MessageKey;
import com.project.CineMe_BE.dto.APIResponse;
import com.project.CineMe_BE.dto.request.BookingRequest;
import com.project.CineMe_BE.service.BookingService;
import com.project.CineMe_BE.utils.LocalizationUtils;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/bookings")
public class BookingController {
    private final BookingService bookingService;
    private final LocalizationUtils localizationUtils;
    @PostMapping("")
    public ResponseEntity<APIResponse> createBooking(@RequestBody BookingRequest bookingRequest, HttpServletRequest request) {
        String paymentUrl = bookingService.createBooking(bookingRequest, request);
        APIResponse apiResponse = new APIResponse();
        int statusCode = 200;
        if (paymentUrl == null ) {
            statusCode = 400;
            apiResponse.setMessage(localizationUtils.getLocalizedMessage(MessageKey.PAYMENT_CREATE_URL_FAILED));
        }
        else {
            apiResponse.setMessage(localizationUtils.getLocalizedMessage(MessageKey.PAYMENT_CREATE_URL_SUCCESS));
            apiResponse.setData(paymentUrl);
        }
        apiResponse.setStatusCode(statusCode);
        return ResponseEntity.status(statusCode).body(apiResponse);
    }

    @GetMapping("/VnPayReturn")
    public ResponseEntity<APIResponse> vnPayReturn(HttpServletRequest request) {
        return null;
//        return ResponseEntity.status(apiResponse.getStatusCode()).body(apiResponse);
    }
}
