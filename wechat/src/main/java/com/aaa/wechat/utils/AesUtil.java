package com.aaa.wechat.utils;

import org.apache.tomcat.util.codec.binary.Base64;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.AlgorithmParameterSpec;
import java.util.Arrays;

/**
 * 微信aes解密
 * @author liuzhen.tian
 * @version 1.0 AesUtil.java  2020/8/22 15:56
 */
public class AesUtil {
    public static String decryptWXAppletInfo(String sessionKey, String encryptedData, String iv)
            throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException,
            InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException {
        String result = null;
        byte[] encrypData = Base64.decodeBase64(encryptedData);
        byte[] ivData = Base64.decodeBase64(iv);
        byte[] sessionKeyB = Base64.decodeBase64(sessionKey);
        // 如果密钥不足16位，那么就补足

        int base = 16;
        if (sessionKeyB.length % base != 0) {
            int groups = sessionKeyB.length / base + (sessionKeyB.length % base != 0 ? 1 : 0);
            byte[] temp = new byte[groups * base];
            Arrays.fill(temp, (byte) 0);
            System.arraycopy(sessionKeyB, 0, temp, 0, sessionKeyB.length);
            sessionKeyB = temp;
        }

        AlgorithmParameterSpec ivSpec = new IvParameterSpec(ivData);
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        SecretKeySpec keySpec = new SecretKeySpec(sessionKeyB, "AES");
        cipher.init(Cipher.DECRYPT_MODE, keySpec, ivSpec);
        byte[] doFinal = cipher.doFinal(encrypData);
        result = new String(doFinal);
        /*
        <dependency>
            <groupId>commons-codec</groupId>
            <artifactId>commons-codec</artifactId>
            <version>1.9</version>
        </dependency>
        <dependency>
            <groupId>org.bouncycastle</groupId>
            <artifactId>bcprov-jdk16</artifactId>
            <version>1.46</version>
        </dependency>
        */
        return result;
    }
}
