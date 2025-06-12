package com.project.CineMe_BE.controller;

import com.project.CineMe_BE.dto.APIResponse;
import com.project.CineMe_BE.dto.request.FormatRequest;
import com.project.CineMe_BE.service.FormatService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/format")
public class FormatController {
    private final FormatService formatService;

    @PostMapping
    public ResponseEntity<APIResponse> create(@RequestBody FormatRequest request) {
        return ResponseEntity.ok(APIResponse.builder()
                .statusCode(200)
                .message("Tạo định dạng thành công")
                .data(formatService.create(request))
                .build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<APIResponse> update(@PathVariable UUID id, @RequestBody FormatRequest request) {
        return ResponseEntity.ok(APIResponse.builder()
                .statusCode(200)
                .message("Cập nhật định dạng thành công")
                .data(formatService.update(request, id))
                .build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<APIResponse> delete(@PathVariable UUID id) {
        formatService.delete(id);
        return ResponseEntity.ok(APIResponse.builder()
                .statusCode(200)
                .message("Xóa định dạng thành công")
                .build());
    }
}