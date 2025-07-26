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
            "LEFT JOIN FETCH s.language " +
            "LEFT JOIN FETCH s.format " +
            "WHERE s.theater.id = :theaterId AND s.room.id = :roomId AND s.schedule.date = :date")
    List<ShowtimeEntity> findByTheaterAndRoom(UUID theaterId, UUID roomId, LocalDate date);


    @Query("SELECT s FROM ShowtimeEntity s " +
            "LEFT JOIN FETCH s.room " +
            "LEFT JOIN FETCH s.theater " +
            "LEFT JOIN FETCH s.schedule " +
            "LEFT JOIN FETCH s.schedule.movie " +
            "LEFT JOIN FETCH s.language " +
            "LEFT JOIN FETCH s.format " +
            "WHERE s.theater.id = :theaterId AND s.schedule.movie.id = :movieId AND s.schedule.date = :date")
    List<ShowtimeEntity> findByMovieIdAndTheaterIdAndDate(UUID movieId, UUID theaterId, LocalDate date);


    @Query("SELECT s FROM ShowtimeEntity s " +
            "LEFT JOIN FETCH s.room " +
            "LEFT JOIN FETCH s.theater " +
            "LEFT JOIN FETCH s.schedule " +
            "LEFT JOIN FETCH s.language " +
            "LEFT JOIN FETCH s.format " +
            "LEFT JOIN FETCH s.schedule.movie " +
            "LEFT JOIN FETCH s.schedule.movie.limitage " +
            "LEFT JOIN FETCH s.schedule.movie.listGenre " +
            "WHERE s.theater.id = :theaterId AND s.schedule.date = :date")
    List<ShowtimeEntity> findByTheaterIdAndDate(UUID theaterId, LocalDate date);

    @Query("SELECT s.privateKey FROM ShowtimeEntity s " +
            "WHERE s.id = :showtimeId")
    String getPriveKey(UUID showtimeId);
}
