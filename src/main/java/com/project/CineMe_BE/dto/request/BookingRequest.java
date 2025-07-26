package com.project.CineMe_BE.dto.request;

import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
public class BookingRequest {
    private UUID userId;
    private UUID showtimeId;
    private Long amount;
    private List<UUID> listSeatId;
}
