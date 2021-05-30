package com.aaa.wechat.web;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author liuzhen.tian
 * @version 1.0 HelloController.java  2021/5/30 10:18
 */
@RestController
// @CrossOrigin 单个接口处理跨域
public class HelloController {
    @RequestMapping("/hello")
    public String HelloSpring (){
        return "hello Java碎碎念！";
    }
}
