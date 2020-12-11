package com.aaa.schedule.quartz.demo.demo2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * @author liuzhen.tian
 * @version 1.0 MyStartupRunner.java  2020/12/11 17:38
 */
@Component
public class MyStartupRunner implements CommandLineRunner {

    @Autowired
    public CronSchedulerJob scheduleJobs;

    @Override
    public void run(String... args) throws Exception {
        scheduleJobs.scheduleJobs();
        System.out.println(">>>>>>>>>>>>>>>定时任务开始执行<<<<<<<<<<<<<");
    }
}
