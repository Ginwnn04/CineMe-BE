package com.project.CineMe_BE.mapper.response;

import com.project.CineMe_BE.dto.response.RoleResponse;
import com.project.CineMe_BE.entity.RoleEntity;
import com.project.CineMe_BE.mapper.BaseResponseMapper;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RoleResponseMapper extends BaseResponseMapper<RoleResponse, RoleEntity> {

}
