package com.aaa.wechat.config;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

/**
 * description: 跨域全局配置 两种配置 1、需要添加一个配置类 ： 2、添加 Filter 的方式
 *
 * @author tianliuzhen
 * @date 2021/05/30
 */

@Configuration
public class CorsConfig {


    /**
     * 配置 跨域方式一
     */
    @Bean
    public FilterRegistrationBean corsFilter() {
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true); // 允许cookies跨域
        config.addAllowedOrigin("*");// #允许向该服务器提交请求的URI，*表示全部允许，自定义可以添加多个，在SpringMVC中，如果设成*，会自动转成当前请求头中的Origin
        config.addAllowedHeader("*");// #允许访问的头信息,*表示全部，可以添加多个
        config.setMaxAge(1800L);// 预检请求的缓存时间（秒），即在这个时间段里，对于相同的跨域请求不会再预检了
        config.addAllowedMethod("*");// 允许提交请求的方法，*表示全部允许，一般OPTIONS,GET,POST三个够了
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);//对所有接口都有效
        FilterRegistrationBean bean = new FilterRegistrationBean(new CorsFilter(source));
        bean.setOrder(1); // 优先级最高
        return bean;
    }

    /**
     * 配置 跨域方式二
     */
    // @Bean
    // public WebMvcConfigurer corsConfigurer() {
    //     return new WebMvcConfigurer() {
    //         @Override
    //         public void addCorsMappings(CorsRegistry registry) {
    //             registry.addMapping("/*")
    //                     .allowedOrigins("*")
    //                     .allowCredentials(true)
    //                     .allowedMethods("GET", "POST", "DELETE", "PUT","PATCH")
    //                     .maxAge(3600);
    //         }
    //     };
    // }



}
