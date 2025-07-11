package com.project.CineMe_BE.service;
import java.util.List;
import java.util.UUID;

import com.project.CineMe_BE.dto.request.SeatRequest;
import com.project.CineMe_BE.dto.response.SeatResponse;

public interface SeatService {
    public List<SeatResponse> getSeatsByRoomId(UUID roomId);

    public List<SeatResponse> getSeatsByShowtime(UUID showtimeId);
    public boolean create(SeatRequest seatRequest);


    boolean lockSeat(UUID showtimeId, String seatNumber, UUID userId);
}
