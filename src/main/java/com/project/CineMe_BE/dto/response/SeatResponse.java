package com.project.CineMe_BE.dto.response;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.util.UUID;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SeatResponse {
    private UUID id;
    private String seatNumber;
    private String seatType;
    private String status;
}
