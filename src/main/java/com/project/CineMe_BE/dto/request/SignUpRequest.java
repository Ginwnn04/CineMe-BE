package com.project.CineMe_BE.dto.request;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;


@Getter
@Setter
@Builder
public class SignUpRequest {

    private String email;
    private String password;
    private String fullName;
    private String phone;
    private UUID roleId;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;


}
