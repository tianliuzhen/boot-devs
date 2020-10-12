package com.aaa.sass.web;

/**
 * @author liuzhen.tian
 * @version 1.0 TokenController.java  2020/7/30 21:24
 */

import com.aaa.sass.annotation.NonLogin;
import com.aaa.sass.config.JwtConfig;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@RestController
public class TokenController {

    @Resource
    private JwtConfig jwtConfig ;

    /**
     * 用户登录接口
     * @param userName
     * @param passWord
     * @return
     */
    @PostMapping("/login")
    public String login (@RequestParam("userName") String userName,
                              @RequestParam("passWord") String passWord) throws JSONException {
        JSONObject json = new JSONObject();

        /** 验证userName，passWord和数据库中是否一致，如不一致，直接return HttpResult.errer(); 【这里省略该步骤】*/

        // 这里模拟通过用户名和密码，从数据库查询userId
        // 这里把userId转为String类型，实际开发中如果subject需要存userId，则可以JwtConfig的createToken方法的参数设置为Long类型
        String userId = 1001 + "";
        String token =jwtConfig.createToken(userId) ;
        if (!StringUtils.isEmpty(token)) {
            json.put("token",token) ;
        }
        return token;
    }

    /**
     * 需要 Token 验证的接口
     */
    @PostMapping("/info")
    public String info (){
        return "info" ;
    }

    /**
     * 不需要 Token 验证的接口
     */
    @NonLogin
    @PostMapping("/logNoneInfo")
    public String LogNoneInfo (){
        return "info" ;
    }

    /**
     * 根据请求头的token获取userId
     * @param request
     * @return
     */
    @GetMapping("/getUserInfo")
    public String getUserInfo(HttpServletRequest request){
        String usernameFromToken = jwtConfig.getUsernameFromToken(request.getHeader("token"));
        return usernameFromToken;
    }

    /*
        为什么项目重启后，带着之前的token还可以访问到需要info等需要token验证的接口？
        答案：只要不过期，会一直存在，类似于redis
     */

}
