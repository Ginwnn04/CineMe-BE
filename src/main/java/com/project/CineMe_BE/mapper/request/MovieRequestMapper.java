package com.project.CineMe_BE.mapper.request;

import com.project.CineMe_BE.dto.request.MovieRequest;
import com.project.CineMe_BE.entity.LanguageEntity;
import com.project.CineMe_BE.entity.MovieEntity;
import com.project.CineMe_BE.mapper.BaseRequestMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.UUID;

@Mapper(componentModel = "spring")
public interface MovieRequestMapper extends BaseRequestMapper<MovieRequest, MovieEntity> {
    @Mapping(target = "trailer", ignore = true)
    @Mapping(target = "image", ignore = true)
    MovieEntity toEntity(MovieRequest dto);


    @Mapping(target = "trailer", ignore = true)
    @Mapping(target = "image", ignore = true)
    void update(@MappingTarget MovieEntity entity, MovieRequest dto);
}