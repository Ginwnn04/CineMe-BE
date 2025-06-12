package com.project.CineMe_BE.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Entity
@Table(name = "limitages")
@Setter
@Getter
public class LimitageEntity {
    @Id
    @Column(name = "id")
    @GeneratedValue
    private UUID id;

    @Column(name = "name_vn")
    private String nameVn;

    @Column(name = "name_en")
    private String nameEn;

    @Column(name = "desc_vn")
    private String descVn;

    @Column(name = "desc_en")
    private String descEn;

}
