package com.aaa.wechat.fegin;

import com.alibaba.fastjson.JSON;
import feign.RequestTemplate;
import feign.codec.Encoder;

import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;

/**
 * openFeign 自定义编码器
 *
 * @author liuzhen.tian
 * @version 1.0 FeignEncoder.java  2021/7/1 21:07
 */
public class FeignEncoder implements Encoder {

    @Override
    public void encode(Object object, Type bodyType, RequestTemplate template) {
        if (object != null) {
            String jsonString = JSON.toJSONString(object);
            template.body(jsonString.getBytes(), StandardCharsets.UTF_8);
        }
    }
}
