package com.aaa.springsecurity.config.secrity.model;

/**
 * @author liuzhen.tian
 * @version 1.0 Constants.java  2023/11/6 22:37
 */
public class Constants {

    /**
     * 令牌
     */
    public static final String TOKEN = "token";

    /**
     * 令牌前缀
     */
    public static final String TOKEN_PREFIX = "Bearer ";

    /**
     * 令牌前缀
     */
    public static final String LOGIN_USER_KEY = "login_user_key";
    /**
     * 登录用户 redis key
     */
    public static final String LOGIN_TOKEN_KEY = "login_tokens:";

}
