package com.aaa.springsecuritylogin.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

/**
 * @author liuzhen.tian
 * @version 1.0 WebSecurityConfig.java  2025/2/4 21:03
 */

@Configuration
public class SecurityConfig {
    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Throwable {
        // http.sessionManagement()
        //         .sessionCreationPolicy(SessionCreationPolicy.STATELESS); // 禁用 Session
        http.csrf(csrf -> csrf.disable());
        http.authorizeRequests(authorize ->
                authorize.antMatchers("*.html", "*.css", "*.js", "/loginHtml", "/doLogin"
                                ,"/setSession","/getSession")
                        .permitAll() // 不需要进行身份验证的接口
                        .anyRequest().authenticated()// 除上面外的所有请求全部需要 鉴权认证
        );

        // http.authorizeHttpRequests(registry -> {
        //     registry.requestMatchers(new AntPathRequestMatcher("")).permitAll();
        //     registry.requestMatchers("/**").authenticated();
        // });
        http.formLogin(form -> {
            // 自定义登录页面
            form.loginPage("/loginHtml")
                    // 自定义登录处理接口
                    .loginProcessingUrl("/doLogin")
                    // 服务器端转发，地址栏不变，适合需要服务器端处理的场景 (只能是post，因为 /doLogin 默认是post) 详情可见：ForwardAuthenticationSuccessHandler.onAuthenticationSuccess
                    // .successForwardUrl("/mySuccessForwardUrl")
                    // 客户端重定向，地址栏更新，适合直接跳转页面的场景    (只能是get) 详情可见：SavedRequestAwareAuthenticationSuccessHandler.onAuthenticationSuccess
                    .defaultSuccessUrl("/myDefaultSuccessUrl") // 有先后顺序，后面会覆盖前面的

            ; // 登录成功展示页面
        });
        http.rememberMe() // 启用 rememberMe 功能
                .key("uniqueAndSecretKey") // 设置加密密钥
                .tokenValiditySeconds(86400) // 设置 Token 有效期（秒），默认 14 天
                .rememberMeParameter("remember-me") // 设置表单中“记住我”参数的名称
                .rememberMeCookieName("remember-me") // 设置 Cookie 名称
                .and();
        return http.build();
    }
}
