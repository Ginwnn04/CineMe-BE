package com.project.CineMe_BE.controller;

import com.project.CineMe_BE.dto.APIResponse;
import com.project.CineMe_BE.dto.request.PermissionRequest;
import com.project.CineMe_BE.service.PermissionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/permissions")
public class PermissionController {
    private final PermissionService permissionService;
    @GetMapping
    public ResponseEntity<APIResponse> getAll() {
        return ResponseEntity.ok(APIResponse.builder()
                .statusCode(200)
                .message("Get all permissions successfully")
                .data(permissionService.getAll())
                .build());
    }

    @GetMapping("/{key}")
    public ResponseEntity<APIResponse> getByKey(@PathVariable("key") String key) {
        return ResponseEntity.ok(APIResponse.builder()
                .statusCode(200)
                .message("Get permission by key successfully")
                .data(permissionService.getByKey(key))
                .build());
    }

    @PostMapping
    public ResponseEntity<APIResponse> create(@RequestBody PermissionRequest request) {
        return ResponseEntity.ok(APIResponse.builder()
                .statusCode(200)
                .message("Create permission successfully")
                .data(permissionService.create(request))
                .build());
    }
}
