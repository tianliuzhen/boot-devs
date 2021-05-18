package com.aaa.wechat;

import com.aaa.wechat.config.WxJxPayProperties;
import com.aaa.wechat.utils.wx.CertHttpUtil;
import com.aaa.wechat.utils.wx.WXPayUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

/**
 * @author liuzhen.tian
 * @version 1.0 WeChatRefunf.java  2021/5/18 20:26
 */
@SpringBootTest
public class WeChatRefund {
    @Autowired
    private WxJxPayProperties wxJxPayProperties;

    @Test
    public void refund() {
        //
        // 1.0 拼凑微信退款需要的参数
        String appid = wxJxPayProperties.getAppId(); // 微信公众号的appid
        String mch_id = wxJxPayProperties.getMchId(); // 商户号
        String nonce_str = WXPayUtil.generateNonceStr(); // 生成随机数
        String out_trade_no = "2104122200000027"; //商户订单号
        String out_refund_no = "210412220000002701";//商户退款单号
        String total_fee = "1";//订单金额
        String refund_fee = "1";//退款金额


        // 2.0 生成map集合
        SortedMap<String, String> packageParams = new TreeMap<String, String>();
        packageParams.put("appid", appid); // 微信公众号的appid
        packageParams.put("mch_id", mch_id); // 商务号
        packageParams.put("nonce_str", nonce_str); // 随机生成后数字，保证安全性
        packageParams.put("out_trade_no", out_trade_no);
        packageParams.put("out_refund_no", out_refund_no);
        packageParams.put("total_fee", total_fee);
        packageParams.put("refund_fee", refund_fee);


        try {
            // 3.0 利用上面的参数，先去生成自己的签名
            String sign = WXPayUtil.generateSignature(packageParams, wxJxPayProperties.getMchKey());

            // 4.0 将签名再放回map中，它也是一个参数
            packageParams.put("sign", sign);

            // 5.0将当前的map结合转化成xml格式
            String xml = WXPayUtil.mapToXml(packageParams);

            // 6.0获取需要发送的url地址
            String wxUrl = "https://api.mch.weixin.qq.com/secapi/pay/refund"; // 获取退款的api接口

            System.out.println("发送前的xml为：" + xml);

            // 7,向微信发送请求转账请求
            String returnXml = CertHttpUtil.postData(wxUrl, xml, wxJxPayProperties.getMchId(), "cert/apiclient_cert.p12");

            System.out.println("返回的returnXml为:" + returnXml);

            // 8，将微信返回的xml结果转成map格式
            Map<String, String> returnMap = WXPayUtil.xmlToMap(returnXml);

            if (returnMap.get("return_code").equals("SUCCESS")) {
                // 退款成功
                System.out.println("returnMap为:" + returnMap);
            }


        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
