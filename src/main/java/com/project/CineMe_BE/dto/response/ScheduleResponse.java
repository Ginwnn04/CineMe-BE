package com.project.CineMe_BE.dto.response;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Setter
@Getter
public class ScheduleResponse {
    private UUID movieId;
    private String movieNameVn;
    private String movieNameEn;
    private String img;
    private String limitAgeVn;
    private String limitAgeEn;
    private String genreVn;
    private String genreEn;
    private List<ShowtimeResponse> showtimes;
}
