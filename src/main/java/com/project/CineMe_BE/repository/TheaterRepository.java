package com.project.CineMe_BE.repository;

import com.project.CineMe_BE.entity.TheaterEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface TheaterRepository extends JpaRepository<TheaterEntity, UUID> {
}
