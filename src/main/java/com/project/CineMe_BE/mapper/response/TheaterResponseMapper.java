package com.project.CineMe_BE.mapper.response;

import com.project.CineMe_BE.dto.response.TheaterResponse;
import com.project.CineMe_BE.entity.TheaterEntity;
import com.project.CineMe_BE.mapper.BaseResponseMapper;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TheaterResponseMapper extends BaseResponseMapper<TheaterResponse, TheaterEntity> {

}
