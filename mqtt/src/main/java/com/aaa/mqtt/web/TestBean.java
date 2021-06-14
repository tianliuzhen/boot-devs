package com.aaa.mqtt.web;

import lombok.Data;

/**
 * @author liuzhen.tian
 * @version 1.0 TestABean.java  2021/6/14 15:23
 */
@Data
public class TestBean {
    private String bean;


    public TestBean(String bean) {
        this.bean = "TestABean";
    }
}
