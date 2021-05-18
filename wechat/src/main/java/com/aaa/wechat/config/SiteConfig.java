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
