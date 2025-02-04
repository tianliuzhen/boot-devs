package com.aaa.springsecurity.web;

import com.aaa.springsecurity.config.secrity.MyTokenService;
import com.aaa.springsecurity.config.secrity.model.AjaxResult;
import com.aaa.springsecurity.config.secrity.model.LoginUser;
import com.aaa.springsecurity.config.secrity.util.SecurityUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

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

    // 令牌自定义标识
    @Value("${token.header}")
    private String header;


    @GetMapping("/login")
    public AjaxResult login(@RequestParam(defaultValue = "admin", required = false) String username,
                            @RequestParam(defaultValue = "123456", required = false) String password,
                            HttpServletResponse response) {
        // 该方法会去调用UserDetailsServiceImpl.loadUserByUsername
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username, password);
        Authentication authentication = authenticationManager.authenticate(authenticationToken);

        LoginUser loginUser = (LoginUser) authentication.getPrincipal();
        String token = tokenService.createToken(loginUser);

        // 设置cookie
        Cookie cookie = new Cookie(header, token);
        cookie.setMaxAge(7 * 24 * 60 * 60); // 7天过期
        cookie.setPath("/");
        response.addCookie(cookie);

        return AjaxResult.success(token);
    }

    @GetMapping("/getUserByToken")
    public AjaxResult getUserByToken(String token) {
        LoginUser loginUser = SecurityUtils.getLoginUser();
        return AjaxResult.success(loginUser);
    }

    /**
     * 自定义解析 PreAuthorize
     *
     * @return
     */
    @PostMapping("/updateById")
    @PreAuthorize("@ss.hasPermi('system:user:edit')")
    public AjaxResult updateById() {
        // if (true) {
        //     也能直接触发：MyAccessDeniedHandler
        //     throw new AccessDeniedException("123");
        // }
        return AjaxResult.success();
    }

    /**
     * @return
     * @Secured注解的作用：(只校验角色) 在用户向浏览器发送一个请求时会去访问控制器中的方法，
     * 然后在访问此控制器中的方法之前会先去UserDetailsService用户细节实现类的实现方法中return的User对象查看是否具有@Secured注解中指定的角色，
     * 如果有指定的角色，那么系统允许用户访问此控制器方法，否则，系统不允许访问此控制器方法；
     * 注意在使用@Secured设置角色名字的时候，角色名的前面一定要加上ROLE_前缀；
     */
    @PostMapping("/deleteById")
    @Secured({"ROLE_USER", "ROLE_admin"})
    @PreAuthorize("hasRole('ROLE_admin,ROlE_wxuser')")
    public AjaxResult deleteById() {
        return AjaxResult.success();
    }

    /**
     * @PreAuthorize 自定义校验角色用法
     */
    @PostMapping("/deleteByUserName")
    // @PreAuthorize("hasAnyRole('admin')") // 单个角色校验
    @PreAuthorize("hasAnyRole('ROLE_wxuser','ROLE_admin')") // 多个角色校验
    public AjaxResult deleteByUserName() {
        return AjaxResult.success();
    }

    /**
     * @PreAuthorize 自定义校验资源用法
     */
    @PostMapping("/deleteByDeptNo")
    // @PreAuthorize("hasAuthority('delete')") // 单个权限校验
    @PreAuthorize("hasAnyAuthority('delete','update')") // 多个权限校验
    public AjaxResult deleteByDeptNo() {
        return AjaxResult.success();
    }
}
