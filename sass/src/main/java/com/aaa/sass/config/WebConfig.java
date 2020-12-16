package com.aaa.sass.config;

/**
 * @author liuzhen.tian
 * @version 1.0 WebConfig.java  2020/7/30 21:24
 */

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import javax.annotation.Resource;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    // @Resource
    // private TokenInterceptor tokenInterceptor ;
    @Resource
    AuthenticationInterceptor authenticationInterceptor;
    @Resource
    private JwtConfig jwtConfig;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // registry.addInterceptor(tokenInterceptor).
        //         excludePathPatterns(jwtConfig.getIgnoreUrls())
        // .addPathPatterns();
        registry.addInterceptor(authenticationInterceptor);
    }
}
