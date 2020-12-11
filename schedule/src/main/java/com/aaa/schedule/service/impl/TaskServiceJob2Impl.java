package com.aaa.schedule.service.impl;

import com.aaa.schedule.service.TaskService;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @author liuzhen.tian
 * @version 1.0 TaskServiceV1Impl.java  2020/12/11 15:42
 */
@Service
public class TaskServiceJob2Impl implements TaskService {
    @Override
    public void HandlerJob() {
        System.out.println("-----job2 开始执行-------："+new Date());
    }

    @Override
    public Integer jobId() {
        return 2;
    }
}
