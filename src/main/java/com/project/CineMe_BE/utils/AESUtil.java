package com.project.CineMe_BE.utils;

import io.jsonwebtoken.io.Decoders;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;

@Component
@Setter
public class AESUtil {
    private static String SECRET_KEY;

    @Value("${JWT_SECRET_KEY}")
    public void setSecretKey(String key) {
        AESUtil.SECRET_KEY = key;
    }

    public static String encrypt(String plainText) throws Exception {
        SecretKeySpec key = new SecretKeySpec(getKeyBytes(), "AES");
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.ENCRYPT_MODE, key);
        byte[] encrypted = cipher.doFinal(plainText.getBytes());
        return Base64.getEncoder().encodeToString(encrypted);
    }

    public static String decrypt(String encryptedText) throws Exception {
        SecretKeySpec key = new SecretKeySpec(getKeyBytes(), "AES");
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.DECRYPT_MODE, key);
        byte[] decrypted = cipher.doFinal(Base64.getDecoder().decode(encryptedText));
        return new String(decrypted);
    }

    private static byte[] getKeyBytes() {
        return Decoders.BASE64.decode(SECRET_KEY);

    }
}
