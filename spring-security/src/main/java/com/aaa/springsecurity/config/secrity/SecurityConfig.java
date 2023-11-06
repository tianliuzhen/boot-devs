package com.aaa.springsecurity.config.secrity;

/**
 * @author liuzhen.tian
 * @version 1.0 SecurityConfig.java  2023/11/6 22:48
 */

import com.aaa.springsecurity.config.secrity.filter.JwtAuthenticationTokenFilter;
import com.aaa.springsecurity.config.secrity.handle.MyAccessDeniedHandler;
import com.aaa.springsecurity.config.secrity.handle.MyAuthenticationEntryPoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * spring security配置
 *
 * @author ruoyi
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private JwtAuthenticationTokenFilter jwtAuthenticationFilter;

    @Autowired
    private MyAuthenticationEntryPoint myAuthenticationEntryPoint;

    @Autowired
    private MyAccessDeniedHandler myAccessDeniedHandler;

    /**
     * 密码明文加密方式配置
     *默认加密方式
     * @return
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * 获取AuthenticationManager（认证管理器），登录时认证使用
     * 默认认证
     * @param authenticationConfiguration
     * @return
     * @throws Exception
     */
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http.csrf().disable(); // 基于 token，不需要 csrf
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);// 基于 token，不需要 session
        // 下面开始设置权限
        http.authorizeRequests(authorize -> authorize
                .antMatchers("/wx/login").permitAll() //不需要进行身份验证的接口
                .anyRequest().authenticated()//除上面外的所有请求全部需要 鉴权认证
        );

        //定义filter的先后顺序，保证 jwtFilter比用户验证的过滤器先执行
        http.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
        //自定义异常捕获机制
        http.exceptionHandling().authenticationEntryPoint(myAuthenticationEntryPoint).accessDeniedHandler(myAccessDeniedHandler);
        return http.build();
    }
}
