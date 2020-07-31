package com.zhibi.doushuextract.processor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * 实现 消息确认到交换机 和 return到队列
 * @author liuzhen.tian
 * @version 1.0 MsgConfirmAndReturn.java  2020/7/31 14:07
 */
@Component
@Slf4j
public class MsgConfirmAndReturn implements RabbitTemplate.ConfirmCallback,RabbitTemplate.ReturnCallback {
    @Autowired
    private RabbitTemplate rabbitTemplate;

    /**
     *  @PostConstruct 是在bean实例化之后，初始化之前
     */
    @PostConstruct
    public void init(){
        rabbitTemplate.setConfirmCallback(this);
        rabbitTemplate.setReturnCallback(this);
    }

    @Override
    public void confirm(CorrelationData correlationData, boolean b, String s) {
        // log.info("CallBackConfirm UUID: " + correlationData.getId());
        //此方法监听消息确认结果（消息是否发到交换机）
        if (b) {
            log.info("~~~~~~~~~~~~~消息发送到交换机【成功】");
        }else {
            log.error("~~~~~~~~~~~~~消息发送到交换机【失败】");
        }

    }

    @Override
    public void returnedMessage(Message message, int i, String s, String s1, String s2) {
        //此方法用于return监听（当交换机分发消息到队列跑【失败】执行）
        log.error("~~~~~~~~~~~~~交换机发送消息到队列【失败】");


    }
}
