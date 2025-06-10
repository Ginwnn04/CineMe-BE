package com.project.CineMe_BE.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "movies")
@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MoviesEntity {
    @Id
    @Column(name = "id")
    private UUID id;

    @Column(name = "name_vn")
    private String nameVn;

    @Column(name = "name_en")
    private String nameEn;

    @Column(name = "director")
    private String director;

    @Column(name = "country_id")
    private UUID countryId;

    @Column(name = "format_id")
    private UUID formatId;

    @Column(name = "release_date")
    private LocalDateTime releaseDate;

    @Column(name = "end_date")
    private LocalDateTime endDate;

    @Column(name = "brief_vn")
    private String briefVn;

    @Column(name = "brief_en")
    private String briefEn;

    @Column(name = "image")
    private String image;

    @Column(name = "himage")
    private String himage;

    @Column(name = "trailer")
    private String trailer;

    @Column(name = "status")
    private String status;

    @Column(name = "ratings")
    private String ratings;

    @Column(name = "time")
    private Long time;

    @Column(name = "limitage_id")
    private UUID limitageId;

    @Column(name = "language_id")
    private UUID languageId;

    @Column(name = "sortorder")
    private Long sortorder;

}
