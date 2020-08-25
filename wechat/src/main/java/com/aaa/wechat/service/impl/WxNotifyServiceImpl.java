package com.aaa.wechat.service.impl;

import com.aaa.wechat.service.WxNotifyService;
import com.github.binarywang.wxpay.bean.notify.WxPayNotifyResponse;
import com.github.binarywang.wxpay.bean.notify.WxPayOrderNotifyResult;
import com.github.binarywang.wxpay.exception.WxPayException;
import com.github.binarywang.wxpay.service.WxPayService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

/**
 * @author liuzhen.tian
 * @version 1.0 WxNotifyServiceImpl.java  2020/8/25 14:26
 */
@Slf4j
@Service
public class WxNotifyServiceImpl implements WxNotifyService {

    @Autowired
    private WxPayService jxWxService;


    @Transactional(rollbackFor = Exception.class)
    @Override
    public String wxParseOrderNotifyResult(String xmlData) {
        log.info("微信支付回调内容:{}" , xmlData);

        final WxPayOrderNotifyResult notifyResult;
        try {
            notifyResult = jxWxService.parseOrderNotifyResult(xmlData);

            //本地后台生成的订单号
            String outTradeNo = notifyResult.getOutTradeNo().split("_")[0];
            //付款时间
            String timeEnd = notifyResult.getTimeEnd();
            //订单金额 分 为单位
            Integer totalFee = notifyResult.getTotalFee();


        } catch (WxPayException e) {
            e.printStackTrace();
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return WxPayNotifyResponse.fail("微信支付回调异常");
        }


        return null;
    }
}
