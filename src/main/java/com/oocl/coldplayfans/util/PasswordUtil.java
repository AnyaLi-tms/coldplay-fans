package com.oocl.coldplayfans.util;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;

public class PasswordUtil {


    private static String encryptMD5(String password) {
        if (password == null || password.isEmpty()) {
            return null;
        }

        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] messageDigest = md.digest(password.getBytes());
            BigInteger no = new BigInteger(1, messageDigest);
            String md5Str = no.toString(16);
            while (md5Str.length() < 32) {
                md5Str = "0" + md5Str;
            }
            return md5Str;
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String generateSalt() {
        // 1. 创建加密级随机数生成器
        SecureRandom secureRandom = new SecureRandom();

        // 2. 生成16字节（128位）的随机字节数组（长度可调整，建议16-32字节）
        byte[] saltBytes = new byte[16];
        secureRandom.nextBytes(saltBytes);

        // 3. 转为Base64字符串（方便存储到数据库，避免乱码）
        return Base64.getEncoder().encodeToString(saltBytes);
    }

    public static String encryptMD5WithSalt(String password, String salt) {
        if (salt == null || salt.isEmpty()) {
            return encryptMD5(password);
        }
        return encryptMD5(password + ":" + salt);
    }
}
    