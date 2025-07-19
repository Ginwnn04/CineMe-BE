package com.project.CineMe_BE.dto.request;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
public class MovieRequest {
    private String nameVn;
    private String nameEn;
    private String director;
    private UUID countryId;
    private LocalDateTime releaseDate;
    private LocalDateTime endDate;
    private String briefVn;
    private String briefEn;
    private MultipartFile image;
    private MultipartFile trailer;
    private Long time;
    private UUID limitageId;
}
