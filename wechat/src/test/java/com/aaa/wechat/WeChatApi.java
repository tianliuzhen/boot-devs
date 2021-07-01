package com.aaa.wechat;

import com.aaa.wechat.api.WechatSdkApi;
import com.aaa.wechat.config.SiteConfig;
import com.aaa.wechat.domain.WxToken;
import com.aaa.wechat.domain.constants.ApiConstants;
import com.aaa.wechat.fegin.FeignClientFactory;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author liuzhen.tian
 * @version 1.0 WeChatApi.java  2021/7/1 22:23
 */
@SpringBootTest
public class WeChatApi {
    @Autowired
    private SiteConfig siteConfig;

    /**
     * 根据 appid 和 secret 及 client_credential  获取 token
     */
    @Test
    public void getToken() {
        WechatSdkApi sdkApi = FeignClientFactory.create2(WechatSdkApi.class, ApiConstants.WECHAT_GET_OPENID);
        WxToken apiToken = sdkApi.getToken(siteConfig.getAppId(), siteConfig.getSecret(), siteConfig.getGrantType2());
        System.out.println(apiToken.getAccessToken());

    }
}
