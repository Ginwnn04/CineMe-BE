package com.project.CineMe_BE.service.impl;

import org.springframework.stereotype.Service;

import com.project.CineMe_BE.dto.request.SeatRequest;
import com.project.CineMe_BE.dto.response.SeatResponse;
import com.project.CineMe_BE.service.SeatService;

import jakarta.transaction.Transactional;

import com.project.CineMe_BE.mapper.response.SeatResponseMapper;
import com.project.CineMe_BE.entity.SeatsEntity;
import com.project.CineMe_BE.repository.SeatsRepository;
import lombok.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class SeatServiceImpl implements SeatService{
    private final SeatResponseMapper seatResponseMapper;
    private final SeatsRepository seatsRepository;
    
    @Override
    public List<SeatResponse> getSeatsByRoomId(UUID roomId) {
        List<SeatsEntity> entityList = seatsRepository.findByRoomId(roomId);
        List<SeatResponse> responseList = seatResponseMapper.toListDto(entityList);
        return responseList; 
    }

    private List<String> generateAllSeats(){
        List<String> allSeats = new ArrayList<>();
        for (char row = 'A'; row <= 'H'; row++) {
            for (int col = 1; col <= 18; col++) {
                String seat = row + String.valueOf(col);
                allSeats.add(seat);
            }
        }
        return allSeats;
    }

    
    @Override
    @org.springframework.transaction.annotation.Transactional
    public boolean create(SeatRequest seatRequest) {
        UUID roomId = seatRequest.getRoomId();
        HashMap<String, List<String>> specialSeats = seatRequest.getSpecialSeats();
        List<String> allSeats = generateAllSeats();
        List<SeatsEntity> resultEntity = new ArrayList<>(allSeats.size());

        for (String seat : allSeats) {
            String seatType = "STANDARD";
            for ( String type : specialSeats.keySet() ) {
                if (specialSeats.get(type).contains(seat)) {
                    seatType = type;
                    break;
                }
            }
            SeatsEntity seatsEntity = SeatsEntity.builder()
                .roomId(roomId)
                .seatNumber(seat)
                .seatType(seatType)
                .isActive(true)
                .status("AVAILABLE")
                .build();
            resultEntity.add(seatsEntity);
        }
        
        seatsRepository.bulkInsert(resultEntity);
        return true;
    }

    

}
