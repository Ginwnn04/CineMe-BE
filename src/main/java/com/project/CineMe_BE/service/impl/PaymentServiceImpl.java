package com.project.CineMe_BE.service.impl;

import com.project.CineMe_BE.config.VNPAYConfig;
import com.project.CineMe_BE.dto.request.BookingRequest;
import com.project.CineMe_BE.service.PaymentService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {
    private final VNPAYConfig vnPayConfig;
    @Override
    public String createPayment(BookingRequest bookingRequest, HttpServletRequest request) {
        long amount = (bookingRequest.getAmount()) * 100;
        String userId = bookingRequest.getUserId().toString();
        String showtimeId = bookingRequest.getShowtimeId().toString();
        String vnpayRef = vnPayConfig.getRandomNumber(8);
//        String bankCode = "";
        Map<String, String> vnpParamsMap = vnPayConfig.getVNPayConfig();

        vnpParamsMap.put("vnp_Amount", String.valueOf(amount));
        vnpParamsMap.put("vnp_IpAddr", vnPayConfig.getIpAddress(request));
        //
        vnpParamsMap.put("vnp_OrderInfo", showtimeId + "_" + userId);
        vnpParamsMap.put("vnp_TxnRef", vnpayRef);
        //
        String queryUrl = vnPayConfig.getPaymentURL(vnpParamsMap, true);
        String hashData = vnPayConfig.getPaymentURL(vnpParamsMap, false);
        String vnpSecureHash = vnPayConfig.hmacSHA512(vnPayConfig.getSecretKey(), hashData);
        queryUrl += "&vnp_SecureHash=" + vnpSecureHash;
        String paymentUrl = vnPayConfig.getVnp_PayUrl() + "?" + queryUrl;
        return paymentUrl;

    }
}
