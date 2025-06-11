package com.project.CineMe_BE.mapper.response;

import com.project.CineMe_BE.dto.response.ActorResponse;
import com.project.CineMe_BE.entity.ActorEntity;
import com.project.CineMe_BE.mapper.BaseResponseMapper;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ActorResponseMapper extends BaseResponseMapper<ActorResponse, ActorEntity> {

}
