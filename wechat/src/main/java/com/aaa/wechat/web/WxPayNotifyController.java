package com.aaa.wechat.web;

import com.aaa.wechat.service.WxNotifyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 微信回调接口
 * @author liuzhen.tian
 * @version 1.0 WxPayNotifyController.java  2020/8/25 14:14
 */
@RestController
@RequestMapping("/wx-notify")
public class WxPayNotifyController {
    @Autowired
    private WxNotifyService wxNotifyService;

    @PostMapping("/notify")
    public String wxParseOrderNotifyResult(@RequestBody String xmlData){
        return wxNotifyService.wxParseOrderNotifyResult(xmlData);
    }
}
