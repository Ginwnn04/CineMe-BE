package com.project.CineMe_BE.dto.request;

import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.UUID;

@Getter
@Setter
public class BookingRequest {
    private UUID userId;
    private UUID showtimeId;
    private Long amount;
    private HashMap<UUID, String> listSeats;
}
