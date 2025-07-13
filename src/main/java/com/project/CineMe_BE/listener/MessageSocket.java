package com.project.CineMe_BE.listener;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Setter
@Getter
public class MessageSocket {
    private UUID showtimeId;
    private String seatNumber;
}
