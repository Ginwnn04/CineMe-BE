package com.project.CineMe_BE.entity;

import jakarta.persistence.*;
import lombok.*;
import java.util.UUID;
import java.util.List;

@Entity
@Table(name = "movies")
@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RoomsEntity {
    @Id
    @Column(name = "id")
    private UUID id;

    @Column(name = "name")
    private String name;

    @Column(name = "room_type_id")
    private UUID roomTypeId;

    @Column(name = "theater_id")
    private UUID theaterId;

    @OneToMany(mappedBy = "roomId", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<SeatsEntity> seats;
}
