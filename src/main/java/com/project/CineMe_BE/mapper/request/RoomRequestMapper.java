package com.project.CineMe_BE.mapper.request;

import java.util.UUID;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.Named;

import com.project.CineMe_BE.dto.request.RoomRequest;
import com.project.CineMe_BE.entity.RoomsEntity;
import com.project.CineMe_BE.entity.TheaterEntity;
import com.project.CineMe_BE.mapper.BaseRequestMapper;

@Mapper(componentModel = "spring")
public interface RoomRequestMapper extends BaseRequestMapper<RoomRequest,RoomsEntity> {
    @Override
    @Mappings({
        @Mapping(source = "theaterId", target = "theater" , qualifiedByName = "UUIDToTheaterEntity"),
        @Mapping(target = "id", ignore = true),
        @Mapping(target = "seats" , ignore = true)
    })
    RoomsEntity toEntity(RoomRequest dto);

    @Named("UUIDToTheaterEntity")
    default TheaterEntity UUIDToTheaterEntity(UUID theaterId) {
        if (theaterId == null) {
            return null;
        }
        TheaterEntity theaterEntity = new TheaterEntity();
        theaterEntity.setId(theaterId);
        return theaterEntity;
    }
}
