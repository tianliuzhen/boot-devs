package com.zhibi.doushuextract.processor.receive;

import com.rabbitmq.client.Channel;
import com.zhibi.doushuextract.config.rabbitmq.RabbitConstants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.BatchMessageListener;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;

/**
 * @author liuzhen.tian
 * @version 1.0 BatchQueueListener.java  2020/9/2 11:55
 */
@Slf4j
@Component
public class BatchQueueListener implements BatchMessageListener {

    //批量接收处理
    // @RabbitListener(queues = RabbitConstants.QUEUE_HELLO_WORD_2,containerFactory = "batchQueueRabbitListenerContainerFactory")
    @Override
    public void onMessageBatch(List<Message> messages) {
        log.info("batch.queue.consumer 收到{}条message", messages.size());
        if(messages.size()>0){
            log.info("第一条数据是: {}", new String(messages.get(0).getBody()));
        }
    }

    //单个接收处理
    @RabbitListener(queues = RabbitConstants.QUEUE_HELLO_WORD_2)
    public void onMessageOne(Message message, Channel channel) throws IOException {
        log.info("MqReceiver  : " + new String(message.getBody()));
        channel.basicAck(message.getMessageProperties().getDeliveryTag(),false);
    }
}
