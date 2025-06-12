package com.project.CineMe_BE.mapper.request;

import com.project.CineMe_BE.dto.request.SignUpRequest;
import com.project.CineMe_BE.entity.RoleEntity;
import com.project.CineMe_BE.entity.UserEntity;
import com.project.CineMe_BE.mapper.BaseRequestMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.UUID;
@Mapper(componentModel = "spring")
public interface UserRequestMapper extends BaseRequestMapper<SignUpRequest, UserEntity> {
    @Mapping(target = "role", source = "roleId")
    UserEntity toEntity(SignUpRequest dto);

    default RoleEntity mapRole(UUID roleId) {
        if (roleId == null) {
            return null;
        }
        return RoleEntity.builder()
                .id(roleId)
                .build();
    }
}
