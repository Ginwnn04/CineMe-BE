package com.project.CineMe_BE.dto.response;

import lombok.Data;
import java.util.UUID;
import java.util.List;

@Data
public class RoomResponse {
    private UUID id;
    private String name;
    private String type;
}