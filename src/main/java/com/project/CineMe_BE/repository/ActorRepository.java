package com.project.CineMe_BE.repository;

import com.project.CineMe_BE.entity.ActorEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ActorRepository extends JpaRepository<ActorEntity, UUID> {
}
