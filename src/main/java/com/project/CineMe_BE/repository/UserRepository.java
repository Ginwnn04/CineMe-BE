package com.project.CineMe_BE.repository;

import com.project.CineMe_BE.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.swing.text.html.Option;
import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<UserEntity, UUID> {
    Optional<UserEntity> findByEmail(String email);
}
