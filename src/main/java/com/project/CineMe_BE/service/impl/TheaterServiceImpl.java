package com.project.CineMe_BE.service.impl;

import com.project.CineMe_BE.dto.response.RoomResponse;
import com.project.CineMe_BE.dto.response.TheaterResponse;
import com.project.CineMe_BE.entity.RoomsEntity;
import com.project.CineMe_BE.entity.TheaterEntity;
import com.project.CineMe_BE.mapper.response.RoomResponseMapper;
import com.project.CineMe_BE.mapper.response.TheaterResponseMapper;
import com.project.CineMe_BE.repository.RoomRepository;
import com.project.CineMe_BE.repository.TheaterRepository;
import com.project.CineMe_BE.service.TheaterService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TheaterServiceImpl implements TheaterService {
    private final TheaterRepository theaterRepository;
    private final TheaterResponseMapper theaterResponseMapper;
    private final RoomResponseMapper roomResponseMapper;
    private final RoomRepository roomRepository;
    @Override
    public List<TheaterResponse> getAllTheaters() {
        List<TheaterEntity> listTheater = theaterRepository.findAll();
        return theaterResponseMapper.toListDto(listTheater);
    }

    @Override
    public List<RoomResponse> getRoomsByTheaterId(UUID theaterId) {
        List<RoomsEntity> listRoom = roomRepository.findByTheaterId(theaterId);
        return roomResponseMapper.toListDto(listRoom);
    }
}
