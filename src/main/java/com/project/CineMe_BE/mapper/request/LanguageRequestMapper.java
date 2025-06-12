package com.project.CineMe_BE.mapper.request;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.project.CineMe_BE.dto.request.LanguageRequest;
import com.project.CineMe_BE.entity.LanguageEntity;
import com.project.CineMe_BE.mapper.BaseRequestMapper;

@Mapper(componentModel = "spring")
public interface LanguageRequestMapper extends BaseRequestMapper<LanguageRequest,LanguageEntity>{
    @Override
    @Mapping(target = "id", ignore = true)
    LanguageEntity toEntity(LanguageRequest request);
}
