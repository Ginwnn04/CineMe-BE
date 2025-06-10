package com.project.CineMe_BE.repository.custom;

import com.project.CineMe_BE.entity.SeatsEntity;
import java.util.List;

public interface SeatsCustomRepository {
    void bulkInsert(List<SeatsEntity> seatsEntities);
}
