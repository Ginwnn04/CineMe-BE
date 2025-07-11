package com.project.CineMe_BE.config;

import com.corundumstudio.socketio.SocketIOServer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SocketIOConfig {

    @Value("${WEBSOCKET_HOST}")
    private String host;
    @Value("${WEBSOCKET_PORT}")
    private Integer port;

    @Bean
    public SocketIOServer socketIOServer() {
        com.corundumstudio.socketio.Configuration config = new com.corundumstudio.socketio.Configuration();
        config.setHostname(host);
        config.setPort(port);
//        config.setOrigin("*"); // Allow all origins, adjust as necessary for security
        return new SocketIOServer(config);
    }
}
