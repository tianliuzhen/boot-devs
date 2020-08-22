package com.aaa.wechat;

import com.aaa.wechat.domain.DecryptSpec;
import com.aaa.wechat.service.WeChatService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

/**
 * @author liuzhen.tian
 * @version 1.0 WeChatTest.java  2020/8/22 16:21
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class WeChatTest {

    @Resource
    private WeChatService weChatService;

    @Test
    void contextLoads() {
        DecryptSpec decryptSpec = new DecryptSpec();
        weChatService.decrypt(decryptSpec);

    }

}
