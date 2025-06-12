package com.project.CineMe_BE.utils;

import org.springframework.beans.factory.annotation.Value;

public class DomainUtil {
    @Value("${MINIO_URL}")
    public static String MINIO_DOMAIN;


}
