package com.project.CineMe_BE.mapper.response;

import org.mapstruct.Mapper;
import com.project.CineMe_BE.dto.response.FormatResponse;
import com.project.CineMe_BE.entity.FormatEntity;
import com.project.CineMe_BE.mapper.BaseResponseMapper;

@Mapper(componentModel = "spring")
public interface FormatResponseMapper extends BaseResponseMapper<FormatResponse, FormatEntity> {

}