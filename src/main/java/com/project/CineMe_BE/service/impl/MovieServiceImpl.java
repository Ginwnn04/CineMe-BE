package com.project.CineMe_BE.service.impl;

import com.project.CineMe_BE.constant.MessageKey;
import com.project.CineMe_BE.dto.request.MovieRequest;
import com.project.CineMe_BE.dto.response.MovieResponse;
import com.project.CineMe_BE.entity.MovieEntity;
import com.project.CineMe_BE.exception.DataNotFoundException;
import com.project.CineMe_BE.mapper.request.MovieRequestMapper;
import com.project.CineMe_BE.mapper.response.MovieResponseMapper;
import com.project.CineMe_BE.repository.MovieRepository;
import com.project.CineMe_BE.service.MinioService;
import com.project.CineMe_BE.service.MovieService;
import com.project.CineMe_BE.utils.LocalizationUtils;
import com.project.CineMe_BE.utils.StringUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class MovieServiceImpl implements MovieService {
    private final LocalizationUtils localizationUtils;
    private final MovieRepository movieRepository;
    private final MovieResponseMapper movieResponseMapper;
    private final MovieRequestMapper movieRequestMapper;
    private final MinioService minioService;


    @Override
    public List<MovieResponse> getAvailableMovies() {
        List<MovieEntity> listMovie = movieRepository.getAvailableMovies();
        return movieResponseMapper.toListDto(listMovie);
    }

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
    @Transactional
    public MovieResponse updateMovie(UUID id, MovieRequest request) {
        MovieEntity movie = movieRepository.findById(id)
                .orElseThrow(() -> new DataNotFoundException(localizationUtils.getLocalizedMessage(MessageKey.MOVIE_NOT_FOUND)));
        movieRequestMapper.update(movie, request);
        if (request.getImage() != null) {
            String imgUrl = minioService.upload(request.getImage());
            movie.setImage(StringUtil.splitUrlResource(imgUrl));
        }
        if (request.getTrailer() != null) {
            String trailerUrl = minioService.upload(request.getTrailer());
            movie.setTrailer(StringUtil.splitUrlResource(trailerUrl));
        }
        MovieEntity updatedMovie = movieRepository.save(movie);
        return movieResponseMapper.toDto(updatedMovie);
    }

    @Override
    public List<MovieResponse> getAllMovie() {
        List<MovieEntity> listMovie = movieRepository.findAll().stream()
                .map(movie -> {
                    movie.setLanguage(null);
                    movie.setLimitage(null);
                    movie.setCountry(null);
                    movie.setListActor(null);
                    return movie;
                })
                .toList();
        return movieResponseMapper.toListDto(listMovie);
    }

    @Override
    public MovieResponse getMovieDetail(UUID id) {
        MovieEntity movie = movieRepository.findById(id)
                .orElseThrow(() -> new DataNotFoundException(localizationUtils.getLocalizedMessage(MessageKey.MOVIE_NOT_FOUND)));
        return movieResponseMapper.toDto(movie);
    }

    @Override
    public void deleteMovie(UUID id) {
        MovieEntity movie = movieRepository.findById(id)
                .orElseThrow(() -> new DataNotFoundException(localizationUtils.getLocalizedMessage(MessageKey.MOVIE_NOT_FOUND)));
        movieRepository.delete(movie);

    }
}
