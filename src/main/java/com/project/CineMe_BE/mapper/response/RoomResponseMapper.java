package com.project.CineMe_BE.mapper.response;

import com.project.CineMe_BE.dto.response.RoomResponse;
import com.project.CineMe_BE.entity.RoomsEntity;
import com.project.CineMe_BE.mapper.BaseResponseMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface RoomResponseMapper extends BaseResponseMapper<RoomResponse, RoomsEntity> {

}
