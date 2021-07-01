package com.aaa.wechat.api;

import com.aaa.wechat.domain.City;
import feign.*;

import java.util.Map;

/**
 * @author liuzhen.tian
 * @version 1.0 OpenFeignApi.java  2021/7/1 21:20
 */
public interface TestOpenFeignApi {

    /**
     * 表单传参 * V1
     * 参数必须要加 @Param注解，否则会报错
     *
     * @param id   id
     * @param code code
     */
    @RequestLine("POST openFeignApi/findByIdAndCode?id={id_}&code={code_}")
    String findByIdAndCode(@Param("id_") Integer id, @Param("code_") String code);

    /**
     * 表单传参 (pojo) * V2
     * 负责将对象编码为Map查询参数名到值的映射。
     *
     * @param city pojo
     */
    @RequestLine("GET openFeignApi/findByCity")
    String findByCity(@QueryMap City city);

    /**
     * 表单传参 (map) * V3
     * 负责将对象编码为Map查询参数名到值的映射。
     *
     * @param map map
     */
    @RequestLine("GET openFeignApi/findByMap")
    String findByMap(@QueryMap Map map);


    /**
     * json 传参 * V1
     *
     * @param city （json格式）
     */
    @RequestLine("POST /openFeignApi/findByJson")
    @Headers({"Content-Type: application/json", "Accept: application/json"})
    String findByJson(City city);

    /**
     * json 传参 （& header传参） * V2
     *
     * @param city （json格式）
     */
    @RequestLine("POST /openFeignApi/findByJson")
    @Headers({"Content-Type: application/json","Accept: application/json"})
    String findByJsonAndHeader(@HeaderMap Map<String, String> headerMap, City city) ;

}
