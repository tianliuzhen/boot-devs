package com.aaa.schedule.scheduling2.service.impl;

import com.aaa.schedule.scheduling2.service.TaskGroupService;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @author liuzhen.tian
 * @version 1.0 TaskServiceV1Impl.java  2020/12/11 15:42
 */
@Service
public class TaskGroupServiceJob1Impl implements TaskGroupService {
    @Override
    public void HandlerJob() {
        System.out.println("------job1 开始执行---------：" + new Date());
    }

    @Override
    public String jobId() {
        return "taskGroupServiceJob1Impl";
    }

    public void printTaskGroup1(){
        System.out.println("------printTaskGroup1 开始执行---------：" + new Date());
    }
    public void printTaskGroup2(){
        System.out.println("------printTaskGroup2 开始执行---------：" + new Date());
    }
}
