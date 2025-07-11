package com.project.CineMe_BE;

import java.util.UUID;

public interface SeatWithStatusProjection {
    UUID getId();
    String getSeatNumber();
    String getSeatType();
    String getStatus();
}
