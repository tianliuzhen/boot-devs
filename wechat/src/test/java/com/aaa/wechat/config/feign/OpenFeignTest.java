package com.aaa.wechat.config.feign;

import com.aaa.wechat.api.TestOpenFeignApi;
import com.aaa.wechat.domain.City;
import com.aaa.wechat.fegin.FeignClientFactory;
import com.google.common.collect.Maps;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;
import java.util.Map;

/**
 * 测试 openFeign
 *
 * @author liuzhen.tian
 * @version 1.0 OpenFeignTest.java  2021/7/1 21:16
 */
@SpringBootTest
public class OpenFeignTest {


    public static final String URL = "http://localhost:8080/";

    /**
     * 表单传参 -- 普通参数
     */
    @Test
    public void findById() {
        String str = FeignClientFactory.create(TestOpenFeignApi.class, URL).findByIdAndCode(1, "ali");
        System.out.println(str);
    }

    /**
     * 表单传参 -- pojo
     */
    @Test
    public void findByCity() {
        String str = FeignClientFactory.create(TestOpenFeignApi.class, URL)
                .findByCity(City.builder().cityCode(1001).cityName("杭州").build());
        System.out.println(str);
    }

    /**
     * 表单传参 -- map
     */
    @Test
    public void findByMap() {
        HashMap<Object, Object> map = Maps.newHashMap();
        map.put("cityCode", 1002);
        map.put("cityName", "上海");
        String str = FeignClientFactory.create(TestOpenFeignApi.class, URL)
                .findByMap(map);
        System.out.println(str);
    }

    /**
     * json传参 -- pojo
     */
    @Test
    public void findByJson() {
        City city = City.builder().cityCode(1001).cityName("杭州").build();
        String str = FeignClientFactory.create(TestOpenFeignApi.class, URL)
                .findByJson(city);
        System.out.println(str);
    }

    /**
     * json传参（带 header） -- pojo
     */
    @Test
    public void findByJsonAndHeader() {
        Map<String, String> headerMap = Maps.newHashMap();
        headerMap.put("token", "123");
        headerMap.put("cookie", "456");
        City city = City.builder().cityCode(1001).cityName("杭州").build();

        String str = FeignClientFactory.create(TestOpenFeignApi.class, URL)
                .findByJsonAndHeader(headerMap, city);

        System.out.println(str);
    }
}
