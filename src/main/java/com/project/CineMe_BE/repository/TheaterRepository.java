package com.project.CineMe_BE.repository;

import com.project.CineMe_BE.entity.TheaterEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public interface TheaterRepository extends JpaRepository<TheaterEntity, UUID> {

    @Query("SELECT t FROM TheaterEntity t " +
            "LEFT JOIN FETCH t.listShowtime s " +
            "LEFT JOIN FETCH s.schedule sc " +
            "WHERE sc.movie.id = :movie AND sc.date = :date")
    List<TheaterEntity> findAllByMovieIdAndDate(UUID movie, LocalDate date);
}
