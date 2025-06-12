package com.project.CineMe_BE.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;
@Entity
@Table(name = "genres")
@Setter
@Getter
public class GenreEntity {
    @Id
    @Column(name = "id")
    @GeneratedValue
    private UUID id;

    @Column(name = "name_vn")
    private String nameVn;

    @Column(name = "name_en")
    private String nameEn;
}
