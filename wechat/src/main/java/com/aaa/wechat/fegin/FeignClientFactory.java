package com.aaa.wechat.fegin;

import feign.Feign;
import feign.Logger;
import feign.Retryer;
import feign.codec.StringDecoder;
import feign.jackson.JacksonDecoder;

/**
 * @author liuzhen.tian
 * @version 1.0 FeignClientFactory.java  2021/7/1 21:12
 */
public abstract class FeignClientFactory {
    public static <T> T create(Class<T> clazz, String url) {
        return Feign.builder()
                .logger(new Logger.ErrorLogger()).logLevel(Logger.Level.FULL)
                .retryer(Retryer.NEVER_RETRY)
                .encoder(new FeignEncoder())
                // .queryMapEncoder(new BeanQueryMapEncoder())

                // 使用StreamDecoder做解码
                .decoder(new StringDecoder())
                .decode404() // 把404也解码 -> 这样就不会以一场形式抛出，中断程序喽，方便我测试嘛
                .target(clazz, url);
    }

    public static <T> T create2(Class<T> clazz, String url) {
        return Feign.builder()
                .logger(new Logger.ErrorLogger()).logLevel(Logger.Level.FULL)
                .retryer(Retryer.NEVER_RETRY)
                .encoder(new FeignEncoder())

                // 使用StreamDecoder做解码
                .decoder(new JacksonDecoder())
                .decode404() // 把404也解码 -> 这样就不会以一场形式抛出，中断程序喽，方便我测试嘛
                .target(clazz, url);
    }
}
