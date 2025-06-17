package com.project.CineMe_BE.repository;

import com.project.CineMe_BE.entity.RoomsEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface RoomRepository extends JpaRepository<RoomsEntity, UUID> {

    List<RoomsEntity> findByTheaterId(UUID theaterId);
}
