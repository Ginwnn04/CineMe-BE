package com.project.CineMe_BE.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.project.CineMe_BE.entity.LanguageEntity;
import java.util.UUID;

public interface LanguageRepository extends JpaRepository<LanguageEntity, UUID> {
   
    
}
