package com.project.CineMe_BE.mapper.response;

import com.project.CineMe_BE.dto.response.MovieGenreResponse;
import com.project.CineMe_BE.entity.Movie_GenreEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface MovieGenreResponseMapper {
    @Mapping(target = "movieId", source = "id.movie")
    @Mapping(target = "genreId", source = "id.genre")
    @Mapping(target = "movieName", source = "movie.nameVn")
    @Mapping(target = "genreName", source = "genre.nameVn")
    MovieGenreResponse toDto(Movie_GenreEntity entity);
}