package com.project.CineMe_BE.controller;

import com.project.CineMe_BE.dto.APIResponse;
import com.project.CineMe_BE.dto.request.LanguageRequest;
import com.project.CineMe_BE.service.LanguageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/language")
public class LanguageController {
    private final LanguageService languageService;

    @PostMapping
    public ResponseEntity<APIResponse> create(@RequestBody LanguageRequest request) {
        return ResponseEntity.ok(APIResponse.builder()
                .statusCode(200)
                .message("Tạo ngôn ngữ thành công")
                .data(languageService.create(request))
                .build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<APIResponse> update(@PathVariable UUID id, @RequestBody LanguageRequest request) {
        return ResponseEntity.ok(APIResponse.builder()
                .statusCode(200)
                .message("Cập nhật ngôn ngữ thành công")
                .data(languageService.update(request, id))
                .build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<APIResponse> delete(@PathVariable UUID id) {
        languageService.delete(id);
        return ResponseEntity.ok(APIResponse.builder()
                .statusCode(200)
                .message("Xóa ngôn ngữ thành công")
                .build());
    }
}