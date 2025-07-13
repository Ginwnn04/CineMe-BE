package com.project.CineMe_BE.service.impl;

import com.project.CineMe_BE.SeatWithStatusProjection;
import com.project.CineMe_BE.constant.CacheName;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.Cursor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ScanOptions;
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
    private final RedisTemplate<String, String> redisTemplate;

    
    @Override
    @Cacheable(value = CacheName.SEAT, key = "#roomId")
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

    @Override
    public List<SeatResponse> getSeatsByShowtime(UUID showtimeId) {
        List<SeatsEntity> listSeats = getSeatsWithLockStatusByShowtimeId(showtimeId);
        return seatResponseMapper.toListDto(listSeats);
    }


    private List<SeatsEntity> getSeatsWithLockStatusByShowtimeId(UUID showtimeId) {
        List<SeatWithStatusProjection> entityList = seatsRepository.findByShowtimeId(showtimeId);
        if (entityList == null && entityList.isEmpty()) {
            return new ArrayList<>();
        }
        List<SeatsEntity> listSeats = new ArrayList<>();
        for (SeatWithStatusProjection projection : entityList) {
            SeatsEntity entity = new SeatsEntity();
            entity.setId(projection.getId());
            entity.setSeatNumber(projection.getSeatNumber());
            entity.setSeatType(projection.getSeatType());
            entity.setStatus(projection.getStatus());
            listSeats.add(entity);
        }
        List<String> lockedSeats = getSeatNumberLocked(showtimeId);
        for (SeatsEntity seat : listSeats) {
            if (lockedSeats.contains(seat.getSeatNumber())) {
                seat.setStatus("LOCKED");
            }
        }
        return listSeats;
    }


    private boolean isAvailable(UUID showtimeId, String seatNumber) {
        return getSeatsWithLockStatusByShowtimeId(showtimeId).stream()
                .anyMatch(seat -> seat.getSeatNumber().equals(seatNumber) && seat.getStatus().equals("AVAILABLE"));
    }

    @Override
    public boolean lockSeat(UUID showtimeId, String seatNumber, UUID userId) {
        if (!isAvailable(showtimeId, seatNumber)) {
            return false;
        }
        String redisKey = "seat-lock:" + showtimeId + ":" + seatNumber;
        if (redisTemplate.hasKey(redisKey)) {
            return false;
        }
        redisTemplate.opsForValue().set(redisKey, userId.toString());
        redisTemplate.expire(redisKey, Duration.ofSeconds(60));
        return true;
    }
    private List<String> getSeatNumberLocked(UUID showtimeId) {
        String pattern = "seat-lock:" + showtimeId + ":*";
        ScanOptions options = ScanOptions.scanOptions().match(pattern).count(100).build();
        Cursor<byte[]> cursor = redisTemplate.getConnectionFactory().getConnection().scan(options);
        List<String> lockedSeats = new ArrayList<>();
        while (cursor.hasNext()) {
            String key = new String(cursor.next());
            String seatNumber = key.substring(key.lastIndexOf(":") + 1);
            lockedSeats.add(seatNumber);
        }

        return lockedSeats;
    }


}
