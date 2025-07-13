package com.project.CineMe_BE.service;

import com.project.CineMe_BE.dto.request.RoomRequest;
import com.project.CineMe_BE.dto.response.RoomResponse;
import com.project.CineMe_BE.dto.response.ShowtimeResponse;
import com.project.CineMe_BE.dto.response.TheaterResponse;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public interface TheaterService {

    List<TheaterResponse> getAllTheaters();
    List<RoomResponse> getRoomsByTheaterId(UUID theaterId);

    List<TheaterResponse> getAllTheatersByMovieAndDate(UUID movieId, LocalDate date);
    List<ShowtimeResponse> getShowtimesByTheaterAndRoom(UUID theaterId, UUID roomId, LocalDate date);
    RoomResponse createRoom(RoomRequest request);
}
