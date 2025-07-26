package com.project.CineMe_BE.repository;

import java.util.UUID;

import com.project.CineMe_BE.repository.projection.SeatWithStatusProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import com.project.CineMe_BE.entity.SeatsEntity;
import com.project.CineMe_BE.repository.custom.SeatsCustomRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SeatsRepository extends JpaRepository<SeatsEntity, UUID> , SeatsCustomRepository {
    List<SeatsEntity> findByRoomId(UUID roomId);


    @Query(value = """
    SELECT s.id as id, s.seat_number as seatNumber, s.seat_type as seatType,
           CASE 
               WHEN bs.id IS NOT NULL THEN 'BOOKED'
               ELSE 'AVAILABLE'
           END AS status
    FROM seats s
    LEFT JOIN booking_seats bs ON bs.seat_id = s.id
    JOIN showtimes st ON st.room_id = s.room_id
    WHERE st.id = :showtimeId
    """, nativeQuery = true)
    List<SeatWithStatusProjection> findByShowtimeId(UUID showtimeId);
}
