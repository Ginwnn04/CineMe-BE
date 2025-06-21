package com.project.CineMe_BE.entity;
import jakarta.persistence.*;
import lombok.*;
import java.util.UUID;

@Entity
@Table(name = "theaters")
@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TheaterEntity {
    @Id
    @GeneratedValue
    @Column(name = "id", columnDefinition = "uuid")
    private UUID id;

    @Column(name = "name_vn")
    private String nameVn;

    @Column(name = "name_en")
    private String nameEn;

}
