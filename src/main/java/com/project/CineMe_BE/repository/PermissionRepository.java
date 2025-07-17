package com.project.CineMe_BE.repository;

import com.project.CineMe_BE.entity.PermissionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PermissionRepository extends JpaRepository<PermissionEntity, String> {

}
