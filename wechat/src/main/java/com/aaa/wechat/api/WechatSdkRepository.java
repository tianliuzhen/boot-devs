package com.aaa.wechat.api;

import com.aaa.wechat.api.fallback.WechatSdkFallBack;
import com.aaa.wechat.domain.constants.ApiConstants;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author liuzhen.tian
 * @version 1.0 WechatSdkRepository.java  2020/8/22 15:58
 */
@FeignClient(name = "WechatSDK", url = ApiConstants.WECHAT_GET_OPENID, fallback = WechatSdkFallBack.class)
@Repository
public interface WechatSdkRepository {

    @GetMapping(value = "/sns/jscode2session")
    String getOpenid(@RequestParam("appid") String appid,
                     @RequestParam("secret") String secret,
                     @RequestParam("js_code") String jsCode,
                     @RequestParam("grant_type") String grantType);

}
