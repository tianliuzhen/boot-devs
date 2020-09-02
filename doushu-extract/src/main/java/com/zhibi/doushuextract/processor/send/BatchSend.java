package com.zhibi.doushuextract.processor.send;

import com.zhibi.doushuextract.config.rabbitmq.RabbitConstants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.core.BatchingRabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * @author liuzhen.tian
 * @version 1.0 BatchSend.java  2020/9/2 12:05
 */
@Slf4j
@Component
public class BatchSend {
    @Autowired
    BatchingRabbitTemplate batchQueueRabbitTemplate;


    public void batchSend()  {
        // 除了send(String exchange, String routingKey, Message message, CorrelationData correlationData)方法是发送单条数据
        // 其他send都是批量发送

        //批量发送

        long timestamp = System.currentTimeMillis();

        String msg;
        Message message;
        MessageProperties messageProperties = new MessageProperties();
        for (int i = 0; i < 100; i++) {
            msg = "batch." + timestamp + "-" + i;
            message = new Message(msg.getBytes(), messageProperties);
            batchQueueRabbitTemplate.send(RabbitConstants.QUEUE_HELLO_WORD_2, message);

//            defaultRabbitTemplate.convertAndSend(RabbitMqConfig2.BATCH_QUEUE_NAME, msg.getBytes());
        }

        System.out.println("发送数据完毕");
        System.out.println("等待30s");
        try {
            TimeUnit.SECONDS.sleep(30); //等待消费者消费
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
