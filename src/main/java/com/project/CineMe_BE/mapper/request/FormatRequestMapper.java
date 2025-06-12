package com.project.CineMe_BE.mapper.request;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.project.CineMe_BE.dto.request.FormatRequest;
import com.project.CineMe_BE.entity.FormatEntity;
import com.project.CineMe_BE.mapper.BaseRequestMapper;

@Mapper(componentModel = "spring")
public interface FormatRequestMapper extends BaseRequestMapper<FormatRequest,FormatEntity>{
    @Override
    @Mapping(target = "id", ignore = true)
    FormatEntity toEntity(FormatRequest request);
}
