package com.aaa.schedule.quartz.demo.demo2;

import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.stereotype.Component;

/**
 * @author liuzhen.tian
 * @version 1.0 CronSchedulerJob.java  2020/12/11 17:35
 */
@Component
public class CronSchedulerJob {

    @Autowired
    private SchedulerFactoryBean schedulerFactoryBean;

    private void scheduleJob1(Scheduler scheduler) throws SchedulerException {
        JobDetail jobDetail = JobBuilder.newJob(ScheduledJob.class) .withIdentity("job1", "group1").build();
        // 6的倍数秒执行 也就是 6 12 18 24 30 36 42 ....
        CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule("0/6 * * * * ?");
        CronTrigger cronTrigger = TriggerBuilder.newTrigger().withIdentity("trigger1", "group1")
                .usingJobData("name","任务1").withSchedule(scheduleBuilder).build();
        scheduler.scheduleJob(jobDetail,cronTrigger);
    }

    private void scheduleJob2(Scheduler scheduler) throws SchedulerException {
        JobDetail jobDetail = JobBuilder.newJob(ScheduledJob2.class) .withIdentity("job2", "group2").build();
        // 12秒的倍数执行  12  24 36  48  60
        CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule("0/12 * * * * ?");
        CronTrigger cronTrigger = TriggerBuilder.newTrigger().withIdentity("trigger2", "group2")
                .usingJobData("name","任务2").withSchedule(scheduleBuilder).build();
        scheduler.scheduleJob(jobDetail,cronTrigger);
    }

    /**
     * @Author Smith
     * @Description 同时启动两个定时任务
     * @return void
     **/
    public void scheduleJobs() throws SchedulerException {
        Scheduler scheduler = schedulerFactoryBean.getScheduler();
        scheduleJob1(scheduler);
        scheduleJob2(scheduler);
    }

    public void removeJobs() throws SchedulerException {
        Scheduler scheduler = schedulerFactoryBean.getScheduler();
        scheduler.deleteJob(new JobKey("sampleJob","group1"));
        scheduler.deleteJob(new JobKey("job1","group1"));
        scheduler.deleteJob(new JobKey("job2","group2"));
        scheduler.deleteJob(new JobKey("job2","group2"));
        scheduler.deleteJob(new JobKey("job1-c1415676-84e5-48b9-b621-0fbb24441cf6","group1"));
        scheduler.deleteJob(new JobKey("job1-e1b8ddfe-4bf6-4258-8a50-5e6201b219de","group1"));
        scheduler.deleteJob(new JobKey("job2-19b30990-dd61-43dc-8de1-9a60f7be50d4","group2"));
    }
}
