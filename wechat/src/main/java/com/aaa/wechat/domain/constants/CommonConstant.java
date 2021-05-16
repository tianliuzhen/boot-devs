package com.aaa.wechat.domain.constants;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @author liuzhen.tian
 * @version 1.0 ApiConstants.java  2020/8/22 16:01
 */
public class CommonConstant {

    public static final ThreadLocal<HttpServletRequest> requestTL = new ThreadLocal<>(); //保存request的threadlocal
    public static final ThreadLocal<HttpServletResponse> responseTL = new ThreadLocal<>(); //保存response的threadlocal
    public static final ThreadLocal<HttpSession> sessionTL = new ThreadLocal<>(); //保存session的threadlocal
    /**
     * 微信接口
     */
    public static final String WECHAT_GET_OPENID = "https://api.weixin.qq.com/";

    public final static String SPACE_SPLIT_STR = " ";
    public final static String SPACE_NULL_STR = "";
    public final static String PERCENT_SPLIT_STR = "%";
    public final static String COMMON_SPLIT_STR = "_";
    public final static String COMMON_DASH_STR = "-";
    public final static String COMMA_SPLIT_STR = ",";
    public final static String SEMICOLON_SPLIT_STR = ";";
    public final static String COMMON_VERTICAL_STR = "|";
    public final static String URL_SPLIT_STR = "/";
    public final static String DOUBLE_SLASH_STR = "//";
    public final static String POUND_SPLIT_STR = "#";
    public final static String COMMON_ESCAPE_STR = "\\";
    public final static String COMMON_AT_STR = "@";
    public final static String COMMON_DOLLAR_STR = "$";
    public final static String COMMON_WAVE_STR = "~";
    public final static String COMMON_STAR_STR = "*";
    public final static String COMMON_COLON_STR = ":";
    public final static String COMMON_DOT_STR = ".";
    public final static String COMMON_EQUAL_STR = "=";
    public final static String COMMON_AND_STR = "&";
    public final static String UP_ARROW_STR = "^";
    public final static String COMMON_BRACKET_LEFT = "(";
    public final static String COMMON_BRACKET_RIGHT = ")";
    public final static String COMMON_SQUARE_BRACKET_LEFT = "[";
    public final static String COMMON_SQUARE_BRACKET_RIGHT = "]";
    public final static String DOUBLE_DASH_STR = "--";
    public final static String COMMON_PLUS_STR = "+";
    public final static String COMMON_QUESTION_MARK = "?";
    public final static String LINE_FEED = "\n";
    public final static String COMMON_TAB = "\t";

    //分隔符
    public static final String SEPARATOR_LINE = System.getProperty("line.separator");
    public static final String SEPARATOR_FILE = System.getProperty("file.separator");

    //开关
    public final static String SWITCH_ON = "on";
    public final static String SWITCH_OFF = "off";
    //通用状态
    public final static int STATUS_YES = 1;
    public final static int STATUS_NO = 0;


    // AES加密
    public final static String YOGA_AES_SKEY = "a6993a8b56f5d376";

    public final static String YOGA_AES_DOOR_SKEY = "e6test2020";

    public final static String YOGA_COOKIE = "sso_yoga_token";
}
