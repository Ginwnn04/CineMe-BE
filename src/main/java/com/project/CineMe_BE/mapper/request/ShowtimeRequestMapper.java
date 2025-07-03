package com.project.CineMe_BE.mapper.request;

import com.project.CineMe_BE.dto.request.ShowtimeRequest;
import com.project.CineMe_BE.entity.*;
import com.project.CineMe_BE.mapper.BaseRequestMapper;
import org.mapstruct.*;

import java.util.UUID;

@Mapper(componentModel = "spring")
public interface ShowtimeRequestMapper extends BaseRequestMapper<ShowtimeRequest, ShowtimeEntity> {
    @Mapping(source = "theaterId", target = "theater", qualifiedByName = "mapToTheater")
    @Mapping(source = "roomId", target = "room", qualifiedByName = "mapToRoom")
    @Mapping(target = "startTime", expression = "java(dto.getStartTime().toLocalTime())")
    @Mapping(target = "endTime", expression = "java(dto.getEndTime().toLocalTime())")
    ShowtimeEntity toEntity(ShowtimeRequest dto);

    @Mapping(target = "startTime", expression = "java(dto.getStartTime().toLocalTime())")
    @Mapping(target = "endTime", expression = "java(dto.getEndTime().toLocalTime())")
    void update(@MappingTarget ShowtimeEntity entity, ShowtimeRequest dto);

    @Named("mapToTheater")
    static TheaterEntity mapToTheater(UUID id) {
        return id == null ? null : TheaterEntity.builder().id(id).build();
    }

    @Named("mapToRoom")
    static RoomsEntity mapToRoom(UUID id) {
        return id == null ? null : RoomsEntity.builder().id(id).build();
    }

}
