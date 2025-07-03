package com.project.CineMe_BE.service.impl;

import com.project.CineMe_BE.constant.MessageKey;
import com.project.CineMe_BE.dto.request.ShowtimeRequest;
import com.project.CineMe_BE.entity.MovieEntity;
import com.project.CineMe_BE.entity.ScheduleEntity;
import com.project.CineMe_BE.entity.ShowtimeEntity;
import com.project.CineMe_BE.exception.DataNotFoundException;
import com.project.CineMe_BE.exception.DataNotValid;
import com.project.CineMe_BE.mapper.request.ShowtimeRequestMapper;
import com.project.CineMe_BE.repository.*;
import com.project.CineMe_BE.service.MovieService;
import com.project.CineMe_BE.service.ShowtimeService;
import com.project.CineMe_BE.service.TheaterService;
import com.project.CineMe_BE.utils.LocalizationUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ShowtimeServiceImpl implements ShowtimeService {
    private final ShowtimeRequestMapper showtimeRequestMapper;
    private final ShowtimeRepository showtimeRepository;
    private final RoomRepository roomRepository;
    private final MovieRepository movieRepository;
    private final TheaterRepository theaterRepository;
    private final ScheduleRepository scheduleRepository;
    private final LocalizationUtils localizationUtils;

    @Override
    @Transactional
    public boolean createShowtime(ShowtimeRequest showtime) {
        theaterRepository.findById(showtime.getTheaterId())
                .orElseThrow(() -> new DataNotFoundException(localizationUtils.getLocalizedMessage(MessageKey.THEATER_NOT_FOUND)));
        movieRepository.findById(showtime.getMovieId())
                .orElseThrow(() -> new DataNotFoundException(localizationUtils.getLocalizedMessage(MessageKey.MOVIE_NOT_FOUND)));
        roomRepository.findByIdAndTheater_Id(showtime.getRoomId(), showtime.getTheaterId())
                .orElseThrow(() -> new DataNotFoundException(localizationUtils.getLocalizedMessage(MessageKey.ROOM_NOT_FOUND)));
        if (!isShowtimeValid(showtime.getStartTime(), showtime.getEndTime())) {
            throw new DataNotValid(localizationUtils.getLocalizedMessage(MessageKey.SHOWTIME_INVALID_TIME));
        }
        if (isShowtimeConflict(showtime)) {
            throw new DataNotValid(localizationUtils.getLocalizedMessage(MessageKey.SHOWTIME_CONFLICT));
        }
        ScheduleEntity schedule = scheduleRepository.findByMovieIdAndDate(showtime.getMovieId(), showtime.getStartTime().toLocalDate())
                .orElseGet(() -> {
                    ScheduleEntity newSchedule = ScheduleEntity.builder()
                            .date(showtime.getStartTime().toLocalDate())
                            .movie(MovieEntity.builder().id(showtime.getMovieId()).build())
                            .build();
                    return scheduleRepository.save(newSchedule);
                });
        ShowtimeEntity entity = showtimeRequestMapper.toEntity(showtime);
        String key = generatePrivateKey();
        entity.setPrivateKey(key);
        entity.setSchedule(schedule);
        log.info("Private key: {}", key);
        showtimeRepository.save(entity);
        return true;
    }


    private String generatePrivateKey() {
        long currentTimeMillis = System.currentTimeMillis();
        long randomPart = (long) (Math.random() * Math.pow(10, 5));
        String key = String.valueOf(currentTimeMillis + randomPart).substring(0, 10);
        return key;
    }

    private boolean isShowtimeValid(LocalDateTime startTime, LocalDateTime endTime) {
        return startTime.isBefore(endTime);

    }

    private boolean isShowtimeConflict(ShowtimeRequest request) {
        List<ShowtimeEntity> existingShowtimes = showtimeRepository.findByTheaterAndRoom(
                request.getTheaterId(), request.getRoomId(), request.getStartTime().toLocalDate());
        boolean isConflict = existingShowtimes.stream()
                .anyMatch(showtime -> {
                    LocalTime existingStart = showtime.getStartTime();
                    LocalTime existingEnd = showtime.getEndTime();
                    LocalTime requestStart = request.getStartTime().toLocalTime();
                    LocalTime requestEnd = request.getEndTime().toLocalTime();
                    return requestStart.isBefore(existingEnd) && requestEnd.isAfter(existingStart);
                });
        return isConflict;
    }
}
