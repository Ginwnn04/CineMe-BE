package com.project.CineMe_BE.dto.response;

import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PaymentResponse {
    private String movieName;
    private String theaterName;
    private String duration;
    private String roomName;
    private String showtime;
    private List<String> seatNumbers;
    private String qrcode;
}
