package com.project.CineMe_BE.service.impl;

import com.project.CineMe_BE.dto.response.MovieResponse;
import com.project.CineMe_BE.entity.MovieEntity;
import com.project.CineMe_BE.exception.DataNotFoundException;
import com.project.CineMe_BE.mapper.response.MovieResponseMapper;
import com.project.CineMe_BE.repository.MovieRepository;
import com.project.CineMe_BE.service.MovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class MovieServiceImpl implements MovieService {
    private final MovieRepository movieRepository;
    private final MovieResponseMapper movieResponseMapper;
    @Override
    public List<MovieResponse> getAllMovie() {
        List<MovieEntity> listMovie = movieRepository.findAll().stream()
                .map(movie -> {
                    movie.setLanguage(null);
                    movie.setLimitage(null);
                    movie.setCountryId(null);
                    return movie;
                })
                .toList();
        return movieResponseMapper.toListDto(listMovie);
    }

    @Override
    public MovieResponse getMovieDetail(UUID id) {
        MovieEntity movie = movieRepository.findById(id)
                .orElseThrow(() -> new DataNotFoundException("Movie not found with id: " + id));
        return movieResponseMapper.toDto(movie);
    }
}
