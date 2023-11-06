package com.aaa.springsecurity.web;

import com.aaa.springsecurity.config.secrity.MyTokenService;
import com.aaa.springsecurity.config.secrity.model.AjaxResult;
import com.aaa.springsecurity.config.secrity.model.LoginUser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author liuzhen.tian
 * @version 1.0 LoginController.java  2023/11/6 22:50
 */
@Slf4j
@RestController
@RequestMapping("/wx")
public class LoginController {
    @Resource
    private AuthenticationManager authenticationManager;
    @Autowired
    private MyTokenService tokenService;

    @GetMapping("/login")
    public AjaxResult login(@RequestParam(defaultValue = "admin",required = false) String username,
                            @RequestParam(defaultValue = "123456",required = false) String password) {
        // 该方法会去调用UserDetailsServiceImpl.loadUserByUsername
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username, password);
        Authentication authentication = authenticationManager.authenticate(authenticationToken);

        LoginUser loginUser = (LoginUser) authentication.getPrincipal();
        String token = tokenService.createToken(loginUser);
        return AjaxResult.success(token);
    }
}
