package com.project.CineMe_BE.mapper.response;

import com.project.CineMe_BE.dto.response.PermissionResponse;
import com.project.CineMe_BE.entity.PermissionEntity;
import com.project.CineMe_BE.mapper.BaseResponseMapper;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PermissionResponseMapper extends BaseResponseMapper<PermissionResponse, PermissionEntity> {
}
