package com.aaa.wechat.web;

import com.aaa.wechat.domain.DecryptSpec;
import com.aaa.wechat.service.WeChatService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;

/**
 * @author liuzhen.tian
 * @version 1.0 UserWechatController.java  2020/8/22 15:52
 */
@RestController
@RequestMapping(value = "/wechat")
public class UserWeChatController {

    @Resource
    private WeChatService weChatService;


    @PostMapping(value = "/getToken")
    public ApiResult getToken(@RequestBody HashMap<String,String> map) {
        weChatService.getToken(map.get("jcode"));
        return null;
    }

    /**
     * 接口解密
     * @param decryptSpec
     * @return
     */
    @PostMapping(value = "/decrypt")
    public ApiResult decrypt(@RequestBody DecryptSpec decryptSpec) {
        weChatService.decrypt(decryptSpec);
        return null;
    }

}
