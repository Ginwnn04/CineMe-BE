package com.project.CineMe_BE.controller;

import com.project.CineMe_BE.dto.response.SeatResponse;
import com.project.CineMe_BE.service.SeatService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import java.util.UUID;


@RestController
@RequiredArgsConstructor
public class SeatsController {
    private final SeatService seatService;
    
    @GetMapping("/v1/rooms/{roomId}")
    public List<SeatResponse> getSeatsByRoomId(@PathVariable("roomId") UUID roomId) {
        return seatService.getSeatsByRoomId(roomId);
    }
}
