package com.project.CineMe_BE.service;

import com.project.CineMe_BE.dto.request.GenreRequest;
import com.project.CineMe_BE.dto.response.GenreResponse;
import java.util.UUID;

public interface GenreService {
    GenreResponse create(GenreRequest genreRequest);
    GenreResponse update(GenreRequest genreRequest, UUID id);
    void delete(UUID id);
}