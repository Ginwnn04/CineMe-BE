package com.project.CineMe_BE.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LimitAgeRequest {
    private String nameVn;
    private String nameEn;
    private String descVn;
    private String descEn;
}
