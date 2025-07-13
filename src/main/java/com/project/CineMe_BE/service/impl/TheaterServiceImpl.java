package com.project.CineMe_BE.service.impl;

import com.project.CineMe_BE.constant.MessageKey;
import com.project.CineMe_BE.dto.response.RoomResponse;
import com.project.CineMe_BE.dto.response.ShowtimeResponse;
import com.project.CineMe_BE.dto.response.TheaterResponse;
import com.project.CineMe_BE.entity.RoomsEntity;
import com.project.CineMe_BE.entity.TheaterEntity;
import com.project.CineMe_BE.exception.DataNotFoundException;
import com.project.CineMe_BE.mapper.request.RoomRequestMapper;
import com.project.CineMe_BE.mapper.response.RoomResponseMapper;
import com.project.CineMe_BE.mapper.response.ShowtimeResponseMapper;
import com.project.CineMe_BE.mapper.response.TheaterResponseMapper;
import com.project.CineMe_BE.repository.RoomRepository;
import com.project.CineMe_BE.repository.ShowtimeRepository;
import com.project.CineMe_BE.repository.TheaterRepository;
import com.project.CineMe_BE.service.TheaterService;
import com.project.CineMe_BE.utils.LocalizationUtils;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import com.project.CineMe_BE.dto.request.RoomRequest;

@Service
@RequiredArgsConstructor
public class TheaterServiceImpl implements TheaterService {
    private final TheaterRepository theaterRepository;
    private final TheaterResponseMapper theaterResponseMapper;
    private final RoomResponseMapper roomResponseMapper;
    private final RoomRepository roomRepository;
    private final ShowtimeRepository showtimeRepository;
    private final ShowtimeResponseMapper showtimeResponseMapper;
    private final LocalizationUtils localizationUtils;
    private final RoomRequestMapper requestMapper;
    private final RoomRepository roomsRepository;
    private final RoomResponseMapper responseMapper;
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

    @Override
    public List<ShowtimeResponse> getShowtimesByTheaterAndRoom(UUID theaterId, UUID roomId, LocalDate date) {
        theaterRepository.findById(theaterId)
                .orElseThrow(() -> new DataNotFoundException(localizationUtils.getLocalizedMessage(MessageKey.THEATER_NOT_FOUND)));
        roomRepository.findById(roomId)
                .orElseThrow(() -> new DataNotFoundException(localizationUtils.getLocalizedMessage(MessageKey.ROOM_NOT_FOUND)));
        return showtimeRepository.findByTheaterAndRoom(theaterId, roomId, date)
                .stream()
                .map(showtimeResponseMapper::toDto)
                .toList();
    }

    @Override
    public List<TheaterResponse> getAllTheatersByMovieAndDate(UUID movieId, LocalDate date) {
        List<TheaterEntity> listTheater = theaterRepository.findAllByMovieIdAndDate(movieId, date);
        if (listTheater.isEmpty()) {
            throw new DataNotFoundException(localizationUtils.getLocalizedMessage(MessageKey.THEATER_NOT_FOUND));
        }
        return theaterResponseMapper.toListDto(listTheater);
    }

    @Override
    public RoomResponse createRoom(RoomRequest request) {
        RoomsEntity entity = requestMapper.toEntity(request);
        roomsRepository.save(entity);    
        return responseMapper.toDto(entity);
    }

    
}
