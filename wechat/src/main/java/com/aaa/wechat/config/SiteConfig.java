package com.aaa.wechat.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
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
public class SiteConfig {


    /**
     * 设置微信公众号或者小程序等的appid
     */
    @Value("${wechat.appid}")
    private String appId;

    /**
     * 设置微信公众号或者小程序等的appid
     */
    @Value("${wechat.secret}")
    private String secret;

    /**
     * grantType = authorization_code (获取openId)
     */
    @Value("${wechat.grant_type}")
    private String grantType;

    /**
     * grantType =  client_credential   (获取token)
     */
    @Value("${wechat.grant_type_2}")
    private String grantType2;

    /**
     * 退款通知回调url
     */
    @Value("${wx.wxNotifyUrl.refund}")
    private String wxRefundNotifyUrl;

    /**
     * 付款通知回调url
     */
    @Value("${wx.wxNotifyUrl.pay}")
    private String wxPayNotifyUrl;


}
