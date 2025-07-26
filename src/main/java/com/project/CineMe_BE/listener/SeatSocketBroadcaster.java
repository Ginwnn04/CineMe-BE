package com.project.CineMe_BE.listener;

import com.corundumstudio.socketio.SocketIOServer;
import com.project.CineMe_BE.service.SeatService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Component
@RequiredArgsConstructor
@Slf4j
public class SeatSocketBroadcaster {
    private final SeatService seatService;
    private final SocketIOServer server;

    public boolean lockSeatAndBroadcast(UUID showtimeId, List<UUID> seatIds, UUID userId) {
        try {
            boolean isLocked = seatService.lockSeats(showtimeId, seatIds, userId);
            MessageSocket data = new MessageSocket(showtimeId, userId, seatIds);

            if (isLocked) {
                log.info("Seats {} for showtime {} locked successfully", seatIds, showtimeId);
                server.getBroadcastOperations().sendEvent("seat_locked", data);
            } else {
                log.warn("Failed to lock seats {} for showtime {}", seatIds, showtimeId);
                server.getBroadcastOperations().sendEvent("seat_lock_failed", data);
            }
            return isLocked;
        } catch (IllegalArgumentException e) {
            log.error("Error locking seats for showtime {}: {}", showtimeId, e.getMessage());
            server.getBroadcastOperations().sendEvent("seat_lock_failed",
                    new MessageSocket(showtimeId, userId, seatIds));
            return false;
        }
    }
}
