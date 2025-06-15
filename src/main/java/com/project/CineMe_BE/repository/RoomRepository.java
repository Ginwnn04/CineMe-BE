package com.project.CineMe_BE.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.CineMe_BE.entity.RoomsEntity;

public interface RoomRepository extends JpaRepository<RoomsEntity , UUID >  {
    // Additional query methods can be defined here if needed
    
}
