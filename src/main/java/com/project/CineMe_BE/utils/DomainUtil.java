package com.project.CineMe_BE.utils;

import org.springframework.beans.factory.annotation.Value;

package com.project.CineMe_BE.utils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class DomainUtil {

    private static String MINIO_DOMAIN;

    public DomainUtil(@Value("${MINIO_URL}") String domain) {
        MINIO_DOMAIN = domain;
    }

    public static String getMinioDomain() {
        return MINIO_DOMAIN;
    }
}
