package com.project.CineMe_BE.mapper.request;

import com.project.CineMe_BE.dto.request.ActorRequest;
import com.project.CineMe_BE.entity.ActorEntity;
import com.project.CineMe_BE.mapper.BaseRequestMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface ActorRequestMapper extends BaseRequestMapper<ActorRequest, ActorEntity> {
    @Mapping(target = "img", ignore = true)
    ActorEntity toEntity(ActorRequest dto);

    @Mapping(target = "img", ignore = true)
    void update(@MappingTarget ActorEntity entity, ActorRequest dto);
}
