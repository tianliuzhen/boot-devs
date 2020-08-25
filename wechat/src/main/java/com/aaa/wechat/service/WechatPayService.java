package com.aaa.wechat.service;

/**
 * @author liuzhen.tian
 * @version 1.0 WxPayService.java  2020/8/25 14:57
 */
public interface WechatPayService {
    /**
     * 微信支付接口
     * @param body 商品描述
     * @param outTradeNo  order 的 code
     * @param totalFee 总金额 订单总金额，单位为分，详见支付金额
     * @param openId 用户标识
     * @param ipAddr 终端IP
     * @return
     */
    Object createWXPay(String body, String outTradeNo, Integer totalFee, String openId, String ipAddr);

    /**
     * 微信退款接口
     * @param outTradeNo 商户订单号 order 的 code
     * @param outRefundNo 售后code
     * @param totalFee 订单总金额 单位为分
     * @param refundFee 需要申请的退款金额 单位为分
     */
    void refund(String outTradeNo, String outRefundNo, Integer totalFee, Integer refundFee);



}
