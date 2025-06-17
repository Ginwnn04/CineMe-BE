package com.project.CineMe_BE.controller;

import com.project.CineMe_BE.constant.MessageKey;
import com.project.CineMe_BE.dto.APIResponse;
import com.project.CineMe_BE.dto.response.RoomResponse;
import com.project.CineMe_BE.dto.response.TheaterResponse;
import com.project.CineMe_BE.service.TheaterService;
import com.project.CineMe_BE.utils.LocalizationUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/theaters")
@RequiredArgsConstructor
public class TheaterController {
    private final TheaterService theaterService;
    private final LocalizationUtils localizationUtils;

    @GetMapping("")
    public ResponseEntity<APIResponse> getTheaters() {
        List<TheaterResponse> listTheater = theaterService.getAllTheaters();
        return ResponseEntity.ok(
                APIResponse.builder()
                        .statusCode(200)
                        .message(localizationUtils.getLocalizedMessage(MessageKey.THEATER_GET_ALL_SUCCESS))
                        .data(listTheater)
                        .build()
        );
    }

    @GetMapping("/{id}/rooms")
    public ResponseEntity<APIResponse> getRoomsByTheaterId(@PathVariable UUID id) {
        List<RoomResponse> listRoom = theaterService.getRoomsByTheaterId(id);
        return ResponseEntity.ok(
                APIResponse.builder()
                        .statusCode(200)
                        .message(localizationUtils.getLocalizedMessage(MessageKey.ROOM_GET_ALL_SUCCESS))
                        .data(listRoom)
                        .build()
        );
    }
}
