package com.project.CineMe_BE.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

import jakarta.validation.constraints.NotBlank;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RoomRequest {

    // @NotBlank(message = "Tên Phòng không được để trống")
    private String name;

    // @NotBlank(message = "Room Type không được để trống")
    private String type;

    // @NotBlank(message = "Rạp Phim không được để trống")
    private UUID theaterId;
}