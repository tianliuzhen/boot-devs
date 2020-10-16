package com.aaa.provider.web;

import com.aaa.provider.config.StudentConfig;
import com.aaa.provider.config.TeachConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author liuzhen.tian
 * @version 1.0 ProviderController.java  2020/10/16 10:47
 */
@RestController
@RequestMapping("provider")
public class ProviderController {


    @Autowired
    private StudentConfig studentConfig;

    @Autowired
    private TeachConfig teachConfig;

    @Value("${user.description}")
    private String description;

    /**
     * consul 默认需要自定义提供一个健康检查接口
     */
    @RequestMapping("/health")
    public void Health() {
        System.out.println("health");
    }

    @GetMapping("/user/student/intro")
    public String StudentIntro() {
        return studentConfig.toString();
    }

    @GetMapping("/user/teach/intro")
    public String TeachIntro() {
        return teachConfig.toString();
    }

    @GetMapping("/user/description")
    public String Description() {
        return description;
    }

}
