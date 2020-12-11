package com.aaa.schedule.quartz.demo.demo2;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author liuzhen.tian
 * @version 1.0 ScheduledJob2.java  2020/12/11 17:32
 */
public class ScheduledJob2 implements Job {

    private String name;

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
        System.out.println("CRON ----> schedule job2 is running ... + " + name + "  ---->  " + dateFormat.format(new Date()));
    }
}
