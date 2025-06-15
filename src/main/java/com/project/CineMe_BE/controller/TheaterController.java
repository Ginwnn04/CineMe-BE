package com.project.CineMe_BE.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.project.CineMe_BE.constant.MessageKey;
import com.project.CineMe_BE.dto.APIResponse;
import com.project.CineMe_BE.dto.request.RoomRequest;
import com.project.CineMe_BE.service.TheaterService;
import com.project.CineMe_BE.utils.LocalizationUtils;
import org.springframework.web.bind.annotation.RequestBody;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/theater")
public class TheaterController {
    private final TheaterService service;
    private final LocalizationUtils localizationUtils;

    @PostMapping
    public ResponseEntity<APIResponse> create(@RequestBody RoomRequest request) {
        return ResponseEntity.ok(APIResponse.builder()
                .statusCode(201)
                .message(localizationUtils.getLocalizedMessage(MessageKey.ROOM_CREATE_SUCCESS))
                .data(service.createRoom(request))
                .build());
    }
}
