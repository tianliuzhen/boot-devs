package com.aaa.springsecurity;

import com.aaa.springsecurity.config.secrity.util.SecurityUtils;

/**
 * @author liuzhen.tian
 * @version 1.0 PasswordTest.java  2025/2/8 21:32
 */
public class PasswordTest {
    public static void main(String[] args) {
        System.out.println( SecurityUtils.encryptPassword("aaa111"));
    }
}
