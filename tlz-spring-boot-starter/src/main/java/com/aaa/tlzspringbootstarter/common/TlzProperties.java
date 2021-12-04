package com.aaa.tlzspringbootstarter.common;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * 映射 yml或者properties 文件
 *
 * @author liuzhen.tian
 * @version 1.0 TlzProperties.java  2021/12/4 10:25
 */
@Setter
@Getter
@ConfigurationProperties(prefix = "tlz")
public class TlzProperties {
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
