package com.aaa.wechat.api;

import com.aaa.wechat.domain.City;
import feign.*;

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
    @RequestLine("GET /objectResponseDate")
    String response();

    /**
     * 表单传参 * V2
     */
    @RequestLine("GET /findById?id={idss}")
    String findById(@Param("idss") int id);


    /**
     * 表单传参 * V2
     */
    @RequestLine("GET /findById")
    String findByIdV2(@QueryMap Map<String, Integer> queryMap);

    /**
     * json 传参 * V1
     *
     * @param city （json格式）
     */
    @RequestLine("POST /user/findByMap")
    @Headers({"Content-Type: application/json", "Accept: application/json"})
    String findByMap(City city);

    /**
     * json 传参 * V2
     *
     * @param baseUri   URI
     * @param headerMap header
     * @param city      城市 pojo （json格式）
     */
    @RequestLine("POST")
    @Headers({"Content-Type: application/json", "Accept: application/json"})
    String findByMapV2(URI baseUri, @HeaderMap Map<String, String> headerMap, City city);


}
