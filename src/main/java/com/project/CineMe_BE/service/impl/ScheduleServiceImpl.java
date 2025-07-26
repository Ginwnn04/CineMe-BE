package com.project.CineMe_BE.service.impl;

import com.project.CineMe_BE.dto.response.ScheduleResponse;
import com.project.CineMe_BE.dto.response.ShowtimeResponse;
import com.project.CineMe_BE.entity.MovieEntity;
import com.project.CineMe_BE.entity.ShowtimeEntity;
import com.project.CineMe_BE.mapper.response.ShowtimeResponseMapper;
import com.project.CineMe_BE.repository.ShowtimeRepository;
import com.project.CineMe_BE.service.ScheduleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ScheduleServiceImpl implements ScheduleService {
    private final ShowtimeRepository showtimeRepository;
    private final ShowtimeResponseMapper showtimeResponseMapper;
    @Override
    public List<ScheduleResponse> findByTheaterIdAndDate(UUID theaterId, LocalDate date) {
        List<ShowtimeEntity> showtimes = showtimeRepository.findByTheaterIdAndDate(theaterId, date);
        Map<MovieEntity, List<ShowtimeEntity>> groupedByMovie = showtimes.stream()
                .collect(Collectors.groupingBy(s -> s.getSchedule().getMovie()));

        List<ScheduleResponse> result = new ArrayList<>();

        for (Map.Entry<MovieEntity, List<ShowtimeEntity>> entry : groupedByMovie.entrySet()) {
            MovieEntity movie = entry.getKey();
            List<ShowtimeEntity> showtimesOfMovie = entry.getValue();

            ScheduleResponse response = new ScheduleResponse();
            response.setMovieId(movie.getId());
            response.setMovieNameVn(movie.getNameVn());
            response.setMovieNameEn(movie.getNameEn());
            response.setImg(movie.getImage());
            response.setLimitAgeVn(movie.getLimitage().getNameVn());
            response.setLimitAgeEn(movie.getLimitage().getNameEn());
            String generVn = movie.getListGenre().stream()
                    .map(g -> g.getNameVn())
                    .collect(Collectors.joining(", "));
            String generEn = movie.getListGenre().stream()
                    .map(g -> g.getNameEn())
                    .collect(Collectors.joining(", "));
            response.setGenreVn(generVn);
            response.setGenreEn(generEn);

            response.setShowtimes(showtimeResponseMapper.toListDto(showtimesOfMovie));
            result.add(response);
        }

        return result;

    }
}
