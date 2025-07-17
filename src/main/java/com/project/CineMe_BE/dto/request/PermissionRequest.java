package com.project.CineMe_BE.dto.request;


import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PermissionRequest {
    private String key;
    private String name;
}
