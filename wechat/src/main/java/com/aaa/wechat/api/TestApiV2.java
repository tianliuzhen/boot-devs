package com.aaa.wechat.api;

import com.aaa.wechat.domain.City;
import feign.*;
import feign.codec.StringDecoder;
import feign.jackson.JacksonDecoder;
import feign.jackson.JacksonEncoder;
import org.springframework.web.bind.annotation.RequestBody;

import java.net.URI;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @author liuzhen.tian
 * @version 1.0 WechatSdkRepository.java  2020/8/22 15:58
 */
public interface TestApiV2 {

    static TestApiV2 connect(){
        TestApiV2 target = Feign.builder()
                .encoder(new JacksonEncoder())
                .decoder(new JacksonDecoder())
                .target(TestApiV2.class, "http://localhost:8070/api/");
        return target;
    }
    static TestApiV2 connectV2(){
        TestApiV2 target = Feign.builder()
                .decoder(new StringDecoder())
                .options(new Request.Options(10L, TimeUnit.SECONDS, 60L, TimeUnit.SECONDS, true))
                .retryer(new Retryer.Default(5000, 5000, 3))
                .target(TestApiV2.class, "http://localhost:8070/api/");
        return target;
    }

    /**
     * 无参
     */
    @RequestLine("GET /user/objectResponseDate")
     Object response();
    /**
     * 表单传参
     */
    @RequestLine("GET /user/findById?id={idss}")
    String findById(@Param("idss") int id);


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
    Object findByMapV2(URI baseUri, @HeaderMap Map<String, String> headerMap, @RequestBody City city) ;

    @RequestLine("POST /user/findByMap")
    @Headers({"Content-Type: application/json","Accept: application/json"})
    Object findByMapV3(@HeaderMap Map<String, String> headerMap, @RequestBody City city) ;





}
