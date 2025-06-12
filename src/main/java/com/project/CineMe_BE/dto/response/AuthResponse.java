package com.project.CineMe_BE.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class AuthResponse {
    private String email;
    private String accessToken;
    private String refreshToken;

}
