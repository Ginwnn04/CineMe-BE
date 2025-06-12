package com.project.CineMe_BE.controller;

import com.project.CineMe_BE.constant.MessageKey;
import com.project.CineMe_BE.dto.APIResponse;
import com.project.CineMe_BE.dto.request.GenreRequest;
import com.project.CineMe_BE.service.GenreService;
import com.project.CineMe_BE.utils.LocalizationUtils;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/genre")
public class GenreController {
    private final GenreService genreService;
    private final LocalizationUtils localizationUtils;
    @PostMapping
    public ResponseEntity<APIResponse> create(@RequestBody GenreRequest request) {
        return ResponseEntity.ok(APIResponse.builder()
                .statusCode(200)
                .message(localizationUtils.getLocalizedMessage( MessageKey.GENRE_CREATE_SUCCESS))
                .data(genreService.create(request))
                .build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<APIResponse> update(@PathVariable UUID id, @RequestBody GenreRequest request) {
        return ResponseEntity.ok(APIResponse.builder()
                .statusCode(200)
                .message(localizationUtils.getLocalizedMessage(MessageKey.GENRE_UPDATE_SUCCESS))
                .data(genreService.update(request, id))
                .build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<APIResponse> delete(@PathVariable UUID id) {
        genreService.delete(id);
        return ResponseEntity.ok(APIResponse.builder()
                .statusCode(200)
                .message(localizationUtils.getLocalizedMessage(MessageKey.GENRE_DELETE_SUCCESS))    
                .build());
    }
}