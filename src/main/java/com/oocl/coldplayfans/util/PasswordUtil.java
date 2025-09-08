package com.oocl.coldplayfans.util;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

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

    public static String encryptMD5WithSalt(String password, String salt) {
        if (salt == null || salt.isEmpty()) {
            return encryptMD5(password);
        }
        return encryptMD5(password + ":" + salt);
    }
}
    