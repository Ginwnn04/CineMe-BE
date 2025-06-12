package com.project.CineMe_BE.service;

import com.project.CineMe_BE.dto.request.FormatRequest;
import com.project.CineMe_BE.dto.response.FormatResponse;
import java.util.UUID;

public interface FormatService {
    FormatResponse create(FormatRequest formatRequest);
    FormatResponse update(FormatRequest formatRequest, UUID id);
    void delete(UUID id);
}