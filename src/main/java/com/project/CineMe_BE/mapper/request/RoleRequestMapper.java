package com.project.CineMe_BE.mapper.request;

import com.project.CineMe_BE.dto.request.RoleRequest;
import com.project.CineMe_BE.entity.RoleEntity;
import com.project.CineMe_BE.mapper.BaseRequestMapper;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RoleRequestMapper extends BaseRequestMapper<RoleRequest, RoleEntity> {
}
