package com.project.CineMe_BE.mapper.response;

import org.mapstruct.Mapper;
import com.project.CineMe_BE.dto.response.GenreResponse;
import com.project.CineMe_BE.entity.GenreEntity;
import com.project.CineMe_BE.mapper.BaseMapperResponse;

@Mapper(componentModel = "spring")
public interface GenreResponseMapper extends BaseMapperResponse<GenreResponse, GenreEntity> {

}