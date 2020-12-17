package com.aaa.schedule.scheduling.config;

import com.aaa.schedule.scheduling.domain.TaskEntity;
import com.aaa.schedule.scheduling.task.MyScheduledTask;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

/**
 * 这里的作用为了，程序启动时首次去加我们设置的task
 * @author liuzhen.tian
 * @version 1.0 StartTask.java  2020/12/11 15:14
 */

@Component
public class StartInitTask implements CommandLineRunner {

    @Autowired
    private MyScheduledTask myScheduledTask;

    @Override
    public void run(String... args) throws Exception {
        List<TaskEntity> list = Arrays.asList(
                new TaskEntity(1, "测试1", "0/1 * *  * * ?"),
                new TaskEntity(2, "测试2", "0/1 * *  * * ?")
        );
        myScheduledTask.refresh(list);
    }
}
