package com.aaa.mqtt.web;

/**
 * @author liuzhen.tian
 * @version 1.0 TestController.java  2021/5/14 21:33
 */

import com.aaa.mqtt.version1.MqttPushClient;
import com.aaa.mqtt.version2.MyMqttSenderGateWay;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@RestController
@RequestMapping("/mqtt")
public class TestController {

    @Autowired
    private MqttPushClient mqttPushClient;


    /**
     * 测试版本一
     */
    @GetMapping(value = "/publishTopic")
    public String publishTopic() {
        String topicString = "wx-lock/lalala";
        mqttPushClient.publish(1, false, topicString, "田留振");
        return "ok";
    }

    /**
     * 测试版本二
     * 注入发送MQTT的Bean
     */
    @Autowired
    private MyMqttSenderGateWay myMqttSenderGateWay;


    @GetMapping(value = "/mqttGateWay")
    public void mqttGateWay() {
        // 发送自定义消息内容，且指定主题
        myMqttSenderGateWay.sendToMqtt("/qr_gate/15736758729/update", "{\"DevSn\":\"1212012432\",\"TimeStamp\":\"1610416762\",\"RecordType\":2,\"CardNum\":\"15736758729\",\"IsVaild\":1,\"RecordTime\":\"2021/1/15 9:58:30\"}");
    }

    @Autowired
    @Qualifier(value = "testABean")
    private TestBean testBean;

    @GetMapping(value = "/testABean")
    public void testABean() {
        System.out.println(testBean.getBean());
    }

}
