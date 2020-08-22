package com.aaa.wechat.api;

import feign.Param;
import feign.RequestLine;

/**
 * @author liuzhen.tian
 * @version 1.0 WechatSdkRepository.java  2020/8/22 15:58
 */
public interface WechatSdkApi {

    /**
     * 表单传参
     */
    @RequestLine("GET /sns/jscode2session?appid={appid}&secret={secret}&js_code={jsCode}&grant_type={grantType}")
    String findById(@Param("appid") String appid,
                    @Param("secret") String secret,
                    @Param("js_code") String jsCode,
                    @Param("grant_type") String grantType);



}
