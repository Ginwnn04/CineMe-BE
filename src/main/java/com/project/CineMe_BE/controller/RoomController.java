package com.project.CineMe_BE.controller;

import com.project.CineMe_BE.constant.MessageKey;
import com.project.CineMe_BE.dto.APIResponse;
import com.project.CineMe_BE.dto.request.SeatRequest;
import com.project.CineMe_BE.dto.response.SeatResponse;
import com.project.CineMe_BE.service.SeatService;
import com.project.CineMe_BE.utils.LocalizationUtils;

import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/rooms")
public class RoomController {
    private final SeatService seatService;
    private final LocalizationUtils localizationUtils;
    @GetMapping("/{roomId}/seats")
    public ResponseEntity<APIResponse> getSeatsByRoomId(@PathVariable("roomId") UUID roomId) {
        List<SeatResponse> result = seatService.getSeatsByRoomId(roomId);
         APIResponse response = APIResponse.builder()
                                            .statusCode(200)
                                          .message(localizationUtils.getLocalizedMessage(MessageKey.SEAT_GET_LIST))
                                          .data(result)
                                          .build();
        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<APIResponse> createSeats(@RequestBody SeatRequest seatRequest) {
        Boolean isCreated = seatService.create(seatRequest);
        APIResponse response = APIResponse.builder()
                                            .statusCode(201)
                                          .message(localizationUtils.getLocalizedMessage(MessageKey.SEAT_CREATE_SUCCESS))
                                          .data(isCreated)
                                          .build();
        return ResponseEntity.ok(response);
    }
}