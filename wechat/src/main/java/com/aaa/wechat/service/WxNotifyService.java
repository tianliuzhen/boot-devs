package com.aaa.wechat.service;

/**
 * @author liuzhen.tian
 * @version 1.0 WxNotifyService.java  2020/8/25 14:25
 */
public interface WxNotifyService {
    /**
     * 微信支付回调处理逻辑
     * @param xmlData
     * @return
     */
    String wxParseOrderNotifyResult(String xmlData);
}
