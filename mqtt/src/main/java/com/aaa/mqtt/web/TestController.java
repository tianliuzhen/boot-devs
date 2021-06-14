package com.aaa.mqtt.web;

/**
 * @author liuzhen.tian
 * @version 1.0 TestController.java  2021/5/14 21:33
 */

import com.aaa.mqtt.version1.MqttPushClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@RestController
@RequestMapping("/mqtt")
public class TestController {

    @Autowired
    private MqttPushClient mqttPushClient;

    @GetMapping(value = "/publishTopic")
    public String publishTopic() {
        String topicString = "wx-lock/lalala";
        mqttPushClient.publish(1, false, topicString, "田留振");
        return "ok";
    }

}
