package com.aaa.tlzspringbootstarter.common;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * 映射 yml或者properties 文件
 * <p>
 * 在 META-INF 下重写
 * spring-configuration-metadata.json
 * <p>
 * 重写为了实现 hints：
 * 是类似于Java中的枚举，只不过是给每一个属性的值配置一个说明，
 * 方便使用者在配置的时候能够按照规定的值进行正确配置
 *
 * @author liuzhen.tian
 * @version 1.0 TlzProperties.java  2021/12/4 10:25
 */
@Setter
@Getter
@ConfigurationProperties(prefix = "tlz2")
public class Tlz2Properties {

    /**
     * 是否启用
     */
    private boolean enable;

    /**
     * 小程序id :废弃字段
     */
    @Deprecated
    private String appid2;

    /**
     * 小程序id
     */
    private String appid;

    /**
     * 小程序 secret
     */
    private String secret;


}
