package com.project.CineMe_BE.service;

import com.project.CineMe_BE.dto.request.MovieGenreRequest;
import com.project.CineMe_BE.dto.response.MovieGenreResponse;

public interface MovieGenreService {
    MovieGenreResponse create(MovieGenreRequest request);
    void deleteByMovieAndGenre(java.util.UUID movieId, java.util.UUID genreId);
}