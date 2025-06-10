package com.project.CineMe_BE.controller;

import com.project.CineMe_BE.dto.response.MovieResponse;
import com.project.CineMe_BE.service.MovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/movies")
public class MovieController {
    private final MovieService movieService;
    @GetMapping("")
    public ResponseEntity<List<MovieResponse>> getAllMovie() {
        return ResponseEntity.ok(movieService.getAllMovie());
    }
}
