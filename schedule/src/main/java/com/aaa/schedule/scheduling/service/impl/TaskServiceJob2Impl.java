package com.aaa.schedule.scheduling.service.impl;

import com.aaa.schedule.scheduling.service.TaskService;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author liuzhen.tian
 * @version 1.0 TaskServiceV1Impl.java  2020/12/11 15:42
 */
@Service
public class TaskServiceJob2Impl implements TaskService {
    @Override
    public void HandlerJob() {
        System.out.println("------job2 开始执行---------："+new Date());

        System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()) + "    " + Thread.currentThread().getName() + "    任务二启动");
        try {
            Thread.sleep(10000);//任务耗时10秒
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()) + "    " + Thread.currentThread().getName() + "    结束");

    }

    @Override
    public Integer jobId() {
        return 2;
    }
}
