package com.aaa.wechat.config.feign;

import com.aaa.wechat.api.TestApiV2;
import com.aaa.wechat.domain.City;
import com.google.common.collect.Maps;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Map;

/**
 * 原生Feign使用详解 :https://www.jianshu.com/p/89a73e0f05c0
 * @author liuzhen.tian
 * @version 1.0 WeChatTest.java  2020/8/22 16:21
 */
@SpringBootTest
public class WeChatTestV2 {


    @Test
    public void testFindById() {
        Map<String, String> headerMap = Maps.newHashMap();
        headerMap.put("token", "123");
        Object hz = TestApiV2.connect().findByMapV3(headerMap,new City("hz", 123));
        System.out.println(hz);
    }
    @Test
    public void testFindByMap() {
        String byId = TestApiV2.connectV2().findById(1);
        System.out.println(byId);
    }
}
