package com.project.CineMe_BE.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import com.project.CineMe_BE.entity.SeatsEntity;
import java.util.List;

public interface SeatsRepository extends JpaRepository<SeatsEntity , UUID > {
    List<SeatsEntity> findByRoomId(UUID roomId);
}
