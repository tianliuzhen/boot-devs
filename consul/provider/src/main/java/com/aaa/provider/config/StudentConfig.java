package com.aaa.provider.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author liuzhen.tian
 * @version 1.0 StudentConfig.java  2020/10/16 11:04
 */
@Data
@Component
@ConfigurationProperties(prefix = "user.student")
public class StudentConfig {
    private String name;
    private int age;

    @Override
    public String toString() {
        return "大家好我是" + name + "，今年" + age + "岁，我是一名在校大学生！";
    }
}
