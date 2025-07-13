package com.project.CineMe_BE.service.impl;

import org.springframework.stereotype.Service;

import com.project.CineMe_BE.dto.request.SeatRequest;
import com.project.CineMe_BE.dto.response.SeatResponse;
import com.project.CineMe_BE.service.SeatService;
import com.project.CineMe_BE.mapper.response.SeatResponseMapper;
import com.project.CineMe_BE.entity.SeatsEntity;
import com.project.CineMe_BE.repository.SeatsRepository;
import lombok.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import org.springframework.transaction.annotation.Transactional;

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

    private Map<Character,String> rowToType(HashMap<String, String> specialSeats){
        Map<Character,String> result = new HashMap<>();

        for (Map.Entry<String, String> entry : specialSeats.entrySet()) {
            String type = entry.getKey();
            String range = entry.getValue();
            
            if(range.length() ==1){
                result.put(range.charAt(0),type);
            }
            else if(range.length() >= 2){
                char startRow = range.charAt(0);
                char endRow = range.charAt(range.length() - 1);

                for (char row = startRow; row <= endRow; row++) {
                        result.put(row, type);
                }
            }
        }
        return result;
    }

    private HashMap<String,String> generateAllSeats(int rows ,int cols,HashMap<String, String> specialSeats){
        //specialSeats : key: VIP , value :"AH" => A to H is VIP
        //specialSeats : key: COUPLE , value :"A" => A's row  is COUPLE
        Map<Character,String> rowTypeMap = rowToType(specialSeats);
        HashMap<String,String> allSeats = new HashMap<>();
        
        for (char row = 'A' ; row <= 'A'+ ( rows - 1 ) ; row++ ) {
            for (int col = 1; col <= cols; col++) {
                String seat = row + String.valueOf(col);
                String seatType = rowTypeMap.getOrDefault(row,"STANDARD");
                allSeats.put(seat, seatType);
            }
        }
        return allSeats;
    }


    
    @Override
    @Transactional
    public boolean create(SeatRequest seatRequest) {
        UUID roomId = seatRequest.getRoomId();
        HashMap<String, String> specialSeats = seatRequest.getSpecialSeats();
        int row = seatRequest.getRow();
        int col = seatRequest.getCol();
        HashMap<String, String> allSeats = generateAllSeats(row, col, specialSeats);
        List<SeatsEntity> resultEntity = new ArrayList<>(allSeats.size());

        for (String seat : allSeats.keySet()) {
            SeatsEntity seatsEntity = SeatsEntity.builder()
                    .roomId(roomId)
                    .seatNumber(seat)
                    .seatType(allSeats.get(seat))
                    .isActive(true)
                    // .status("AVAILABLE")
                    .build();
            resultEntity.add(seatsEntity);
        }
        
        seatsRepository.bulkInsert(resultEntity);
        return true;
    }

    

}
