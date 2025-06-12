package com.project.CineMe_BE.service;

import com.project.CineMe_BE.dto.request.MovieRequest;
import com.project.CineMe_BE.dto.response.MovieResponse;

import java.util.List;
import java.util.UUID;

public interface MovieService {

    public MovieResponse createMovie(MovieRequest request);
    public List<MovieResponse> getAllMovie();
    public MovieResponse getMovieDetail(UUID id);

}
