package com.aaa.wechat.web;

import com.aaa.wechat.domain.City;
import com.aaa.wechat.service.WechatPayService;
import com.aaa.wechat.utils.CommonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.Date;

/**
 *  微信支付接口、退款接口
 * @author liuzhen.tian
 * @version 1.0 WxPayController.java  2020/8/25 14:53
 */

@RestController
@RequestMapping("/wx-pay")
public class WxPayController {

    @Autowired
    private WechatPayService wechatPayService;

    @PostMapping("/aaa")
    public City aaa(){
        City city = new City();
        city.setDate(new Date());
        return city;
    }


    @PostMapping("/pay")
    public void createPay(HttpServletRequest request){

        /**
         * @param body 商品描述
         * @param outTradeNo  order 的 code
         * @param totalFee 总金额 订单总金额，单位为分，详见支付金额
         * @param openId 用户标识
         * @param ipAddr 终端IP
         */
        wechatPayService.createWXPay("测试商品",
                "商户订单号",
                CommonUtils.convertBigDecimalToInteger(BigDecimal.valueOf(128.12)),
                "用户的唯一标识一般是  openid",
                CommonUtils.getIpAddr(request));

    }
    @PostMapping("/refund")
    public void createRefund(){


        /**
         *
         * 申请退款之前需要先查询订单，拿到
         * 微信订单号 transaction_id  或者  out_trade_no
         *
         * transaction_id、out_trade_no二选一，如果同时存在优先级：transaction_id> out_trade_no
         * transaction_id 是在回调的接口中 拿到的
         */

        /**
         * 微信退款接口
         * @param outTradeNo 商户订单号  （这个商户也是我们自己后台生成的）
         * @param outRefundNo 售后code
         * @param totalFee 订单总金额 单位为分
         * @param refundFee 需要申请的退款金额 单位为分
         */
        wechatPayService.refund("商户订单号",
                                "售后code",
                CommonUtils.convertBigDecimalToInteger(BigDecimal.valueOf(128.12)),
                CommonUtils.convertBigDecimalToInteger(BigDecimal.valueOf(28.12)));
    }
}
