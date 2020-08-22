package com.aaa.wechat.api;

import com.aaa.wechat.domain.City;
import feign.Headers;
import feign.Param;
import feign.RequestLine;

/**
 * @author liuzhen.tian
 * @version 1.0 WechatSdkRepository.java  2020/8/22 15:58
 */
public interface TestApi {


    @RequestLine("GET /user/objectResponseDate")
     Object response();


    @RequestLine("GET /user/findById?id={id}")
    Object findById(@Param("id") int id );


    /**
     * 如果 ss.equals("1")那么抛出异常
     *
     * @param city
     * @return
     */
    @RequestLine("POST /user/findByMap")
    @Headers({"Content-Type: application/json","Accept: application/json"})
    Object findByMap(City city);


}
