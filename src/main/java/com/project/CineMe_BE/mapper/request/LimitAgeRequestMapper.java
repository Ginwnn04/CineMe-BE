package com.project.CineMe_BE.mapper.request;

import com.project.CineMe_BE.mapper.BaseRequestMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.project.CineMe_BE.dto.request.LimitAgeRequest;
import com.project.CineMe_BE.entity.LimitageEntity;


@Mapper(componentModel = "spring")
public interface LimitAgeRequestMapper extends BaseRequestMapper<LimitAgeRequest, LimitageEntity> {
    @Override
    @Mapping(target = "id", ignore = true)
    LimitageEntity toEntity(LimitAgeRequest request);

    
}
