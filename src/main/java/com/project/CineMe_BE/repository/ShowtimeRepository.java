package com.project.CineMe_BE.repository;

import com.project.CineMe_BE.entity.ShowtimeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Repository
public interface ShowtimeRepository extends JpaRepository<ShowtimeEntity, UUID> {

    @Query("SELECT s FROM ShowtimeEntity s " +
            "LEFT JOIN FETCH s.room " +
            "LEFT JOIN FETCH s.theater " +
            "LEFT JOIN FETCH s.schedule " +
            "LEFT JOIN FETCH s.schedule.movie " +
            "WHERE s.theater.id = :theaterId AND s.room.id = :roomId AND s.schedule.date = :date")
    List<ShowtimeEntity> findByTheaterAndRoom(UUID theaterId, UUID roomId, LocalDate date);
}
