package com.project.CineMe_BE.listener;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;
import java.util.UUID;

@Setter
@Getter
@AllArgsConstructor
public class MessageSocket {
    private UUID showtimeId;
    private UUID userId;
    private Set<String> seatNumber;
}
