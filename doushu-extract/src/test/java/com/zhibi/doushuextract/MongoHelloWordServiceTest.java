package com.zhibi.doushuextract;

import com.zhibi.doushuextract.service.MongoHelloWordService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author liuzhen.tian
 * @version 1.0 MongoHelloWordServiceTest.java  2020/8/24 12:06
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class MongoHelloWordServiceTest {
    @Autowired
    private MongoHelloWordService mongoHelloWordService;

    @Test
    public void updateByListTest(){
        mongoHelloWordService.updateByList();
    }
    @Test
    public void insertByList(){
        mongoHelloWordService.insertByList();
    }
}
