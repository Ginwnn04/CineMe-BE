package com.project.CineMe_BE.service.impl;

import com.project.CineMe_BE.dto.request.FormatRequest;
import com.project.CineMe_BE.dto.response.FormatResponse;
import com.project.CineMe_BE.entity.FormatEntity;
import com.project.CineMe_BE.exception.DataNotFoundException;
import com.project.CineMe_BE.mapper.request.FormatRequestMapper;
import com.project.CineMe_BE.mapper.response.FormatResponseMapper;
import com.project.CineMe_BE.repository.FormatRepository;
import com.project.CineMe_BE.service.FormatService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class FormatServiceImpl implements FormatService {

    private final FormatRepository formatRepository;
    private final FormatResponseMapper responseMapper;
    private final FormatRequestMapper requestMapper;

    @Override
    public FormatResponse create(FormatRequest request) {
        FormatEntity entity = requestMapper.toEntity(request);
        formatRepository.save(entity);
        return responseMapper.toDto(entity);
    }

    @Override
    public FormatResponse update(FormatRequest request, UUID id) {
        FormatEntity entity = formatRepository.findById(id)
                .orElseThrow(() -> new DataNotFoundException("Format not found with id: " + id));
        requestMapper.update(entity, request);
        formatRepository.save(entity);
        return responseMapper.toDto(entity);
    }

    @Override
    public void delete(UUID id) {
        FormatEntity entity = formatRepository.findById(id)
                .orElseThrow(() -> new DataNotFoundException("Format not found with id: " + id));
        formatRepository.delete(entity);
    }
}