package com.project.CineMe_BE.service;

import com.project.CineMe_BE.dto.response.RoomResponse;
import com.project.CineMe_BE.dto.response.TheaterResponse;

import java.util.List;
import java.util.UUID;

public interface TheaterService {

    List<TheaterResponse> getAllTheaters();

    List<RoomResponse> getRoomsByTheaterId(UUID theaterId);
}
