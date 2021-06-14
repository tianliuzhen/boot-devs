package com.aaa.mqtt.version2;

/**
 * @author liuzhen.tian
 * @version 1.0 MqttReceiveConfig.java  2021/6/14 12:26
 */

import com.aaa.mqtt.config.MqttProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.IntegrationComponentScan;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.core.MessageProducer;
import org.springframework.integration.mqtt.core.MqttPahoClientFactory;
import org.springframework.integration.mqtt.inbound.MqttPahoMessageDrivenChannelAdapter;
import org.springframework.integration.mqtt.support.DefaultPahoMessageConverter;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageHandler;
import org.springframework.messaging.MessagingException;
import org.springframework.stereotype.Component;

@Configuration
@Component
@IntegrationComponentScan
public class MqttReceiverConfig {

    @Autowired
    private MqttProperties mqttProperties;

    @Autowired
    private MqttPahoClientFactory mqttClientFactory;


    private String[] topics = {"test-topic", "test-topic1"};

    /**
     =============订阅==============
     */

    //接收通道
    @Bean
    public MessageChannel mqttInputChannel() {
        return new DirectChannel();
    }


    //配置client,监听的topic
    @Bean
    public MessageProducer inbound() {
        MqttPahoMessageDrivenChannelAdapter adapter =
                new MqttPahoMessageDrivenChannelAdapter(
                        mqttProperties.getClientID()+"_inbound",
                        mqttClientFactory,
                        topics  );
        adapter.setCompletionTimeout(mqttProperties.getTimeout());
        adapter.setConverter(new DefaultPahoMessageConverter());
        adapter.setQos(1);
        adapter.setOutputChannel(mqttInputChannel());
        return adapter;
    }

    //通过通道获取数据
    @Bean
    @ServiceActivator(inputChannel = "mqttInputChannel")
    public MessageHandler handler() {
        return new MessageHandler() {
            @Override
            public void handleMessage(Message<?> message) throws MessagingException {
                String topic = message.getHeaders().get("mqtt_receivedTopic").toString();
                String type = topic.substring(topic.lastIndexOf("/")+1, topic.length());
                if("hello".equalsIgnoreCase(topic)){
                    System.out.println("hello,fuckXX,"+message.getPayload().toString());
                }else if("hello1".equalsIgnoreCase(topic)){
                    System.out.println("hello1,fuckXX,"+message.getPayload().toString());
                }
            }
        };
    }


}
