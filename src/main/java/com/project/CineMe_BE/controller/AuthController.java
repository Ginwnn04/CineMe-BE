package com.project.CineMe_BE.controller;

import com.project.CineMe_BE.dto.request.LoginRequest;
import com.project.CineMe_BE.dto.response.AuthResponse;
import com.project.CineMe_BE.service.AuthService;
import com.project.CineMe_BE.service.impl.JwtServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;
    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest request) {
        return ResponseEntity.ok(authService.login(request));
    }

}
