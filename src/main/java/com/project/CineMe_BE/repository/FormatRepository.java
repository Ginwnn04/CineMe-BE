package com.project.CineMe_BE.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.project.CineMe_BE.entity.FormatEntity;
import java.util.UUID;

public interface FormatRepository extends JpaRepository<FormatEntity, UUID> {

}