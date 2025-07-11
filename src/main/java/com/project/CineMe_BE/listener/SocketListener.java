package com.project.CineMe_BE.listener;

import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.SocketIOServer;
import com.corundumstudio.socketio.listener.ConnectListener;
import com.corundumstudio.socketio.listener.DataListener;
import com.corundumstudio.socketio.listener.DisconnectListener;
import com.project.CineMe_BE.service.SeatService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.bridge.Message;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Slf4j
@Component
public class SocketListener {
    private final SocketIOServer server;
    private final SeatService seatService;

    public SocketListener(SocketIOServer server, SeatService seatService) {
        this.server = server;
        this.seatService = seatService;
        server.addConnectListener(onConnected());
        server.addDisconnectListener(onDisconnected());
        server.addEventListener("send_message", MessageSocket.class, onChatReceived());
    }
    private ConnectListener onConnected() {
        return (client) -> {
            log.info("Socket ID[{}]  Connected to socket", client.getSessionId().toString());
        };
    }

    private DisconnectListener onDisconnected() {
        return client -> {
            log.info("Client[{}] - Disconnected from socket", client.getSessionId().toString());
        };
    }

    private DataListener<MessageSocket> onChatReceived() {
        return (senderClient, data, ackSender) -> {
            log.info(data.toString());
            UUID userId = UUID.fromString("1a2b3c4d-5e6f-7a8b-9c0d-1e2f3a4b5c6d");
            boolean isLocked = seatService.lockSeat(data.getShowtimeId(), data.getSeatNumber(), userId);
            if (isLocked) {
                log.info("Seat {} for showtime {} locked successfully", data.getSeatNumber(), data.getShowtimeId());
                senderClient.getNamespace().getBroadcastOperations().sendEvent("seat_locked", data);
//                senderClient.sendEvent("seat_locked", data);
            } else {
                log.warn("Failed to lock seat {} for showtime {}", data.getSeatNumber(), data.getShowtimeId());
                senderClient.sendEvent("seat_lock_failed", "Failed to lock seat " + data.getSeatNumber() + " for showtime " + data.getShowtimeId());
            }
        };
    }


//    private sendMessage(SocketIOClient senderClient, MessageSocket message, String key) {
//        log.info("Received message: {}", message);
//        server.getBroadcastOperations().sendEvent("message", message);
//    }

}
