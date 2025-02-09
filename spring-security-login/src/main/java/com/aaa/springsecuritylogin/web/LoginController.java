package com.aaa.springsecuritylogin.web;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;

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
    public String mySuccessForwardUrl(HttpSession session){
        return "forwardSuccessful";
    }

    @GetMapping(value = "/myDefaultSuccessUrl")
    public String successful(HttpSession session){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return "successful";
    }
}
