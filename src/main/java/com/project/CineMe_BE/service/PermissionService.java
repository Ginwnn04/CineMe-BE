package com.project.CineMe_BE.service;

import com.project.CineMe_BE.dto.request.PermissionRequest;
import com.project.CineMe_BE.dto.response.PermissionResponse;

import java.util.List;

public interface PermissionService {
    List<PermissionResponse> getAll();
    PermissionResponse getByKey(String key);
    PermissionResponse create(PermissionRequest request);
}
