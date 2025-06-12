package com.project.CineMe_BE.utils;

public class StringUtil {

        public static String splitUrlResource(String url) {
            if (url == null || url.isEmpty()) {
                return "";
            }
            return url.substring(url.indexOf("resource"), url.indexOf("?"));
        }

}
