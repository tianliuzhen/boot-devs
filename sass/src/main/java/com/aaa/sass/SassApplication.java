package com.aaa.sass;

import com.aaa.sass.config.LoggingListener;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@Slf4j
@SpringBootApplication
public class SassApplication {

    public static void main(String[] args) {
        // SpringApplication.run(SassApplication.class, args);
        SpringApplication application = new SpringApplication(SassApplication.class);
        // 添加 日志监听器，使 log4j2-spring.xml 可以间接读取到配置文件的属性
        application.addListeners(new LoggingListener());
        application.run(args);
        log.info("SassApplication start success...");
    }

}
