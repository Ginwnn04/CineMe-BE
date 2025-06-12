package com.project.CineMe_BE.dto.request;


import com.project.CineMe_BE.validator.anotation.ValidImage;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
public class ActorRequest {
    @NotBlank(message = "Name cannot be blank")
    private String name;
    @ValidImage(message = "Invalid image file. Allowed types: jpeg, png, jpg. Max size: 10MB")
    private MultipartFile img;

}
