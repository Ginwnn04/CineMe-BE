package com.project.CineMe_BE.controller;

import com.project.CineMe_BE.constant.MessageKey;
import com.project.CineMe_BE.dto.APIResponse;
import com.project.CineMe_BE.dto.request.MovieRequest;
import com.project.CineMe_BE.dto.response.MovieResponse;
import com.project.CineMe_BE.service.MinioService;
import com.project.CineMe_BE.service.MovieService;
import com.project.CineMe_BE.utils.LocalizationUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/movies")
public class MovieController {
    private final MovieService movieService;
    private final LocalizationUtils localizationUtils;
    @PostMapping(consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<APIResponse> createMovie(@ModelAttribute MovieRequest request) {
        MovieResponse movieResponse = movieService.createMovie(request);
        return ResponseEntity.ok(APIResponse.builder()
                .statusCode(201)
                .message(localizationUtils.getLocalizedMessage(MessageKey.MOVIE_CREATE_SUCCESS))
                .data(movieResponse)
                .build());
    }

    @PutMapping(value = "/{id}", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<APIResponse> updateMovie(@PathVariable UUID id, @ModelAttribute MovieRequest request) {
        MovieResponse movieResponse = movieService.updateMovie(id, request);
        return ResponseEntity.ok(APIResponse.builder()
                .statusCode(200)
                .message(localizationUtils.getLocalizedMessage(MessageKey.MOVIE_UPDATE_SUCCESS))
                .data(movieResponse)
                .build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<APIResponse> deleteMovie(@PathVariable UUID id) {
        movieService.deleteMovie(id);
        return ResponseEntity.ok(APIResponse.builder()
                .statusCode(200)
                .message(localizationUtils.getLocalizedMessage(MessageKey.MOVIE_DELETE_SUCCESS))
                .build());
    }

    @GetMapping("")
    public ResponseEntity<APIResponse> getAllMovie() {
        return ResponseEntity.ok(APIResponse.builder()
                .statusCode(200)
                .message(localizationUtils.getLocalizedMessage(MessageKey.MOVIE_GET_ALL_SUCCESS))
                .data(movieService.getAllMovie())
                .build());
    }
    @GetMapping("/{id}/detail")
    public ResponseEntity<APIResponse> getMovieDetail(@PathVariable UUID id) {
        return ResponseEntity.ok(APIResponse.builder()
                .statusCode(200)
                .message(localizationUtils.getLocalizedMessage(MessageKey.MOVIE_GET_DETAILS))
                .data(movieService.getMovieDetail(id)) // Replace with actual movie detail data when implemented
                .build());
    }


}
