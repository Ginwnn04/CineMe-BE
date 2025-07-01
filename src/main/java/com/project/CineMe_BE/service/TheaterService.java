package com.project.CineMe_BE.service;

import com.project.CineMe_BE.dto.response.RoomResponse;
import com.project.CineMe_BE.dto.response.ShowtimeResponse;
import com.project.CineMe_BE.dto.response.TheaterResponse;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public interface TheaterService {

    List<TheaterResponse> getAllTheaters();
    List<RoomResponse> getRoomsByTheaterId(UUID theaterId);

    List<ShowtimeResponse> getShowtimesByTheaterAndRoom(UUID theaterId, UUID roomId, LocalDate date);
}
