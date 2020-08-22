package com.aaa.wechat.api.fallback;

import com.aaa.wechat.api.WechatSdkRepository;
import org.springframework.stereotype.Component;

/**
 * @author liuzhen.tian
 * @version 1.0 WechatSdkFallBack.java  2020/8/22 16:02
 */
@Component
public class WechatSdkFallBack implements WechatSdkRepository {

    @Override
    public String getOpenid(String appid, String secret, String jsCode, String grantType) {
        return null;
    }
}

