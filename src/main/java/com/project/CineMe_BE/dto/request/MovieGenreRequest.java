package com.project.CineMe_BE.dto.request;

import java.util.UUID;
import lombok.Data;

@Data
public class MovieGenreRequest {
    private UUID movieId;
    private UUID genreId;
}