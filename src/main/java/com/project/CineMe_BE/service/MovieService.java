package com.project.CineMe_BE.service;

import com.project.CineMe_BE.dto.response.MovieResponse;
import com.project.CineMe_BE.entity.MovieEntity;

import java.util.List;

public interface MovieService {

    public List<MovieResponse> getAllMovie();


}
