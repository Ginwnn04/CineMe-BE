package com.project.CineMe_BE.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.UUID;

@Getter
@Setter
public class ShowtimeRequest {
    private UUID movieId;
    private UUID theaterId;
    private UUID roomId;
    private LocalDate date;
    private LocalTime startTime;
    private LocalTime endTime;


}
