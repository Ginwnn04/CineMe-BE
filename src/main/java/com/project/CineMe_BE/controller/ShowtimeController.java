package com.project.CineMe_BE.controller;


import com.project.CineMe_BE.dto.APIResponse;
import com.project.CineMe_BE.dto.request.ShowtimeRequest;
import com.project.CineMe_BE.service.ShowtimeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/showtimes")
@RequiredArgsConstructor
public class ShowtimeController {
    private final ShowtimeService showtimeService;
    @PostMapping("")
    public ResponseEntity<APIResponse> createShowtime(@RequestBody ShowtimeRequest request) {
        showtimeService.createShowtime(request);
        return ResponseEntity.status(201).body(APIResponse.builder()
                        .statusCode(201)
                        .message("Showtime created successfully")
                        .build());
    }
//    @PutMapping("/{id}")
//    public ResponseEntity<APIResponse> updateShowtime(@PathVariable UUID id, @RequestBody ShowtimeRequest request) {
//        showtimeService.updateShowtime(id, request);
//        return null;
//    }
}
