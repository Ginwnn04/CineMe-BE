package com.project.CineMe_BE.repository;

import com.project.CineMe_BE.entity.Movie_GenreEntity;
import com.project.CineMe_BE.entity.id.MovieGenreId;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieGenreRepository extends JpaRepository<Movie_GenreEntity, MovieGenreId> {
}