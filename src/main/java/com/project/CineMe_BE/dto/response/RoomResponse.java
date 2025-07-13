package com.project.CineMe_BE.dto.response;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class RoomResponse {
    private UUID id;
    private String name;
    private String type;
}
