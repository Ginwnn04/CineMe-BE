package com.project.CineMe_BE.repository;

import com.project.CineMe_BE.entity.MovieEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface MovieRepository extends JpaRepository<MovieEntity, UUID> {
    @Query("SELECT m FROM MovieEntity m  " +
            "LEFT JOIN FETCH m.format " +
            "LEFT JOIN FETCH m.country " +
            "LEFT JOIN FETCH m.language " +
            "LEFT JOIN FETCH m.limitage " +
            "LEFT JOIN FETCH m.listActor " +
            "WHERE m.releaseDate <= CURRENT_DATE AND m.endDate >= CURRENT_DATE")
    List<MovieEntity> getAvailableMovies();
}
