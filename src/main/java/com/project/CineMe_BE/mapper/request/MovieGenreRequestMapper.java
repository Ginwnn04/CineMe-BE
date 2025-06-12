package com.project.CineMe_BE.mapper.request;

import com.project.CineMe_BE.dto.request.MovieGenreRequest;
import com.project.CineMe_BE.entity.Movie_GenreEntity;
import com.project.CineMe_BE.entity.id.MovieGenreId;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(componentModel = "spring")
public interface MovieGenreRequestMapper {
    @Mapping(target = "id", source = ".", qualifiedByName = "toMovieGenreId")
    Movie_GenreEntity toEntity(MovieGenreRequest request);

    @Named("toMovieGenreId")
    default MovieGenreId toMovieGenreId(MovieGenreRequest request) {
        MovieGenreId id = new MovieGenreId();
        id.setMovie(request.getMovieId());
        id.setGenre(request.getGenreId());
        return id;
    }
}