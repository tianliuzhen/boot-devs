package com.aaa.consumer.api;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author liuzhen.tian
 * @version 1.0 ProviderApi.java  2020/10/16 14:25
 */
@FeignClient("consul-provider")
public interface ProviderApi {

    /**
     * 调用 provider 项目，ProviderController.java 里的StudentIntro方法
     */
    @GetMapping("/provider/user/student/intro")
    String StudentIntro() ;

    @GetMapping("/provider/user/student/intro")
    String TeachIntro();
}
