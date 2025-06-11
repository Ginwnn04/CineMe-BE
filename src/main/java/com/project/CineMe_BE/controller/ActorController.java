package com.project.CineMe_BE.controller;

import com.project.CineMe_BE.dto.APIResponse;
import com.project.CineMe_BE.dto.request.ActorRequest;
import com.project.CineMe_BE.service.ActorService;
import com.project.CineMe_BE.service.MinioService;
import jakarta.annotation.Nonnull;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.awt.*;

@RestController
@RequestMapping("/api/v1/actors")
@RequiredArgsConstructor
public class ActorController {
    private final ActorService actorService;


    @PostMapping(consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<APIResponse> createActor(@Valid @ModelAttribute ActorRequest actor) {
        return ResponseEntity.status(HttpStatus.CREATED).body(
                APIResponse.builder()
                        .statusCode(201)
                        .message("Create actor successfully")
                        .data(actorService.createActor(actor))
                        .build()
        );
    }
}
