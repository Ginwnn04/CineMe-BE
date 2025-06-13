package com.project.CineMe_BE.utils;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class StringUtil {
    @Value("${MINIO_PUBLIC_URL}")
    private String domain;
    public static String MINIO_DOMAIN;
    @PostConstruct
    public void init() {
        MINIO_DOMAIN = domain;
    }

    public static String splitUrlResource(String url) {
            if (url == null || url.isEmpty()) {
                return "";
            }
            return url.substring(url.indexOf("resource"), url.indexOf("?"));
        }

    public static String mapTrailer(String trailer) {
        if (trailer == null || trailer.isEmpty()) {
            return "";
        }
        else if (trailer.indexOf("resources") == -1) {
            return "https://www.youtube.com/watch?v=" + trailer;
        }
        return MINIO_DOMAIN + "/" + trailer;
    }

    public static String mapImg(String img) {
        if (img == null || img.isEmpty()) {
            return "";
        }
        else if (img.indexOf("resources") == -1) {
            return img;
        }
        return MINIO_DOMAIN + "/" + img;
    }

}
