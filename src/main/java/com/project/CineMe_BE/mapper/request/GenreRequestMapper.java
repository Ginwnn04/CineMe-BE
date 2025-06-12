package com.project.CineMe_BE.mapper.request;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.project.CineMe_BE.dto.request.GenreRequest;
import com.project.CineMe_BE.entity.GenreEntity;
import com.project.CineMe_BE.mapper.BaseRequestMapper;

@Mapper(componentModel = "spring")
public interface GenreRequestMapper extends BaseRequestMapper<GenreRequest,GenreEntity>{
    @Override
    @Mapping(target = "id", ignore = true)
    GenreEntity toEntity(GenreRequest request);
}
