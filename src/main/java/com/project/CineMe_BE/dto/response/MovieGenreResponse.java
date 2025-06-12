package com.project.CineMe_BE.dto.response;

import java.util.UUID;
import lombok.Data;

@Data
public class MovieGenreResponse {
    private UUID movieId;
    private UUID genreId;
    private String movieName;
    private String genreName;
}