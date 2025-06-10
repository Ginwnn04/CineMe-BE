package com.project.CineMe_BE.controller;

import com.project.CineMe_BE.dto.APIResponse;
import com.project.CineMe_BE.dto.response.MovieResponse;
import com.project.CineMe_BE.service.MovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/movies")
public class MovieController {
    private final MovieService movieService;
    @GetMapping("")
    public ResponseEntity<APIResponse> getAllMovie() {
        return ResponseEntity.ok(APIResponse.builder()
                .statusCode(200)
                .message("Get all movies successfully")
                .data(movieService.getAllMovie())
                .build());
    }
    @GetMapping("/{id}/detail")
    public ResponseEntity<APIResponse> getMovieDetail(@PathVariable UUID id) {
        return ResponseEntity.ok(APIResponse.builder()
                .statusCode(200)
                .message("Get movie detail successfully")
                .data(movieService.getMovieDetail(id)) // Replace with actual movie detail data when implemented
                .build());
    }


}
