package com.project.CineMe_BE.entity;

import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Table(name = "permissions")
@Getter
public class PermissionEntity {
    @Id
    @Column(name = "key")
    private String key;

    @Column(name = "name")
    private String name;

}
