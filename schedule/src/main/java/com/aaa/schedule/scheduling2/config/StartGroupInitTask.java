package com.aaa.schedule.scheduling2.config;

import com.aaa.schedule.scheduling2.domain.TaskGroupEntity;
import com.aaa.schedule.scheduling2.domain.TaskMethods;
import com.aaa.schedule.scheduling2.task.MyGroupScheduledTask;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 这里的作用为了，程序启动时首次去加我们设置的task
 * @author liuzhen.tian
 * @version 1.0 StartTask.java  2020/12/11 15:14
 */

// @Component
public class StartGroupInitTask implements CommandLineRunner {

    @Autowired
    private MyGroupScheduledTask myGroupScheduledTask;

    @Override
    public void run(String... args) throws Exception {
        // 这里模拟存在数据库的数据
        TaskGroupEntity taskGroupEntity = new TaskGroupEntity("taskGroupServiceJob1Impl", "分组测试1");
        List<TaskMethods> taskMethodsList = new ArrayList<>();
        taskMethodsList.add(TaskMethods.builder().methodName("printTaskGroup1").methodCron("0/1 * *  * * ?").build());
        taskMethodsList.add(TaskMethods.builder().methodName("printTaskGroup2").methodCron("0/1 * *  * * ?").build());
        taskGroupEntity.setTaskMethodsList(taskMethodsList);

        List<TaskGroupEntity>  list = Arrays.asList(taskGroupEntity);
        myGroupScheduledTask.refresh(list);



    }
}
