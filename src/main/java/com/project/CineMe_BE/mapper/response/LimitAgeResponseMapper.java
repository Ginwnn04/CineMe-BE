package com.project.CineMe_BE.mapper.response;

import org.mapstruct.Mapper;

import com.project.CineMe_BE.dto.response.LimitAgeResponse;
import com.project.CineMe_BE.entity.LimitageEntity;
import com.project.CineMe_BE.mapper.BaseMapperResponse;

@Mapper(componentModel = "spring")
public interface LimitAgeResponseMapper extends BaseMapperResponse<LimitAgeResponse , LimitageEntity> {

    
}
