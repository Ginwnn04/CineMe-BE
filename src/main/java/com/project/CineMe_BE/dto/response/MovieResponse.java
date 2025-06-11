package com.project.CineMe_BE.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
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
    private String limitageNameVn;
    private String limitageNameEn;
    private String languageNameVn;
    private String languageNameEn;
    private Long sortorder;
    private List<ActorResponse> listActor;

}
