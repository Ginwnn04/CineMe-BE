package com.project.CineMe_BE.service;

import java.util.UUID;
import com.project.CineMe_BE.dto.request.LimitAgeRequest;
import com.project.CineMe_BE.dto.response.LimitAgeResponse;

public interface LimitAgeService {
    LimitAgeResponse create(LimitAgeRequest request);
    LimitAgeResponse update(UUID id, LimitAgeRequest request);
    void delete(UUID id);
}
