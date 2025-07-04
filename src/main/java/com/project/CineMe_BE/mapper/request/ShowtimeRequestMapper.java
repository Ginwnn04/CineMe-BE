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
    ShowtimeEntity toEntity(ShowtimeRequest dto);



    @Named("mapToTheater")
    static TheaterEntity mapToTheater(UUID id) {
        return id == null ? null : TheaterEntity.builder().id(id).build();
    }

    @Named("mapToRoom")
    static RoomsEntity mapToRoom(UUID id) {
        return id == null ? null : RoomsEntity.builder().id(id).build();
    }

}
