package com.project.CineMe_BE.mapper.response;

import com.project.CineMe_BE.dto.response.MovieResponse;
import com.project.CineMe_BE.entity.MovieEntity;
import com.project.CineMe_BE.mapper.BaseMapperResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MovieResponseMapper extends BaseMapperResponse<MovieResponse, MovieEntity> {
}
