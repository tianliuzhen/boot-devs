package com.aaa.schedule.scheduling.task;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * 参考：https://blog.csdn.net/a718515028/article/details/80396102
 * @author liuzhen.tian
 * @version 1.0 SimpleScheduleConfig.java  2020/12/8 10:50
 */
@Component
public class SimpleTask {

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

    //5秒一次
    @Scheduled(cron = "*/5 * * * * ?")
    public void task1() throws InterruptedException {
        System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()) + "    " + Thread.currentThread().getName() + "    任务一启动");
        Thread.sleep(10000);//任务耗时10秒
        System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()) + "    " + Thread.currentThread().getName() + "    结束");

    }

    @Scheduled(cron = "*/5 * * * * ?")
    public void task2() throws InterruptedException {
        System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()) + "    " + Thread.currentThread().getName() + "    任务二启动");
        Thread.sleep(10000);
        System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()) + "    " + Thread.currentThread().getName() + "    结束");
    }
}
