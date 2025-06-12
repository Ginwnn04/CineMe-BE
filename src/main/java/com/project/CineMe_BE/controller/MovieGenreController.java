package com.project.CineMe_BE.controller;

import com.project.CineMe_BE.dto.APIResponse;
import com.project.CineMe_BE.dto.request.MovieGenreRequest;
import com.project.CineMe_BE.service.MovieGenreService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/movie-genre")
public class MovieGenreController {
    private final MovieGenreService movieGenreService;

    @PostMapping
    public ResponseEntity<APIResponse> create(@RequestBody MovieGenreRequest request) {
        return ResponseEntity.ok(APIResponse.builder()
                .statusCode(200)
                .message("Thêm thể loại cho phim thành công")
                .data(movieGenreService.create(request))
                .build());
    }

    @DeleteMapping
    public ResponseEntity<APIResponse> delete(@RequestParam UUID movieId, @RequestParam UUID genreId) {
        movieGenreService.deleteByMovieAndGenre(movieId, genreId);
        return ResponseEntity.ok(APIResponse.builder()
                .statusCode(200)
                .message("Xóa thể loại khỏi phim thành công")
                .build());
    }
}