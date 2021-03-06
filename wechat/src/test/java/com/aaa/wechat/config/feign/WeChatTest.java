package com.aaa.wechat.config.feign;

import com.aaa.wechat.api.TestApi;
import com.aaa.wechat.domain.City;
import com.google.common.collect.Maps;
import feign.Feign;
import feign.Request;
import feign.Retryer;
import feign.codec.StringDecoder;
import feign.jackson.JacksonDecoder;
import feign.jackson.JacksonEncoder;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * 原生Feign使用详解 :https://www.jianshu.com/p/89a73e0f05c0
 * @author liuzhen.tian
 * @version 1.0 WeChatTest.java  2020/8/22 16:21
 */
@SpringBootTest
public class WeChatTest {


    @Test
    public void testApi() {

    }
    @Test
    public void testResponse() {

        // options方法指定连接超时时长及响应超时时长，
        // retryer方法指定重试策略
        // target方法绑定接口与服务端地址。返回类型为绑定的接口类型。

        TestApi action = Feign.builder()
               // 解码
               .decoder(new JacksonDecoder())
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
                .decoder(new StringDecoder())
                .options(new Request.Options(10L, TimeUnit.SECONDS, 60L, TimeUnit.SECONDS, true))
                .retryer(new Retryer.Default(5000, 5000, 3))
                .target(TestApi.class,
                        "http://localhost:8070/api/"
                );
        Object response = action.findById(1);
        System.out.println(response);
    }

    @Test
    public void testFindByIdV2() {
        TestApi action = Feign.builder()
                .decoder(new StringDecoder())
                .options(new Request.Options(10L, TimeUnit.SECONDS, 60L, TimeUnit.SECONDS, true))
                .retryer(new Retryer.Default(5000, 5000, 3))
                .target(TestApi.class,
                        "http://localhost:8070/api/"
                );
        Map<String, Integer> queryMap = Maps.newHashMap();
        queryMap.put("id", 4);
        Object response = action.findByIdV2(queryMap);
        System.out.println(response);
    }

    @Test
    public void testFindByMap() {
        TestApi action = Feign.builder()
                .encoder(new JacksonEncoder())
                // 解码编码都是 Jackson  ，springMvc （@RequestBody、@ResponseBody）才能解析
                .decoder(new JacksonDecoder())
                .target(TestApi.class,
                        "http://localhost:8070/"
                );

        Object response = action.findByMap(new City("hz",123));
        System.out.println(response);
    }

    @Test
    public void testFindByMapV2() throws URISyntaxException {
        TestApi action = Feign.builder()
                .encoder(new JacksonEncoder())
                // 解码编码都是 Jackson  ，springMvc （@RequestBody、@ResponseBody）才能解析
                .decoder(new JacksonDecoder())
                .target(TestApi.class,
                        "http://localhost:8070/"
                );
        Map<String, String> headerMap = Maps.newHashMap();
        headerMap.put("token", "123");
        Object response = action.findByMapV2(new URI("http://localhost:8070/api/user/findByMap"),headerMap,new City("hz",123));
        System.out.println(response);
    }
}
