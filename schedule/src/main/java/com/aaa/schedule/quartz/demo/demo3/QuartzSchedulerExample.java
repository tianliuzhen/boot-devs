package com.aaa.schedule.quartz.demo.demo3;

/**
 * @author liuzhen.tian
 * @version 1.0 QuartzSchedulerExample.java  2024/12/10 21:38
 */

import lombok.Setter;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import org.springframework.scheduling.quartz.QuartzJobBean;

public class QuartzSchedulerExample {
    public static void main(String[] args) throws SchedulerException {
        // 创建调度器工厂
        // 获取调度器实例
        Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();


        // 创建任务详情
        JobDetail detail = JobBuilder.newJob(SampleJob.class)
                .withIdentity("sampleJob")
                .usingJobData("key", "World!")
                .build();

        // 创建触发器
        CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule("0/6 * * * * ?");
        CronTrigger trigger = TriggerBuilder.newTrigger()
                .withIdentity("sampleJob")
                .usingJobData("key", "Hello!")
                .withSchedule(scheduleBuilder).build();

        // ... 其他代码，如创建任务和触发器，并添加到调度器中
        scheduler.scheduleJob(detail, trigger);

        // 启动调度器
        scheduler.start();


        // 关闭调度器（通常在应用程序结束时调用）
        // scheduler.shutdown();
    }

    // @DisallowConcurrentExecution 进制并发执行同一个job定义
    public static class SampleJob extends QuartzJobBean {

        @Setter
        private String key;


        @Override
        protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {
            System.out.println("Quartz ---->  Hello, " + this.key);
        }
    }

}
