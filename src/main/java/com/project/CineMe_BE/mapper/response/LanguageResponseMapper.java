package com.project.CineMe_BE.mapper.response;

import org.mapstruct.Mapper;

import com.project.CineMe_BE.dto.response.LanguageResponse;
import com.project.CineMe_BE.entity.LanguageEntity;
import com.project.CineMe_BE.mapper.BaseResponseMapper;

@Mapper(componentModel = "spring")
public interface LanguageResponseMapper extends BaseResponseMapper<LanguageResponse, LanguageEntity> {

}
