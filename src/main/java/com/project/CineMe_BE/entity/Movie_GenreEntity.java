package com.project.CineMe_BE.entity;

import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;

import com.project.CineMe_BE.entity.id.MovieGenreId;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "movie_genres")
public class Movie_GenreEntity {
    @EmbeddedId
    private MovieGenreId id;

    @ManyToOne
    @MapsId("genre")
    @JoinColumn(name = "genre_id")
    private GenreEntity genre;

    @ManyToOne
    @MapsId("movie")
    @JoinColumn(name = "movie_id")
    private MovieEntity movie;
}
