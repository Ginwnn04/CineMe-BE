package com.project.CineMe_BE.mapper.response;

import com.project.CineMe_BE.dto.response.MovieResponse;
import com.project.CineMe_BE.entity.MovieEntity;
import com.project.CineMe_BE.mapper.BaseResponseMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {ActorResponseMapper.class})
public interface MovieResponseMapper extends BaseResponseMapper<MovieResponse, MovieEntity> {

    @Mapping(target = "limitageNameVn", source = "limitage.nameVn")
    @Mapping(target = "limitageNameEn", source = "limitage.nameEn")
    @Mapping(target = "languageNameVn", source = "language.nameVn")
    @Mapping(target = "languageNameEn", source = "language.nameEn")
    MovieResponse toDto(MovieEntity entity);
}
