package com.project.CineMe_BE.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import com.project.CineMe_BE.entity.SeatsEntity;
import com.project.CineMe_BE.repository.custom.SeatsCustomRepository;

import java.util.List;

public interface SeatsRepository extends JpaRepository<SeatsEntity , UUID > , SeatsCustomRepository{
    List<SeatsEntity> findByRoomId(UUID roomId);
}
