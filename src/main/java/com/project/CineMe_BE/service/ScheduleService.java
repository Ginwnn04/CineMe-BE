package com.project.CineMe_BE.service;

import com.project.CineMe_BE.dto.response.ScheduleResponse;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public interface ScheduleService {

    List<ScheduleResponse> findByTheaterIdAndDate(UUID theaterId, LocalDate date);
}
