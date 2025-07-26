package com.project.CineMe_BE.mapper.response;

import com.project.CineMe_BE.dto.response.ShowtimeResponse;
import com.project.CineMe_BE.entity.ShowtimeEntity;
import com.project.CineMe_BE.mapper.BaseResponseMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ShowtimeResponseMapper extends BaseResponseMapper<ShowtimeResponse, ShowtimeEntity> {

    @Mapping(target = "roomName", source = "room.name")
    @Mapping(target = "roomId", source = "room.id")
//    @Mapping(target = "movieNameVn", source = "schedule.movie.nameVn")
//    @Mapping(target = "movieNameEn", source = "schedule.movie.nameEn")
    @Mapping(target = "languageVn", source = "language.nameVn")
    @Mapping(target = "languageEn", source = "language.nameEn")
    @Mapping(target = "formatVn", source = "format.nameVn")
    @Mapping(target = "formatEn", source = "format.nameEn")
    ShowtimeResponse toDto(ShowtimeEntity entity);
}
