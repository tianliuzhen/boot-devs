package com.aaa.schedule.scheduling2.web;

import com.aaa.schedule.scheduling2.domain.TaskGroupEntity;
import com.aaa.schedule.scheduling2.domain.TaskMethods;
import com.aaa.schedule.scheduling2.task.MyGroupScheduledTask;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author liuzhen.tian
 * @version 1.0 StartController.java  2020/12/11 10:03
 */
@RestController
@RequestMapping(value = "group")
public class TaskGroupController {

    @Autowired
    private MyGroupScheduledTask scheduledTask;

    @PostMapping(value = "/startOrChangeCron")
    public String changeCron(@RequestBody List<TaskGroupEntity> list){
        if (CollectionUtils.isEmpty(list)) {
            // 这里模拟存在数据库的数据
            TaskGroupEntity taskGroupEntity = new TaskGroupEntity("taskGroupServiceJob1Impl", "分组测试1");
            List<TaskMethods> taskMethodsList = new ArrayList<>();
            taskMethodsList.add(TaskMethods.builder().methodName("printTaskGroup1").methodCron("0/1 * *  * * ?").build());
            taskMethodsList.add(TaskMethods.builder().methodName("printTaskGroup2").methodCron("0/1 * *  * * ?").build());
            taskGroupEntity.setTaskMethodsList(taskMethodsList);

            list = Arrays.asList(taskGroupEntity);
        }
        scheduledTask.refresh(list);
        return "task任务:" + list.toString() + "已经开始运行";
    }

    @PostMapping(value = "/stopCron")
    public String stopCron(@RequestBody List<TaskGroupEntity> list){
        if (CollectionUtils.isEmpty(list)) {
            // 这里模拟将要停止的cron可通过前端传来
            TaskGroupEntity taskGroupEntity = new TaskGroupEntity("taskGroupServiceJob1Impl", "分组测试1");
            List<TaskMethods> taskMethodsList = new ArrayList<>();
            taskMethodsList.add(TaskMethods.builder().methodName("printTaskGroup1").methodCron("0/1 * *  * * ?").build());
            taskGroupEntity.setTaskMethodsList(taskMethodsList);

            list = Arrays.asList(taskGroupEntity);
        }
        scheduledTask.stop(list);
        List<String> collect = list.stream().flatMap(group -> group.getTaskMethodsList().stream())
                .map(TaskMethods::getMethodName).collect(Collectors.toList());

        return "task任务:" + collect.toString() + "已经停止启动";
    }


}
