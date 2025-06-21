package com.project.CineMe_BE.service;

import com.project.CineMe_BE.dto.request.RoomRequest;
import com.project.CineMe_BE.dto.response.RoomResponse;

public interface TheaterService {
    RoomResponse createRoom(RoomRequest request);
}
