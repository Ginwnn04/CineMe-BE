package com.project.CineMe_BE.mapper.request;

import com.project.CineMe_BE.dto.request.PermissionRequest;
import com.project.CineMe_BE.entity.PermissionEntity;
import com.project.CineMe_BE.mapper.BaseRequestMapper;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PermissionRequestMapper extends BaseRequestMapper<PermissionRequest, PermissionEntity> {
}
