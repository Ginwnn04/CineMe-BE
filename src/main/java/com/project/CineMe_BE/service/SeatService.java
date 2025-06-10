package com.project.CineMe_BE.service;
import java.util.List;
import java.util.UUID;

import com.project.CineMe_BE.dto.response.SeatResponse;

public interface SeatService {
    public List<SeatResponse> getSeatsByRoomId(UUID roomId);
}
