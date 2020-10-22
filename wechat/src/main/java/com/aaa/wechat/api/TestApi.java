package com.aaa.wechat.api;

import com.aaa.wechat.domain.City;
import feign.*;
import org.springframework.web.bind.annotation.RequestBody;

import java.net.URI;
import java.util.Map;

/**
 * @author liuzhen.tian
 * @version 1.0 WechatSdkRepository.java  2020/8/22 15:58
 */
public interface TestApi {

    /**
     * 无参
     */
    @RequestLine("GET /user/objectResponseDate")
     Object response();
    /**
     * 表单传参
     */
    @RequestLine("GET /user/findById?id={idss}")
    String findById(@Param("idss") int id );


    /**
     * 表单传参V2
     */
    @RequestLine("GET /user/findById")
    String findByIdV2(@QueryMap Map<String, Integer> queryMap);

    /**
     * 传 json
     * @param city
     * @return
     */
    @RequestLine("POST /user/findByMap")
    @Headers({"Content-Type: application/json","Accept: application/json"})
    Object findByMap(City city);

    @RequestLine("POST")
    @Headers({"Content-Type: application/json","Accept: application/json"})
    Object findByMapV2(URI baseUri, @HeaderMap Map<String,String> headerMap, @RequestBody City city) ;


}
