package com.project.CineMe_BE.response;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class APIResponse {
    @Builder.Default
    private boolean success = true;
    private Integer code;
    private String message;
    private Object result;

    /**
     * Generate APIResponse with code 200
     * @param result
     * @return
     */
    public static APIResponse ok(Object result) {
        return APIResponse.builder()
                          .code(200)
                          .success(true)
                          .result(result)
                          .build();
    }
}
