package com.project.CineMe_BE.controller;

import com.project.CineMe_BE.constant.MessageKey;
import com.project.CineMe_BE.dto.APIResponse;
import com.project.CineMe_BE.dto.response.RoomResponse;
import com.project.CineMe_BE.dto.response.ShowtimeResponse;
import com.project.CineMe_BE.dto.response.TheaterResponse;
import com.project.CineMe_BE.listener.SocketListener;
import com.project.CineMe_BE.service.ShowtimeService;
import com.project.CineMe_BE.service.TheaterService;
import com.project.CineMe_BE.utils.DateFormatUltil;
import com.project.CineMe_BE.utils.LocalizationUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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
    @GetMapping("/search")
    public ResponseEntity<APIResponse> getTheatersByMovieAndDate(@RequestParam UUID movieId,
                                                                 @RequestParam String date) {
        List<TheaterResponse> listTheater = theaterService.getAllTheatersByMovieAndDate(movieId, DateFormatUltil.formatDate(date));
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

    // Khi lay data ben admin
    @GetMapping("/{theaterId}/rooms/{roomId}/showtimes")
    public ResponseEntity<APIResponse> getRoomsByTheaterId(@PathVariable UUID theaterId,
                                                           @PathVariable UUID roomId,
                                                           @RequestParam(required = false) LocalDate date) {
        List<ShowtimeResponse> listShowtimes = theaterService.getShowtimesByTheaterAndRoom(theaterId, roomId, date);
        return ResponseEntity.ok(
                APIResponse.builder()
                        .statusCode(200)
                        .message(localizationUtils.getLocalizedMessage(MessageKey.ROOM_GET_ALL_SUCCESS))
                        .data(listShowtimes)
                        .build()
        );
    }

}
