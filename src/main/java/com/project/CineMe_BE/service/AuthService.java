package com.project.CineMe_BE.service;

import com.project.CineMe_BE.dto.request.LoginRequest;
import com.project.CineMe_BE.dto.request.RefreshTokenRequest;
import com.project.CineMe_BE.dto.response.AuthResponse;
import jakarta.servlet.http.HttpServletRequest;

public interface AuthService {

    AuthResponse login(LoginRequest loginRequest);

    AuthResponse refreshToken(RefreshTokenRequest refreshToken);

    boolean logout(HttpServletRequest request);
}
