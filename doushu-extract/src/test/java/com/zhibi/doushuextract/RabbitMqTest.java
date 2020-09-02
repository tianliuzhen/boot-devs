package com.zhibi.doushuextract;

import com.zhibi.doushuextract.config.rabbitmq.RabbitConstants;
import com.zhibi.doushuextract.processor.send.BatchSend;
import com.zhibi.doushuextract.processor.send.HelloWordSend;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.core.BatchingRabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

/**
 * @author liuzhen.tian
 * @version 1.0 RabbitMqTest.java  2020/7/31 14:17
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class RabbitMqTest {

    @Resource
    private HelloWordSend helloWordSend;

    // --------------------------- 测试batch
    @Resource
    private BatchSend batchSend;

    @Test
    public void BatchSend(){
        batchSend.batchSend();
    }

    @Test
    public void sendHelloWord(){
        helloWordSend.sendToMq();
    }
}
