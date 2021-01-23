package com.aaa.schedule.scheduling2.service.impl;

import com.aaa.schedule.scheduling2.service.TaskGroupService;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @author liuzhen.tian
 * @version 1.0 TaskServiceV1Impl.java  2020/12/11 15:42
 */
@Service
public class TaskGroupServiceJob2Impl implements TaskGroupService {
    @Override
    public void HandlerJob() {
        System.out.println("-----job2 开始执行-------："+new Date());
    }

    @Override
    public String jobId() {
        // this.getClass().getName()
        return "taskGroupServiceJob2Impl";
    }

}
