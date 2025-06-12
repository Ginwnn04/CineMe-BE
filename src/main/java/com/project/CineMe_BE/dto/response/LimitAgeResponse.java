package com.project.CineMe_BE.dto.response;
import java.util.UUID;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LimitAgeResponse {
    private UUID id;
    private String nameVn;
    private String nameEn;
    private String descVn;
    private String descEn;
}
