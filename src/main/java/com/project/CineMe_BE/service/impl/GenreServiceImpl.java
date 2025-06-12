package com.project.CineMe_BE.service.impl;

import com.project.CineMe_BE.dto.request.GenreRequest;
import com.project.CineMe_BE.dto.response.GenreResponse;
import com.project.CineMe_BE.entity.GenreEntity;
import com.project.CineMe_BE.exception.DataNotFoundException;
import com.project.CineMe_BE.mapper.request.GenreRequestMapper;
import com.project.CineMe_BE.mapper.response.GenreResponseMapper;
import com.project.CineMe_BE.repository.GenreRepository;
import com.project.CineMe_BE.service.GenreService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class GenreServiceImpl implements GenreService {

    private final GenreRepository genreRepository;
    private final GenreResponseMapper responseMapper;
    private final GenreRequestMapper requestMapper;

    @Override
    public GenreResponse create(GenreRequest request) {
        GenreEntity entity = requestMapper.toEntity(request);
        genreRepository.save(entity);
        return responseMapper.toDto(entity);
    }

    @Override
    public GenreResponse update(GenreRequest request, UUID id) {
        GenreEntity entity = genreRepository.findById(id)
                .orElseThrow(() -> new DataNotFoundException("Genre not found with id: " + id));
        requestMapper.update(entity, request);
        genreRepository.save(entity);
        return responseMapper.toDto(entity);
    }

    @Override
    public void delete(UUID id) {
        GenreEntity entity = genreRepository.findById(id)
                .orElseThrow(() -> new DataNotFoundException("Genre not found with id: " + id));
        genreRepository.delete(entity);
    }
}