package com.aaa.wechat.service.impl;

import com.aaa.wechat.config.SiteConfig;
import com.aaa.wechat.service.WechatPayService;
import com.aaa.wechat.utils.CommonUtils;
import com.github.binarywang.wxpay.bean.request.WxPayRefundRequest;
import com.github.binarywang.wxpay.bean.request.WxPayUnifiedOrderRequest;
import com.github.binarywang.wxpay.bean.result.WxPayOrderQueryResult;
import com.github.binarywang.wxpay.exception.WxPayException;
import com.github.binarywang.wxpay.service.WxPayService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author liuzhen.tian
 * @version 1.0 WxPayServiceImpl.java  2020/8/25 14:57
 */
@Slf4j
@Service
public class WxPayServiceImpl implements WechatPayService {

    @Autowired
    private SiteConfig siteConfig;

    @Autowired
    private WxPayService jxWxService;

    /**
     *  交易类型  JSAPI -JSAPI支付
     *  参考微信官方：https://pay.weixin.qq.com/wiki/doc/api/jsapi.php?chapter=7_1
     */
    private static final String TRADE_TYPE = "JSAPI";

    @Override
    public Object createWXPay(String body, String outTradeNo, Integer totalFee, String openId, String ipAddr) {

        //1. 封装request对象
        WxPayUnifiedOrderRequest request = new WxPayUnifiedOrderRequest();
        request.setNonceStr(CommonUtils.getRandomStringByLength(32));
        request.setBody(CommonUtils.cutString(body,127));
        request.setOutTradeNo(outTradeNo);
        request.setTotalFee(totalFee);
        request.setSpbillCreateIp(ipAddr);
        request.setNotifyUrl(siteConfig.getWxRefundNotifyUrl());
        request.setTradeType(TRADE_TYPE);
        request.setOpenid(openId);
        Object order = null;
        try {
            order = jxWxService.createOrder(request);


        } catch (WxPayException e) {
            log.error("WXPay ERR:{}" ,"微信支付异常");
            e.printStackTrace();

        }
        return order;
    }

    @Override
    public void refund(String outTradeNo, String outRefundNo, Integer totalFee, Integer refundFee) {
        WxPayRefundRequest request = new WxPayRefundRequest();
        //  transaction_id   和  out_trade_no  二选一即可
        // request.setTransactionId("");
        request.setOutTradeNo(outTradeNo);
        request.setOutRefundNo(outRefundNo);
        request.setTotalFee(totalFee);
        request.setRefundFee(refundFee);
        try {
            jxWxService.refund(request);
        } catch (WxPayException e) {
            log.error("WXPay ERR:{}" ,"微信退款异常");
            e.printStackTrace();
        }
    }

    public void queryOrder(){
        WxPayOrderQueryResult wxPayOrderQueryResult;
        try {
            /**
             * 这里官方还是要求二选一 ，这里提供的api，两个参数都要填。
             * 那就都填把
             * transaction_id
             * out_trade_no
             */
            wxPayOrderQueryResult = jxWxService.queryOrder("","");
        } catch (WxPayException e) {
            e.printStackTrace();
        }
    }
}
