package com.project.CineMe_BE.service.impl;

import java.util.UUID;

import org.springframework.stereotype.Service;

import com.project.CineMe_BE.dto.request.LanguageRequest;
import com.project.CineMe_BE.dto.response.LanguageResponse;
import com.project.CineMe_BE.entity.LanguageEntity;
import com.project.CineMe_BE.exception.DataNotFoundException;
import com.project.CineMe_BE.mapper.request.LanguageRequestMapper;
import com.project.CineMe_BE.mapper.response.LanguageResponseMapper;
import com.project.CineMe_BE.repository.LanguageRepository;
import com.project.CineMe_BE.service.LanguageService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class LanguageServiceImpl implements LanguageService {
    private final LanguageRepository languageRepository;
    private final LanguageResponseMapper responseMapper;
    private final LanguageRequestMapper requestMapper;
    @Override
    public LanguageResponse create(LanguageRequest languageRequest) {
        LanguageEntity entity = requestMapper.toEntity(languageRequest);
        languageRepository.save(entity);
        return responseMapper.toDto(entity);
    }

    @Override
    public void delete(UUID id) {
        var entity = languageRepository.findById(id)
                .orElseThrow(() -> new DataNotFoundException("Language not found with id: " + id));
        languageRepository.deleteById(id);
    }

    @Override
    public LanguageResponse update(LanguageRequest languageRequest, UUID id) {
        LanguageEntity entity = languageRepository.findById(id)
                .orElseThrow(() -> new DataNotFoundException("Language not found with id: " + id));
        requestMapper.update(entity, languageRequest);
        languageRepository.save(entity);
        return responseMapper.toDto(entity);
    }


    
}
