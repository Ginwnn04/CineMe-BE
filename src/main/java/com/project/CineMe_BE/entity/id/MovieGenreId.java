package com.project.CineMe_BE.entity.id;

import java.io.Serializable;
import java.util.UUID;
import jakarta.persistence.Embeddable;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Embeddable
@Getter
@Setter
@EqualsAndHashCode
public class MovieGenreId implements Serializable {
    private UUID movie;
    private UUID genre;
}