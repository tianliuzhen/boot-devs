package com.aaa.mqtt.version2;
import com.aaa.mqtt.config.MqttProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.IntegrationComponentScan;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.mqtt.core.MqttPahoClientFactory;
import org.springframework.integration.mqtt.outbound.MqttPahoMessageHandler;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageHandler;
import org.springframework.stereotype.Component;

/**
 * @author liuzhen.tian
 * @version 1.0 MqttReceiveConfig.java  2021/6/14 12:26
 */
@Configuration
@Component
@IntegrationComponentScan
public class MqttSenderConfig {

    @Autowired
    private MqttProperties mqttProperties;

    @Autowired
    private MqttPahoClientFactory mqttClientFactory;


    /**
     * ==============发送=============
     */
    @Bean
    public MessageChannel mqttOutboundChannel() {
        return new DirectChannel();
    }

    @Bean
    @ServiceActivator(inputChannel = "mqttOutboundChannel")
    public MessageHandler mqttOutbound() {
        MqttPahoMessageHandler messageHandler = new MqttPahoMessageHandler(
                mqttProperties.getClientID(),
                mqttClientFactory);
        messageHandler.setDefaultQos(1);
        messageHandler.setAsync(true);
        messageHandler.setDefaultTopic(mqttProperties.getDefaultTopic());
        return messageHandler;
    }


}
