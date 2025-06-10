package com.project.CineMe_BE.service.impl;

import com.project.CineMe_BE.dto.response.MovieResponse;
import com.project.CineMe_BE.entity.MovieEntity;
import com.project.CineMe_BE.mapper.response.MovieResponseMapper;
import com.project.CineMe_BE.repository.MovieRepository;
import com.project.CineMe_BE.service.MovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MovieServiceImpl implements MovieService {
    private final MovieRepository movieRepository;
    private final MovieResponseMapper movieResponseMapper;
    @Override
    public List<MovieResponse> getAllMovie() {
        List<MovieEntity> listMovie = movieRepository.findAll();
        return movieResponseMapper.toListDto(listMovie);

    }
}
