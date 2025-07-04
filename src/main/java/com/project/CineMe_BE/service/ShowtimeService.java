package com.project.CineMe_BE.service;

import com.project.CineMe_BE.dto.request.ShowtimeRequest;

import java.util.UUID;

public interface ShowtimeService {

     boolean createShowtime(ShowtimeRequest showtime);
     boolean updateShowtime(UUID id, ShowtimeRequest showtime);

}
