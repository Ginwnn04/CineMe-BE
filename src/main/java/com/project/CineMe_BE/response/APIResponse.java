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
    private boolean sucess = true;
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
                          .sucess(true)
                          .result(result)
                          .build();
    }

    /**
     * Generate APIResponse with code 200
     * @param result
     * @param message
     * @return
     */
    public static APIResponse ok(Object result, String message) {
        return APIResponse.builder()
                          .code(200)
                          .sucess(true)
                          .result(result)
                          .message(message)
                          .build();
    }
}
