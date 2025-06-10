package com.project.CineMe_BE.dto.request;
import lombok.*;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SeatRequest {
    private UUID roomId;
    private HashMap< String , List<String> > specialSeats;
    
}