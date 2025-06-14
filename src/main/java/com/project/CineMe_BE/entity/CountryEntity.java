package com.project.CineMe_BE.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Table(name = "countries")
@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CountryEntity {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "name_vn")
    private String nameVn;

    @Column(name = "name_en")
    private String nameEn;


}
