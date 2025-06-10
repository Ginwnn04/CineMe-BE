package com.project.CineMe_BE.mapper.response;

import com.project.CineMe_BE.dto.response.MovieResponse;
import com.project.CineMe_BE.entity.MovieEntity;
import com.project.CineMe_BE.mapper.BaseMapperResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface MovieResponseMapper extends BaseMapperResponse<MovieResponse, MovieEntity> {

    @Mapping(target = "limitageNameVn", source = "limitage.nameVn")
    @Mapping(target = "limitageNameEn", source = "limitage.nameEn")
    @Mapping(target = "languageNameVn", source = "language.nameVn")
    @Mapping(target = "languageNameEn", source = "language.nameEn")
    MovieResponse toDto(MovieEntity entity);
}
