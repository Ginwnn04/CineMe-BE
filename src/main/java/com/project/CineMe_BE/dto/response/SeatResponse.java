package com.project.CineMe_BE.dto.response;
import lombok.*;

import java.util.UUID;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SeatResponse {
    private UUID id;
    private String seatNumber;
    private String seatType;
    // private String status;
}
