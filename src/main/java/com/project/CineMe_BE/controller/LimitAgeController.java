package com.project.CineMe_BE.controller;

import com.project.CineMe_BE.dto.APIResponse;
import com.project.CineMe_BE.dto.request.LimitAgeRequest;
import com.project.CineMe_BE.service.LimitAgeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/limit-age")
public class LimitAgeController {
    private final LimitAgeService limitAgeService;

    @PostMapping
    public ResponseEntity<APIResponse> create(@RequestBody LimitAgeRequest request) {
        return ResponseEntity.ok(APIResponse.builder()
                .statusCode(200)
                .message("Tạo giới hạn tuổi thành công")
                .data(limitAgeService.create(request))
                .build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<APIResponse> update(@PathVariable UUID id, @RequestBody LimitAgeRequest request) {
        return ResponseEntity.ok(APIResponse.builder()
                .statusCode(200)
                .message("Cập nhật giới hạn tuổi thành công")
                .data(limitAgeService.update(id, request))
                .build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<APIResponse> delete(@PathVariable UUID id) {
        limitAgeService.delete(id);
        return ResponseEntity.ok(APIResponse.builder()
                .statusCode(200)
                .message("Xóa giới hạn tuổi thành công")
                .build());
    }
}