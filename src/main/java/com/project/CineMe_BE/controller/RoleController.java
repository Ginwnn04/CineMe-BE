package com.project.CineMe_BE.controller;

import com.project.CineMe_BE.dto.APIResponse;
import com.project.CineMe_BE.dto.request.RoleRequest;
import com.project.CineMe_BE.dto.response.RoleResponse;
import com.project.CineMe_BE.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/roles")
public class RoleController {
    private final RoleService roleService;
    @GetMapping
    ResponseEntity<APIResponse> getAll(){
        List<RoleResponse> listResponse = roleService.getAllRoles();
        return ResponseEntity.ok(APIResponse.builder()
                .statusCode(200)
                .message("Get all roles successfully")
                .data(listResponse)
                .build());
    }

    @GetMapping("/{id}")
    ResponseEntity<APIResponse> getById(@PathVariable("id") UUID id) {
        RoleResponse response = roleService.getRoleById(id);
        return ResponseEntity.ok(APIResponse.builder()
                .statusCode(200)
                .message("Get role by id successfully")
                .data(response)
                .build());
    }

    @PostMapping
    ResponseEntity<APIResponse> create(@RequestBody RoleRequest request) {
        RoleResponse response = roleService.createRole(request);
        return ResponseEntity.ok(APIResponse.builder()
                .statusCode(200)
                .message("Create role successfully")
                .data(response)
                .build());
    }

}
