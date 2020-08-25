package com.aaa.wechat.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

/**
 * Create date 2017/5/24
 * <p>系统配置</p>
 *
 * @author Males
 * @since 1.0.0
 */
@Setter
@Getter
@Component
@ComponentScan(basePackages = {"com.aaa"})
public class SiteConfig {


    @Value("${wechat.appid}")
    private String appid;

    @Value("${wechat.secret}")
    private String secret;

    @Value("${wechat.grant_type}")
    private String grantType;

    @Value("${wx.wxNotifyUrl.notify}")
    private String wxRefundNotifyUrl;

}
