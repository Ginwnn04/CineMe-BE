package com.project.CineMe_BE.dto.response;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SeatResponse {
    private int roomdId;
    private String seatNumber;
    private String seatType;
    private String status;
}
