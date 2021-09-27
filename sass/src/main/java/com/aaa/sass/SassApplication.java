package com.aaa.sass;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@Slf4j
@SpringBootApplication
public class SassApplication {

    public static void main(String[] args) {
        SpringApplication.run(SassApplication.class, args);
        log.info("SassApplication start success...");
    }

}
