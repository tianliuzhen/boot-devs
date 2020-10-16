package com.aaa.consumer.web;

import com.aaa.consumer.api.ProviderApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author liuzhen.tian
 * @version 1.0 ConsumerController.java  2020/10/16 14:22
 */
@RestController
@RequestMapping("/consumer")
public class ConsumerController {

    @Autowired
    private ProviderApi providerApi;

    /**
     * consul 默认需要自定义提供一个健康检查接口
     */
    @RequestMapping("/health")
    public void Health() {
        System.out.println("health");
    }

    @GetMapping("/consumer/getRemoteStudent")
    public String getRemoteStu(){
        return providerApi.StudentIntro();
    }

    @GetMapping("/consumer/getRemoteTeacher")
    public String getRemoteTeacher(){
        return providerApi.TeachIntro();
    }
}
