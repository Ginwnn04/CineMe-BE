package com.project.CineMe_BE.repository.projection;

import java.util.UUID;

public interface SeatWithStatusProjection {
    UUID getId();
    String getSeatNumber();
    String getSeatType();
    String getStatus();
}
