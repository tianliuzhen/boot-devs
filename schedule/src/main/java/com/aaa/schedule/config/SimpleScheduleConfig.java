package com.aaa.schedule.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;


/**
 * 参考：https://blog.csdn.net/a718515028/article/details/80396102
 * @author liuzhen.tian
 * @version 1.0 SimpleScheduleConfig.java  2020/12/8 10:50
 */
@Configuration //1.主要用于标记配置类，兼备Component的效果。
@EnableScheduling // 2.开启定时任务
public class SimpleScheduleConfig {

    // /**
    //  * 允许并发执行
    //  */
    // @Bean
    // public TaskScheduler taskScheduler(){
    //     ThreadPoolTaskScheduler taskScheduler = new ThreadPoolTaskScheduler();
    //     taskScheduler.setPoolSize(10);
    //     taskScheduler.initialize();
    //     return taskScheduler;
    // }

    // @Scheduled(cron = "0/5 * *  * * ? ")
    public void startSchedule() {
        System.out.println("===========1=>");
        try {
            for(int i=1;i<=5;i++){
                System.out.println("=1==>"+i);
                Thread.sleep(1000);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    // @Scheduled(cron = "0/5 * *  * * ? ")
    public void startSchedule2() {
        for(int i=1;i<=5;i++){
            System.out.println("=2==>"+i);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
