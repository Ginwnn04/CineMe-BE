package com.project.CineMe_BE.service.impl;

import com.project.CineMe_BE.dto.request.MovieGenreRequest;
import com.project.CineMe_BE.dto.response.MovieGenreResponse;
import com.project.CineMe_BE.entity.GenreEntity;
import com.project.CineMe_BE.entity.MovieEntity;
import com.project.CineMe_BE.entity.Movie_GenreEntity;
import com.project.CineMe_BE.entity.id.MovieGenreId;
import com.project.CineMe_BE.exception.DataNotFoundException;
import com.project.CineMe_BE.mapper.response.MovieGenreResponseMapper;
import com.project.CineMe_BE.repository.GenreRepository;
import com.project.CineMe_BE.repository.MovieGenreRepository;
import com.project.CineMe_BE.repository.MovieRepository;
import com.project.CineMe_BE.service.MovieGenreService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class MovieGenreServiceImpl implements MovieGenreService {

    private final MovieGenreRepository movieGenreRepository;
    private final MovieRepository movieRepository;
    private final GenreRepository genreRepository;
    private final MovieGenreResponseMapper responseMapper;

    @Override
    @Transactional
    public MovieGenreResponse create(MovieGenreRequest request) {
        MovieEntity movie = movieRepository.findById(request.getMovieId())
                .orElseThrow(() -> new DataNotFoundException("Movie not found"));
        GenreEntity genre = genreRepository.findById(request.getGenreId())
                .orElseThrow(() -> new DataNotFoundException("Genre not found"));

          // Tạo id tổng hợp
        MovieGenreId id = new MovieGenreId();
        id.setMovie(movie.getId());
        id.setGenre(genre.getId());

        Movie_GenreEntity entity = new Movie_GenreEntity();
        entity.setMovie(movie);
        entity.setGenre(genre);
        entity.setId(id);

        movieGenreRepository.save(entity);
        return responseMapper.toDto(entity);
    }

        @Override
        @Transactional
        public void deleteByMovieAndGenre(UUID movieId, UUID genreId) {
        MovieGenreId id = new MovieGenreId();
        id.setMovie(movieId);
        id.setGenre(genreId);
        Movie_GenreEntity entity = movieGenreRepository.findById(id)
                .orElseThrow(() -> new DataNotFoundException("Movie-Genre relation not found"));
        movieGenreRepository.delete(entity);
        }
}