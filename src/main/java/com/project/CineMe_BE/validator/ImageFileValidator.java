package com.project.CineMe_BE.validator;

import com.project.CineMe_BE.validator.anotation.ValidImage;
import jakarta.validation.ConstraintValidator;
import org.springframework.web.multipart.MultipartFile;

import java.util.Arrays;
import java.util.List;

public class ImageFileValidator implements ConstraintValidator<ValidImage, MultipartFile> {
    private final List<String> allowedContentTypes = Arrays.asList(
            "image/jpeg", "image/png", "image/jpg"
    );
    private final long maxSize = 10 * 1024 * 1024; // 10 MB

    @Override
    public void initialize(ValidImage constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(MultipartFile file, jakarta.validation.ConstraintValidatorContext context) {
        if (file == null || file.isEmpty()) {
            return true;
        }
        return allowedContentTypes.contains(file.getContentType()) &&
               file.getSize() <= maxSize;
    }
}
