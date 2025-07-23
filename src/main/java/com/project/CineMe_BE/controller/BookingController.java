package com.project.CineMe_BE.controller;

import com.google.zxing.WriterException;
import com.project.CineMe_BE.constant.MessageKey;
import com.project.CineMe_BE.dto.APIResponse;
import com.project.CineMe_BE.dto.request.BookingRequest;
import com.project.CineMe_BE.dto.response.PaymentResponse;
import com.project.CineMe_BE.service.BookingService;
import com.project.CineMe_BE.service.MinioService;
import com.project.CineMe_BE.utils.AESUtil;
import com.project.CineMe_BE.utils.LocalizationUtils;
import com.project.CineMe_BE.utils.QrCodeUtil;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.bouncycastle.jcajce.provider.symmetric.AES;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Slf4j
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
        PaymentResponse paymentResponse = bookingService.confirmBooking(request);
        return ResponseEntity.ok(
                APIResponse.builder()
                        .data(paymentResponse != null ? paymentResponse : "Payment confirmation failed")
                        .statusCode(paymentResponse != null ? 200 : 400)
                        .build()
        );}

//    @GetMapping("/encode")
//    public String getQRCode(@RequestParam String booking,
//                             @RequestParam String user) throws Exception {
//        String name = booking + "_" + user;
//        String encrypted = AESUtil.encrypt(name);
////        MultipartFile file = QrCodeUtil.createQR(name, name);
////        minioService.upload(file);
//        return encrypted;
//    }
//    @GetMapping("/decode")
//    public String decode(@RequestParam String code) throws Exception {
//        String decoded = AESUtil.decrypt(code);
////        MultipartFile file = QrCodeUtil.createQR(name, name);
////        minioService.upload(file);
//        return decoded;
//    }
}
