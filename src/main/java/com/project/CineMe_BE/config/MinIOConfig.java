package com.project.CineMe_BE.config;

import io.minio.MinioClient;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@Getter
public class MinIOConfig {
    @Value("${MINIO_URL}")
    private String url;
    @Value("${MINIO_ACCESS_KEY}")
    private String accessKey;
    @Value("${MINIO_SECRET_KEY}")
    private String secretKey;

    @Bean
    public MinioClient minioClient() {
        return MinioClient.builder()
                .endpoint(url)
                .credentials(accessKey, secretKey)
                .build();
    }
}
