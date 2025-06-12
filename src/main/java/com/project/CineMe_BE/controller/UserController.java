package com.project.CineMe_BE.controller;


import com.project.CineMe_BE.dto.request.SignUpRequest;
import com.project.CineMe_BE.entity.UserEntity;
import com.project.CineMe_BE.mapper.request.UserRequestMapper;
import com.project.CineMe_BE.repository.UserRepository;
import com.project.CineMe_BE.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/users")
public class UserController {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserRequestMapper userRequestMapper;


    @PostMapping("/signup")
    public String signUp(@RequestBody SignUpRequest request) {
        UserEntity entity = userRequestMapper.toEntity(request);
        entity.setPassword(passwordEncoder.encode(request.getPassword()));
        userRepository.save(entity);
        return "Sign-up endpoint is under construction";
    }
}
