package com.aaa.schedule.quartz.demo.demo1;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

/**
 * 参考：https://www.cnblogs.com/wadmwz/p/10315481.html
 * @author liuzhen.tian
 * @version 1.0 SampleJob.java  2020/12/11 17:17
 */
public class SampleJob extends QuartzJobBean {

    private String name;

    public void setName(String name) {
        this.name = name;
    }

    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        System.out.println("Quartz ---->  Hello, " + this.name);
    }
}
