package com.aaa.mqtt.config;

import com.aaa.mqtt.common.MqttPushClient;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;


/**
 * @author liuzhen.tian
 * @version 1.0 MqttConfig.java  2021/5/14 21:25
 * @Classname MqttConfig
 * @Description mqtt相关配置信息
 */

@Setter
@Getter
@Component
@ConfigurationProperties("mqtt")
public class MqttConfig {
    @Autowired
    private MqttPushClient mqttPushClient;

    /**
     * 用户名
     */
    private String username;
    /**
     * 密码
     */
    private String password;
    /**
     * 连接地址
     */
    private String hostUrl;
    /**
     * 客户Id
     */
    private String clientID;
    /**
     * 默认连接话题
     */
    private String defaultTopic;
    /**
     * 超时时间
     */
    private int timeout;
    /**
     * 保持连接数
     */
    private int keepalive;

    @Bean
    public MqttPushClient getMqttPushClient() {
        //clientID 拼接随机六位小数
        String clientID = this.clientID + ((Math.random() * 9 + 1) * 100000);
        mqttPushClient.connect(hostUrl, clientID, username, password, timeout, keepalive);
        // 以test/#结尾表示订阅所有以test开头的主题
        mqttPushClient.subscribe(defaultTopic, 1);
        return mqttPushClient;
    }
}
