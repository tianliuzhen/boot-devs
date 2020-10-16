package com.aaa.provider.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author liuzhen.tian
 * @version 1.0 TeachConfig.java  2020/10/16 11:05
 */
@Data
@Component
@ConfigurationProperties(prefix = "user.teacher")
public class TeachConfig {
    private String name;
    private String course;


    @Override
    public String toString() {
        return "大家好我是" + name + "，是一名大学老师！教同学们学习" + course;
    }
}
