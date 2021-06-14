package com.aaa.mqtt.web;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author liuzhen.tian
 * @version 1.0 Config.java  2021/6/14 22:01
 */

@Configuration
public class TestConfig {
    @Bean(name = "testABean")
    public TestBean testABean(){
        return new TestBean("testABean");
    }

    @Bean(name = "testABean")
    public TestBean testABean2(){
        return new TestBean("testABean2");
    }

}
