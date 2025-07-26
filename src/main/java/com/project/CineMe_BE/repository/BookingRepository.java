package com.project.CineMe_BE.repository;

import com.project.CineMe_BE.PaymentProjection;
import com.project.CineMe_BE.entity.BookingEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface BookingRepository extends JpaRepository<BookingEntity, UUID> {

    @Query(value = "SELECT \n" +
            "m.name_vn AS movieName,\n" +
            "m.time AS duration,\n" +
            "t.name_vn AS theaterName,\n" +
            "r.name AS roomName,\n" +
            "s.date || ' ' || st.start_time AS showtime,\n" +
            "se.seat_number AS seatNumber\n" +
            "FROM bookings b\n" +
            "JOIN showtimes st ON b.showtime_id = st.id\n" +
            "JOIN schedules s ON st.schedule_id = s.id\n" +
            "JOIN movies m ON s.movie_id = m.id\n" +
            "JOIN theaters t ON st.theater_id = t.id\n" +
            "JOIN rooms r ON st.room_id = r.id\n" +
            "JOIN booking_seats bs ON bs.booking_id = b.id\n" +
            "JOIN seats se ON bs.seat_id = se.id\n" +
            "WHERE b.id = :bookingId", nativeQuery = true)
    List<PaymentProjection> getPaymentInfoByBookingId(UUID bookingId);
}
