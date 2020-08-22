package com.aaa.wechat.api;

import feign.Headers;
import feign.Param;
import feign.RequestLine;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author liuzhen.tian
 * @version 1.0 WechatSdkRepository.java  2020/8/22 15:58
 */
public interface TestApi {


    @RequestLine("GET /user/objectResponseDate")
     Object response();


    @RequestLine("GET /user/findById")
    Object findById(@Param("id") int id );


    /**
     * 如果 ss.equals("1")那么抛出异常
     *
     * @param json
     * @return
     */
    @RequestLine("POST /user/findByMap")
    @Headers({"Content-Type: application/json", "Accept: application/json"})
    Object findByMap(String json);


}
