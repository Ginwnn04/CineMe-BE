package com.project.CineMe_BE.repository;

import com.project.CineMe_BE.entity.LimitageEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

public interface LimitageRepository extends JpaRepository<LimitageEntity, UUID> {
    // Define any custom query methods if needed
    
}
