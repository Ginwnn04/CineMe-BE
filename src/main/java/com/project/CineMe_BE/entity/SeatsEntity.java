package com.project.CineMe_BE.entity;

import jakarta.persistence.*;
import lombok.*;
import java.util.UUID;

@Entity
@Table(name = "seats")
@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SeatsEntity {

    @Id
    @GeneratedValue
    @Column(name = "id", columnDefinition = "uuid")
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "room_id", nullable = false)
    private RoomsEntity room;

    @Column(name = "seat_number", length = 10)
    private String seatNumber;

    @Column(name = "seat_type", length = 20)
    private String seatType;

    @Column(name = "is_active")
    private Boolean isActive;


    // @Column(name = "status", length = 255)
    // private String status;

}
