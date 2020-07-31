package com.zhibi.doushuextract;

import com.zhibi.doushuextract.processor.send.HelloWordSend;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

/**
 * @author liuzhen.tian
 * @version 1.0 RabbitMqTest.java  2020/7/31 14:17
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class RabbitMqTest {

    @Resource
    private HelloWordSend helloWordSend;


    @Test
    public void sendHelloWord(){
        helloWordSend.sendToMq();
    }
}
