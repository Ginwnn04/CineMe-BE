package com.project.CineMe_BE.controller;


import com.project.CineMe_BE.constant.MessageKey;
import com.project.CineMe_BE.dto.APIResponse;
import com.project.CineMe_BE.dto.request.ShowtimeRequest;
import com.project.CineMe_BE.dto.response.SeatResponse;
import com.project.CineMe_BE.dto.response.ShowtimeResponse;
import com.project.CineMe_BE.service.SeatService;
import com.project.CineMe_BE.service.ShowtimeService;
import com.project.CineMe_BE.utils.DateFormatUltil;
import com.project.CineMe_BE.utils.LocalizationUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.Cursor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ScanOptions;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Duration;
import java.util.*;

@RestController
@RequestMapping("/api/v1/showtimes")
@RequiredArgsConstructor
public class ShowtimeController {
    private final ShowtimeService showtimeService;
    private final SeatService seatService;
    private final LocalizationUtils localizationUtils;
    private final RedisTemplate<String, String> redisTemplate;
    @PostMapping("")
    public ResponseEntity<APIResponse> createShowtime(@RequestBody ShowtimeRequest request) {
        showtimeService.createShowtime(request);
        return ResponseEntity.status(201).body(APIResponse.builder()
                        .statusCode(201)
                        .message("Showtime created successfully")
                        .build());
    }

    @GetMapping("")
    public ResponseEntity<APIResponse> getShowtimesByTheaterId(@RequestParam UUID movieId,
                                                               @RequestParam UUID theaterId,
                                                               @RequestParam (required = true) String date) {
        List<ShowtimeResponse> listShowtimes = showtimeService.getShowtimesByMovieIdAndTheaterIdAndDate(movieId, theaterId, DateFormatUltil.formatDate(date));
        return ResponseEntity.ok(
                APIResponse.builder()
                        .statusCode(200)
                        .message(localizationUtils.getLocalizedMessage(MessageKey.SHOWTIME_GET_ALL_SUCCESS))
                        .data(listShowtimes)
                        .build()
        );
    }

    @GetMapping("/{id}/seats")
    public ResponseEntity<APIResponse> getSeatsByShowtimeId(@PathVariable UUID id) {
        List<SeatResponse> listSeats = seatService.getSeatsByShowtime(id);
        return ResponseEntity.ok(APIResponse.builder()
                .statusCode(200)
                .data(listSeats)
                .message("Seats for showtime " + id + " retrieved successfully")
                .build());
    }

//    @GetMapping("/test")
//    public String testGetSeatsByShowtimeId(@RequestParam UUID showtimeId,
//                                            @RequestParam String seatNumber,
//                                            @RequestParam UUID userId) {
//        String redisKey = "seat-lock:" + showtimeId + ":" + seatNumber;
//        redisTemplate.opsForValue().set(redisKey, userId.toString());
//        redisTemplate.expire(redisKey, Duration.ofSeconds(60));
//        return "Oke";
//    }


//    @PutMapping("/{id}")
//    public ResponseEntity<APIResponse> updateShowtime(@PathVariable UUID id, @RequestBody ShowtimeRequest request) {
//        showtimeService.updateShowtime(id, request);
//        return null;
//    }
}
