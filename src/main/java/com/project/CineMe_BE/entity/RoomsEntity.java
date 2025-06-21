package com.project.CineMe_BE.entity;

import jakarta.persistence.*;
import lombok.*;
import java.util.UUID;
import java.util.List;

@Entity
@Table(name = "rooms")
@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RoomsEntity {
    @Id
    @Column(name = "id")
    @GeneratedValue
    private UUID id;

    @Column(name = "name")
    private String name;

    @Column(name = "type")
    private String type;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "theater_id")
    private TheaterEntity theater;

    @OneToMany(mappedBy = "roomId", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<SeatsEntity> seats;
}
