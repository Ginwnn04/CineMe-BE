package com.project.CineMe_BE.controller;

import com.project.CineMe_BE.constant.MessageKey;
import com.project.CineMe_BE.dto.APIResponse;
import com.project.CineMe_BE.dto.request.ActorRequest;
import com.project.CineMe_BE.service.ActorService;
import com.project.CineMe_BE.service.MinioService;
import com.project.CineMe_BE.utils.LocalizationUtils;
import jakarta.annotation.Nonnull;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.awt.*;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/actors")
@RequiredArgsConstructor
public class ActorController {
    private final ActorService actorService;
    private final LocalizationUtils localizationUtils;


    @GetMapping("/{id}/detail")
    public ResponseEntity<APIResponse> getActorById(@PathVariable @Nonnull UUID id) {
        return ResponseEntity.status(HttpStatus.CREATED).body(
                APIResponse.builder()
                        .statusCode(200)
                        .message(localizationUtils.getLocalizedMessage(MessageKey.ACTOR_GET_DETAIL))
                        .data(actorService.getActorById(id))
                        .build()
        );
    }

    @PostMapping(consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<APIResponse> createActor(@Valid @ModelAttribute ActorRequest actor) {
        return ResponseEntity.status(HttpStatus.CREATED).body(
                APIResponse.builder()
                        .statusCode(201)
                        .message(localizationUtils.getLocalizedMessage(MessageKey.ACTOR_CREATE_SUCCESS))
                        .data(actorService.createActor(actor))
                        .build()
        );
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<APIResponse> deleteActor(@PathVariable @Nonnull UUID id) {
        actorService.deleteActor(id);
        return ResponseEntity.ok(
                APIResponse.builder()
                        .statusCode(200)
                        .message(localizationUtils.getLocalizedMessage(MessageKey.ACTOR_DELETE_SUCCESS))
                        .build()
        );
    }

    @PutMapping(value = "/{id}", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<APIResponse> updateActor(@PathVariable @Nonnull UUID id,
                                                   @Valid @ModelAttribute ActorRequest actor) {
        return ResponseEntity.ok(
                APIResponse.builder()
                        .statusCode(200)
                        .message(localizationUtils.getLocalizedMessage(MessageKey.ACTOR_UPDATE_SUCCESS))
                        .data(actorService.updateActor(id, actor))
                        .build()
        );
    }
}
