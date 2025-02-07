package com.aaa.springsecuritylogin.web;

/**
 * @author liuzhen.tian
 * @version 1.0 SessionController.java  2025/2/7 23:10
 */
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
public class SessionController {

    // 存储数据到 Session
    @GetMapping("/setSession")
    public String setSession(@RequestParam String name, HttpSession session) {
        session.setAttribute("name", name); // 将数据存储到 Session 中
        return "Session 已设置，name = " + name;
    }

    // 从 Session 中读取数据
    @GetMapping("/getSession")
    public String getSession(HttpSession session) {
        String name = (String) session.getAttribute("name"); // 从 Session 中获取数据
        return "从 Session 中获取的 name = " + name;
    }

    // 清除 Session
    @GetMapping("/clearSession")
    public String clearSession(HttpSession session) {
        session.invalidate(); // 清除 Session
        return "Session 已清除";
    }
}
