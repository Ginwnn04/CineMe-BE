package com.project.CineMe_BE.dto.response;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
public class MovieResponse {
    private UUID id;
    private String nameVn;
    private String nameEn;
    private String director;
    private UUID countryId;
    private UUID formatId;
    private LocalDateTime releaseDate;
    private LocalDateTime endDate;
    private String briefVn;
    private String briefEn;
    private String image;
    private String himage;
    private String trailer;
    private String status;
    private String ratings;
    private Long time;
    private UUID limitageId;
    private UUID languageId;
    private Long sortorder;


}
