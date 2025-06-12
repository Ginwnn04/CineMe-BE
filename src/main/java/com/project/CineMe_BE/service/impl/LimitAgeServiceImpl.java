package com.project.CineMe_BE.service.impl;

import java.util.UUID;

import org.springframework.stereotype.Service;

import com.project.CineMe_BE.dto.request.LimitAgeRequest;
import com.project.CineMe_BE.dto.response.LimitAgeResponse;
import com.project.CineMe_BE.entity.LimitageEntity;
import com.project.CineMe_BE.exception.DataNotFoundException;
import com.project.CineMe_BE.mapper.request.LimitAgeRequestMapper;
import com.project.CineMe_BE.mapper.response.LimitAgeResponseMapper;
import com.project.CineMe_BE.repository.LimitageRepository;
import com.project.CineMe_BE.service.LimitAgeService;

import lombok.*;

@Service
@RequiredArgsConstructor
public class LimitAgeServiceImpl implements LimitAgeService {

    private final LimitageRepository limitAgeRepository;
    private final LimitAgeResponseMapper responseMapper;
    private final LimitAgeRequestMapper requestMapper;

    @Override
    public LimitAgeResponse create(LimitAgeRequest request) {
        LimitageEntity entity = requestMapper.toEntity(request);
        limitAgeRepository.save(entity);
        return responseMapper.toDto(entity);
    }

    @Override
    public void delete(UUID id) {
        var entity = limitAgeRepository.findById(id)
                .orElseThrow(() -> new DataNotFoundException("LimitAge not found with id: " + id));
        limitAgeRepository.deleteById(id);
    }

    @Override
    public LimitAgeResponse update(UUID id, LimitAgeRequest request) {
        LimitageEntity entity = limitAgeRepository.findById(id)
                .orElseThrow(() -> new DataNotFoundException("LimitAge not found with id: " + id));
        requestMapper.update(entity, request);
        limitAgeRepository.save(entity);
        return responseMapper.toDto(entity);
    }
}