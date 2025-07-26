package com.project.CineMe_BE.config;

import jakarta.servlet.http.HttpServletRequest;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.stream.Collectors;

@Configuration
@Getter
public class VNPAYConfig {
    @Value("${VN_PAY_URL}")
    public String vnp_PayUrl;
    @Value("${VN_PAY_RETURN_URL}")
    public String vnp_ReturnUrl;
    @Value("${VN_PAY_TMN_CODE}")
    public String vnp_TmnCode;
    @Value("${VN_PAY_SECRET_KEY}")
    public String secretKey;
    @Value("${VN_PAY_VERSION}")
    public String vnp_Version;
    @Value("${VN_PAY_COMMAND}")
    public String vnp_Command;
    @Value("${VN_PAY_ORDER_TYPE}")
    public String orderType;
    public Map<String, String> getVNPayConfig() {
        Map<String, String> vnpParamsMap = new HashMap<>();
        vnpParamsMap.put("vnp_Version", this.vnp_Version);
        vnpParamsMap.put("vnp_Command", this.vnp_Command);
        vnpParamsMap.put("vnp_TmnCode", this.vnp_TmnCode);
        vnpParamsMap.put("vnp_CurrCode", "VND");
        vnpParamsMap.put("vnp_TxnRef",  getRandomNumber(8));
        vnpParamsMap.put("vnp_OrderType", this.orderType);
        vnpParamsMap.put("vnp_Locale", "vn");
        vnpParamsMap.put("vnp_ReturnUrl", this.vnp_ReturnUrl);
        LocalDateTime localDateTime = LocalDateTime.now(ZoneId.of("Asia/Ho_Chi_Minh"));
        String vnpCreateDate = localDateTime.format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss"));
        vnpParamsMap.put("vnp_ReturnUrl", vnp_ReturnUrl + "/VnPayReturn");
        vnpParamsMap.put("vnp_CreateDate", vnpCreateDate);
        String vnp_ExpireDate = localDateTime.plusMinutes(10).format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss"));
        vnpParamsMap.put("vnp_ExpireDate", vnp_ExpireDate);
        return vnpParamsMap;
    }

    public String getRandomNumber(int len) {
        Random rnd = new Random();
        String chars = "0123456789";
        StringBuilder sb = new StringBuilder(len);
        for (int i = 0; i < len; i++) {
            sb.append(chars.charAt(rnd.nextInt(chars.length())));
        }
        return sb.toString();
    }
    public String getPaymentURL(Map<String, String> paramsMap, boolean encodeKey) {
        return paramsMap.entrySet().stream()
                .filter(entry -> entry.getValue() != null && !entry.getValue().isEmpty())
                .sorted(Map.Entry.comparingByKey())
                .map(entry ->
                        (encodeKey ? URLEncoder.encode(entry.getKey(),
                                StandardCharsets.US_ASCII)
                                : entry.getKey()) + "=" +
                                URLEncoder.encode(entry.getValue()
                                        , StandardCharsets.US_ASCII))
                .collect(Collectors.joining("&"));
    }

    public String hmacSHA512(final String key, final String data) {
        try {
            if (key == null || data == null) {
                throw new NullPointerException();
            }
            final Mac hmac512 = Mac.getInstance("HmacSHA512");
            byte[] hmacKeyBytes = key.getBytes();
            final SecretKeySpec secretKey = new SecretKeySpec(hmacKeyBytes, "HmacSHA512");
            hmac512.init(secretKey);
            byte[] dataBytes = data.getBytes(StandardCharsets.UTF_8);
            byte[] result = hmac512.doFinal(dataBytes);
            StringBuilder sb = new StringBuilder(2 * result.length);
            for (byte b : result) {
                sb.append(String.format("%02x", b & 0xff));
            }
            return sb.toString();

        } catch (Exception ex) {
            return "";
        }
    }

    public static String getIpAddress(HttpServletRequest request) {
        String ipAdress = "";
        try {
            ipAdress = request.getHeader("X-FORWARDED-FOR");
            if (ipAdress == null) {
                ipAdress = request.getRemoteAddr();
            }
        } catch (Exception e) {
            ipAdress = "Invalid IP:" + e.getMessage();
        }
        return ipAdress;
    }

}
