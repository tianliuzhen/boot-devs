package com.aaa.sass.web;

/**
 * @author liuzhen.tian
 * @version 1.0 TokenController.java  2020/7/30 21:24
 */

import com.aaa.sass.annotation.NonLogin;
import com.aaa.sass.config.JwtConfig;
import com.aaa.sass.config.UserContext;
import com.aaa.sass.domain.User;
import com.aaa.tlzspringbootstarter.common.TlzProperties;
import com.aaa.tlzspringbootstarter.common.TlzService;
import com.alibaba.fastjson.JSON;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@Log4j2
@RestController
public class TokenController {

    @Resource
    private JwtConfig jwtConfig;

    @Autowired
    private TlzService tlzService;
    @Autowired
    private TlzProperties tlzProperties;


    @NonLogin
    @PostMapping("/testStarter")
    public void testStarter() {
        String bySelf = tlzService.getBySelf("");

    }


    /**
     * 用户登录接口
     *
     * @param userName
     * @param passWord
     * @return
     */
    @PostMapping("/login")
    public String login(@RequestParam("userName") String userName,
                        @RequestParam("passWord") String passWord) throws JSONException {
        JSONObject json = new JSONObject();

        /** 验证userName，passWord和数据库中是否一致，如不一致，直接return HttpResult.errer(); 【这里省略该步骤】*/

        // 这里模拟通过用户名和密码，从数据库查询userId
        // 这里把userId转为String类型，实际开发中如果subject需要存userId，则可以JwtConfig的createToken方法的参数设置为Long类型
        String userId = 1001 + "";
        String token = jwtConfig.createToken(userId);
        if (!StringUtils.isEmpty(token)) {
            json.put("token", token);
        }
        return token;
    }

    /**
     * 需要 Token 验证的接口
     */
    @NonLogin
    @PostMapping("/info")
    public String info() {
        log.info("info###########");
        log.error("info###########");
        log.warn("info###########");
        return "info";
    }

    /**
     * 从threadLocal 获取用户信息
     */
    @PostMapping("/getUserInfoByThread")
    public User getUserInfoByThread() {
        return UserContext.getUser();
    }


    /**
     * 不需要 Token 验证的接口
     */
    @NonLogin
    @PostMapping("/logNoneInfo")
    public String LogNoneInfo() {
        return "info";
    }

    /**
     * 根据请求头的token获取userId
     *
     * @param request
     * @return
     */
    @GetMapping("/getUserInfo")
    public String getUserInfo(HttpServletRequest request) {
        String usernameFromToken = jwtConfig.getUsernameFromToken(request.getHeader("token"));
        return usernameFromToken;
    }

    /**
     * 根据请求头的token获取userId
     *
     * @param request
     * @return
     */
    @GetMapping("/getUserInfoTokenExpire")
    public Date getUserInfoTokenExpire(HttpServletRequest request) {
        Date startTime = jwtConfig.getIssuedAtDateFromToken(request.getHeader("token"));
        Date expireTime = jwtConfig.getExpirationDateFromToken(request.getHeader("token"));
        return expireTime;
    }

    @NonLogin
    @PostMapping("/getUser")
    public User getUser(@RequestBody User user) {
        String s = JSON.toJSONString(user);
        User res = JSON.parseObject(s, User.class);
        return res;
    }

    @NonLogin
    @PostMapping("/getDate")
    public Date getDate() {
        return new Date();
    }

    /*
        为什么项目重启后，带着之前的token还可以访问到需要info等需要token验证的接口？
        答案：只要不过期，会一直存在，类似于redis
     */

}
