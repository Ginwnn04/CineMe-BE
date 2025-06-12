package com.project.CineMe_BE.service.impl;

import com.project.CineMe_BE.dto.request.MovieRequest;
import com.project.CineMe_BE.dto.response.MovieResponse;
import com.project.CineMe_BE.entity.MovieEntity;
import com.project.CineMe_BE.exception.DataNotFoundException;
import com.project.CineMe_BE.mapper.request.MovieRequestMapper;
import com.project.CineMe_BE.mapper.response.MovieResponseMapper;
import com.project.CineMe_BE.repository.MovieRepository;
import com.project.CineMe_BE.service.MinioService;
import com.project.CineMe_BE.service.MovieService;
import com.project.CineMe_BE.utils.StringUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class MovieServiceImpl implements MovieService {
    private final MovieRepository movieRepository;
    private final MovieResponseMapper movieResponseMapper;
    private final MovieRequestMapper movieRequestMapper;
    private final MinioService minioService;

    @Override
    public MovieResponse createMovie(MovieRequest request) {
        MovieEntity movie = movieRequestMapper.toEntity(request);
        if (request.getImage() != null) {
            String imgUrl = minioService.upload(request.getImage());
            movie.setImage(StringUtil.splitUrlResource(imgUrl));
        }
        if (request.getTrailer() != null) {
            String trailerUrl = minioService.upload(request.getTrailer());
            movie.setTrailer(StringUtil.splitUrlResource(trailerUrl));
        }
        MovieEntity savedMovie = movieRepository.save(movie);
        return movieResponseMapper.toDto(savedMovie);
    }

    @Override
    public List<MovieResponse> getAllMovie() {
        List<MovieEntity> listMovie = movieRepository.findAll().stream()
                .map(movie -> {
                    movie.setLanguage(null);
                    movie.setLimitage(null);
                    movie.setCountryId(null);
                    movie.setListActor(null);
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
