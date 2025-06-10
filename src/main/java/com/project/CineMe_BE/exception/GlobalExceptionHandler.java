package com.project.CineMe_BE.exception;

import com.project.CineMe_BE.dto.APIResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(DataNotFoundException.class)
    public ResponseEntity<APIResponse> handleDataNotFoundException(DataNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(APIResponse.builder()
                        .statusCode(404)
                        .message(ex.getMessage())
                        .build());
    }
}
