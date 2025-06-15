package com.project.CineMe_BE.service.impl;

import org.springframework.stereotype.Service;

import com.project.CineMe_BE.dto.request.RoomRequest;
import com.project.CineMe_BE.dto.response.RoomResponse;
import com.project.CineMe_BE.entity.RoomsEntity;
import com.project.CineMe_BE.mapper.request.RoomRequestMapper;
import com.project.CineMe_BE.mapper.response.RoomResponseMapper;
import com.project.CineMe_BE.repository.RoomRepository;
import com.project.CineMe_BE.service.TheaterService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TheaterServiceImpl implements TheaterService {
    private final RoomResponseMapper responseMapper;
    private final RoomRepository roomsRepository;
    private final RoomRequestMapper requestMapper;

    @Override
    public RoomResponse createRoom(RoomRequest request) {
        RoomsEntity entity = requestMapper.toEntity(request);
        roomsRepository.save(entity);    
        return responseMapper.toDto(entity);
    }
    
}
