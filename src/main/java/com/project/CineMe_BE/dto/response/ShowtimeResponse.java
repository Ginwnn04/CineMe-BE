package com.project.CineMe_BE.dto.response;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalTime;
import java.util.UUID;

@Getter
@Setter
public class ShowtimeResponse {
    private UUID id;
    private String movieNameVn;
    private String movieNameEn;
    private LocalTime startTime;
    private LocalTime endTime;
    private String room;

}
