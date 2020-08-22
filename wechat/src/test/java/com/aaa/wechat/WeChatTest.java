package com.aaa.wechat;

import com.aaa.wechat.api.TestApi;
import com.aaa.wechat.domain.DecryptSpec;
import com.aaa.wechat.service.WeChatService;
import com.alibaba.fastjson.JSON;
import feign.Feign;
import feign.codec.StringDecoder;
import feign.gson.GsonDecoder;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * @author liuzhen.tian
 * @version 1.0 WeChatTest.java  2020/8/22 16:21
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class WeChatTest {

    @Resource
    private WeChatService weChatService;


    @Test
    public void testApi() {
    }
    @Test
    public void testResponse() {
        TestApi action = Feign.builder()
               .decoder(new GsonDecoder())
               //  .decoder(new StringDecoder())
                .target(TestApi.class,
                        "http://localhost:8070/"
                );
        Object response = action.response();
        System.out.println(response);
    }
    @Test
    public void testFindById() {
        TestApi action = Feign.builder()
                .decoder(new GsonDecoder())
                //  .decoder(new StringDecoder())
                .target(TestApi.class,
                        "http://localhost:8070/"
                );
        Object response = action.findById(1);
        System.out.println(response);
    }
    @Test
    public void testFindByMap() {
        TestApi action = Feign.builder()
                .decoder(new GsonDecoder())
                //  .decoder(new StringDecoder())
                .target(TestApi.class,
                        "http://localhost:8070/"
                );
        Map map = new HashMap();
        map.put("name", "123");
        Object response = action.findByMap(JSON.toJSONString(map));
        System.out.println(response);
    }
}
