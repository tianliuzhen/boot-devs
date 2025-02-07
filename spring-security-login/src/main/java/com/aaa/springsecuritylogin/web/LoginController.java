package com.aaa.springsecuritylogin.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * @author liuzhen.tian
 * @version 1.0 LoginController.java  2025/2/4 21:16
 */
@Controller
public class LoginController {

    @GetMapping(value = "/loginHtml")
    public String loginHtml(){
        return "/login";
    }

    @PostMapping(value = "/mySuccessForwardUrl")
    public String mySuccessForwardUrl(){
        return "forwardSuccessful";
    }

    @GetMapping(value = "/myDefaultSuccessUrl")
    public String successful(){
        return "successful";
    }
}
