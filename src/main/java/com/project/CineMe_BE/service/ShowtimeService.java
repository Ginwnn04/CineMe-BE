package com.project.CineMe_BE.service;

import com.project.CineMe_BE.dto.request.ShowtimeRequest;
import com.project.CineMe_BE.dto.response.ShowtimeResponse;
import com.project.CineMe_BE.entity.ShowtimeEntity;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public interface ShowtimeService {

     boolean createShowtime(ShowtimeRequest showtime);
     boolean updateShowtime(UUID id, ShowtimeRequest showtime);

     List<ShowtimeResponse> getShowtimesByMovieIdAndTheaterIdAndDate(UUID movie, UUID theater, LocalDate date);

}
