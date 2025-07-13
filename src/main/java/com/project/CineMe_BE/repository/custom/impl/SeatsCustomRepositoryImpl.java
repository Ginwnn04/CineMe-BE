package com.project.CineMe_BE.repository.custom.impl;

import org.springframework.stereotype.Repository;

import com.project.CineMe_BE.entity.SeatsEntity;
import com.project.CineMe_BE.repository.custom.SeatsCustomRepository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.UUID;

@Repository
public class SeatsCustomRepositoryImpl implements SeatsCustomRepository{
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional
    public void bulkInsert(List<SeatsEntity> seats) {
        if (seats.isEmpty()) return;
        StringBuilder sql = new StringBuilder("INSERT INTO seats (id,room_id ,seat_number, seat_type, is_active) VALUES ");
        for (int i = 0; i < seats.size(); i++) {
            sql.append("(? , ?, ?, ?, ?)");
            if (i < seats.size() - 1) sql.append(",");
        }
        var query = entityManager.createNativeQuery(sql.toString());
        int idx = 1;
        for (SeatsEntity seat : seats) {
            query.setParameter(idx++, UUID.randomUUID());
//            query.setParameter(idx++, seat.getRoomId());
            query.setParameter(idx++, seat.getSeatNumber());
            query.setParameter(idx++, seat.getSeatType());
            query.setParameter(idx++, seat.getIsActive());
            // query.setParameter(idx++, seat.getStatus());
        }
        query.executeUpdate();
    }
}
