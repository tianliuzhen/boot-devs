package com.aaa.wechat.config;

import com.github.binarywang.wxpay.config.WxPayConfig;
import com.github.binarywang.wxpay.service.WxPayService;
import com.github.binarywang.wxpay.service.impl.WxPayServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author liuzhen.tian
 * @version 1.0 WxPayConfiguration.java  2020/8/25 14:27
 */
@Configuration
@ConditionalOnClass(WxPayService.class)
//@EnableConfigurationProperties(JXWxPayProperties.class)
public class WxPayConfiguration {

    @Autowired
    private WxPayProperties payProperties;


    @Bean(name = "jxWxService")
    @ConditionalOnMissingBean(name = "jxWxService")
    public WxPayService jxWxService() {
        WxPayConfig payConfig = new WxPayConfig();
        payConfig.setAppId(StringUtils.trimToNull(this.payProperties.getAppId()));
        payConfig.setMchId(StringUtils.trimToNull(this.payProperties.getMchId()));
        payConfig.setMchKey(StringUtils.trimToNull(this.payProperties.getMchKey()));
        // payConfig.setSubAppId(StringUtils.trimToNull(this.jxProperties.getSubAppId()));
        // payConfig.setSubMchId(StringUtils.trimToNull(this.jxProperties.getSubMchId()));
        payConfig.setKeyPath(StringUtils.trimToNull(this.payProperties.getKeyPath()));

        // 可以指定是否使用沙箱环境
        payConfig.setUseSandboxEnv(false);

        WxPayService wxPayService = new WxPayServiceImpl();
        wxPayService.setConfig(payConfig);
        return wxPayService;
    }



}

