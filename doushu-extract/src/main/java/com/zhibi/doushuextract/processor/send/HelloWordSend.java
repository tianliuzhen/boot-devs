package com.zhibi.doushuextract.processor.send;

import com.zhibi.doushuextract.config.rabbitmq.RabbitConstants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * 测试消息发送
 * @author liuzhen.tian
 * @version 1.0 HelloWordSend.java  2020/7/31 13:56
 */
@Slf4j
@Component
public class HelloWordSend {

    @Resource
    private RabbitTemplate rabbitTemplate;

    public void sendToMq(){
        /**
         * @parme 1 交换机
         * @parme 2 路由key
         * @parme 3 消息实体
         */
        rabbitTemplate.convertAndSend(
                RabbitConstants.EXCHANGE_HELLO_WORD,
                RabbitConstants.ROUTING_KEY_HELLO_WORD_A,
                "helloWord!"
        );

    }
}
