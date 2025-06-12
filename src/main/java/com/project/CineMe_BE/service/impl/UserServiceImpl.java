package com.project.CineMe_BE.service.impl;

import com.project.CineMe_BE.exception.DataNotFoundException;
import com.project.CineMe_BE.repository.UserRepository;
import com.project.CineMe_BE.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Override
    public UserDetailsService userDetailsService() {
        return email -> userRepository.findByEmail(email)
                .orElseThrow(() -> new DataNotFoundException("User not found with email: " + email));
    }

}
