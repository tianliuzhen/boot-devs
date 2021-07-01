package com.aaa.wechat.api;

import com.aaa.wechat.domain.WxToken;
import feign.Param;
import feign.RequestLine;

/**
 * @author liuzhen.tian
 * @version 1.0 WechatSdkRepository.java  2020/8/22 15:58
 */
public interface WechatSdkApi {

    /**
     * 表单传参
     * jscode2session
     */
    @RequestLine("GET /sns/jscode2session?appid={appid}&secret={secret}&js_code={js_code}&grant_type={grant_type}")
    String jscode2session(@Param("appid") String appid,
                          @Param("secret") String secret,
                          @Param("js_code") String jsCode,
                          @Param("grant_type") String grantType);


    @RequestLine("GET /cgi-bin/token?appid={appid}&secret={secret}&grant_type={grant_type}")
    WxToken getToken(@Param("appid") String appid,
                     @Param("secret") String secret,
                     @Param("grant_type") String grantType);
}
