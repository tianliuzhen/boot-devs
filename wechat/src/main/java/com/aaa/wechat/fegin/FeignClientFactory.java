package com.aaa.wechat.fegin;

import feign.Feign;
import feign.Logger;
import feign.Retryer;
import feign.codec.StringDecoder;
import feign.jackson.JacksonDecoder;
import feign.okhttp.OkHttpClient;
import okhttp3.ConnectionPool;

import java.util.concurrent.TimeUnit;

/**
 * @author liuzhen.tian
 * @version 1.0 FeignClientFactory.java  2021/7/1 21:12
 */
public abstract class FeignClientFactory {


    /**
     * 创建 feign 客户端
     *
     * @param clazz class
     * @param url   url
     * @param <T>   泛型参数
     * @return T
     */
    public static <T> T create(Class<T> clazz, String url) {

        // 定义 okHttpClient
        okhttp3.OkHttpClient okHttpClient = new okhttp3.OkHttpClient()
                .newBuilder()
                .connectionPool(new ConnectionPool(5, 5L, TimeUnit.MINUTES))
                .connectTimeout(15, TimeUnit.SECONDS)
                .writeTimeout(20, TimeUnit.SECONDS)
                .readTimeout(20, TimeUnit.SECONDS)
                .build();

        // 定义 feign 对应 OkHttpClient
        OkHttpClient client = new OkHttpClient(okHttpClient);

        // 创建 feign
        return Feign.builder()
                .client(client)
                .logger(new Logger.ErrorLogger()).logLevel(Logger.Level.FULL)
                .retryer(Retryer.NEVER_RETRY) // 重试机制
                .encoder(new FeignEncoder())  // 自定义：入参编码器
                .decoder(new StringDecoder()) // 定义：出参解码器
                .decode404() // 解码404，方便测试
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
