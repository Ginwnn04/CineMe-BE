package com.project.CineMe_BE.repository;

import com.project.CineMe_BE.entity.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface RoleRepository extends JpaRepository<RoleEntity, UUID> {

}
