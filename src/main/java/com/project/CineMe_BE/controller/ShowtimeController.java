package com.project.CineMe_BE.controller;


import com.project.CineMe_BE.dto.APIResponse;
import com.project.CineMe_BE.dto.request.ShowtimeRequest;
import com.project.CineMe_BE.service.ShowtimeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/showtimes")
@RequiredArgsConstructor
public class ShowtimeController {
    private final ShowtimeService showtimeService;
    @PostMapping("")
    public ResponseEntity<APIResponse> createShowtime(@RequestBody ShowtimeRequest request) {
        showtimeService.createShowtime(request);
        return null;
    }
}
