package com.zhibi.doushuextract.config.rabbitmq;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * @author liuzhen.tian
 * @version 1.0 QueueAndExchangeConfig.java  2020/7/31 13:49
 */
@Configuration
@Component
public class QueueAndExchangeConfig {

    @Bean
    public DirectExchange HelloWordExchange() {
        return new DirectExchange(RabbitConstants.EXCHANGE_HELLO_WORD,true,false);
    }

    @Bean
    public Queue queueA() {
        return new Queue(RabbitConstants.QUEUE_HELLO_WORD_1);
    }

    @Bean
    public Queue queueB() {
        //队列持久
        return new Queue(RabbitConstants.QUEUE_HELLO_WORD_2, true);
    }


    /**
     * 一个交换机可以绑定多个消息队列，也就是消息通过一个交换机，可以分发到不同的队列当中去
     * 路由：相当于密钥/第三者，与交换机绑定即可路由消息到指定的队列！
     */
    @Bean
    public Binding binding() {
        return BindingBuilder.bind(queueA()).to(HelloWordExchange()).with(RabbitConstants.ROUTING_KEY_HELLO_WORD_A);
    }


}
