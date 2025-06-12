package com.project.CineMe_BE.service;

import com.project.CineMe_BE.dto.request.LanguageRequest;
import com.project.CineMe_BE.dto.response.LanguageResponse;
import java.util.UUID;

public interface LanguageService {
    LanguageResponse create(LanguageRequest languageRequest);
    LanguageResponse update(LanguageRequest languageRequest, UUID id);
    void delete(UUID id);
}
