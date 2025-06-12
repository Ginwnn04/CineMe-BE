package com.project.CineMe_BE.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.project.CineMe_BE.entity.GenreEntity;
import java.util.UUID;

public interface GenreRepository extends JpaRepository<GenreEntity, UUID> {

}