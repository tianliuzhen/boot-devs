package com.zhibi.doushuextract.processor.receive;

import com.rabbitmq.client.Channel;
import com.zhibi.doushuextract.config.rabbitmq.RabbitConstants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * @author liuzhen.tian
 * @version 1.0 HelloWordReceive.java  2020/7/31 14:11
 */
@Component
@Slf4j
public class HelloWordReceive {

    @RabbitListener(queues = RabbitConstants.QUEUE_HELLO_WORD_1)
    /**
     * @param message  消息实体
     * @param channel
     * @return void
     */
    public void process(Message message, Channel channel) throws IOException {
        // 限制于消费级别、限流处理：消息体大小不限制，每次限制消费一条，只作用于该Consumer层，不作用于Channel
        channel.basicQos(0, 1, false);
        log.info("MqReceiver  : " + new String(message.getBody()));
        String messageText = new String(message.getBody());
        try {
            if(validate(messageText)){
                try {
                    log.info("MqReceiver 确认消费");
                    /**
                     * 手动确认消息接收
                     * 如果不加下面这句话是默认是不确认，
                     * 即这里只会读取队列里的消息但是不会删除，采用手动应答模式, 手动确认应答更为安全稳定
                     */
                    channel.basicAck(message.getMessageProperties().getDeliveryTag(),false);
                    // int i=1/0;  // 模拟异常 ，可通过 channel.basicNack 进行返现回滚到队列，防止误删
                } catch (Exception e) {
                    /**
                     *  最后一个参数requeue一般都会为true，此次没调用到数据，把这个消息返回到队列中再消费，
                     *  如果代码中出现了int i=1/0,那么还是会造成死循环。
                     *  给Queue绑定死信队列，使用nack（requque为false）确认消息消费失败
                     *  个人建议这里还是，通过数据库记录，没必要再次放在队列，重复消费浪费资源
                     */
                    // channel.basicNack(message.getMessageProperties().getDeliveryTag(), false, false);
                }
            }else{
                log.info("[receiver] reject");
                //拒绝消息接收
                channel.basicReject(message.getMessageProperties().getDeliveryTag(), false);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private boolean validate(String messageText) {
        return !(messageText!=null && messageText.indexOf("fuck")>-1);
    }
}


