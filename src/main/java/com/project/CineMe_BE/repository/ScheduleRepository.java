package com.project.CineMe_BE.repository;

import com.project.CineMe_BE.entity.ScheduleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;

public interface ScheduleRepository extends JpaRepository<ScheduleEntity, UUID> {
    Optional<ScheduleEntity> findByMovieIdAndDate(UUID movieId, LocalDate date);

}
