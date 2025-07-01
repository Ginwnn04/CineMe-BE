package com.project.CineMe_BE.service.impl;

import com.project.CineMe_BE.constant.MessageKey;
import com.project.CineMe_BE.dto.request.ShowtimeRequest;
import com.project.CineMe_BE.entity.ShowtimeEntity;
import com.project.CineMe_BE.exception.DataNotFoundException;
import com.project.CineMe_BE.mapper.request.ShowtimeRequestMapper;
import com.project.CineMe_BE.repository.RoomRepository;
import com.project.CineMe_BE.repository.ShowtimeRepository;
import com.project.CineMe_BE.repository.TheaterRepository;
import com.project.CineMe_BE.service.ShowtimeService;
import com.project.CineMe_BE.utils.LocalizationUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
public class ShowtimeServiceImpl implements ShowtimeService {
    private final ShowtimeRequestMapper showtimeRequestMapper;
    private final ShowtimeRepository showtimeRepository;
    private final RoomRepository roomRepository;
    private final LocalizationUtils localizationUtils;
    private final int lengthKey = 8;
    @Override
    @Transactional
    public boolean createShowtime(ShowtimeRequest showtime) {
        ShowtimeEntity entity = showtimeRequestMapper.toEntity(showtime);
        String key = generatePrivateKey();
        entity.setPrivateKey(key);
        log.info("Private key: {}", key);
        roomRepository.findByIdAndTheater_Id(showtime.getRoomId(), showtime.getTheaterId())
                .orElseThrow(() -> new DataNotFoundException(localizationUtils.getLocalizedMessage(MessageKey.ROOM_NOT_FOUND)));
        showtimeRepository.save(entity);
        return true;
    }


    private String generatePrivateKey() {
        long currentTimeMillis = System.currentTimeMillis();
        long randomPart = (long) (Math.random() * Math.pow(10, 5));
        String key = String.valueOf(currentTimeMillis + randomPart).substring(0, 10);
        return key;
    }



}
