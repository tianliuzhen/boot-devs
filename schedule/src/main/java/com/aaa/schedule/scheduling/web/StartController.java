package com.aaa.schedule.scheduling.web;

import com.aaa.schedule.scheduling.domain.TaskEntity;
import com.aaa.schedule.scheduling.task.MyScheduledTask;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author liuzhen.tian
 * @version 1.0 StartController.java  2020/12/11 10:03
 */
@RestController
public class StartController {

    @Autowired
    private MyScheduledTask scheduledTask;

    @PostMapping(value = "/startOrChangeCron")
    public String changeCron(@RequestBody List<TaskEntity> list){
        if (CollectionUtils.isEmpty(list)) {
            list = Arrays.asList(
                    new TaskEntity(1, "测试1","0/1 * *  * * ?") ,
                    new TaskEntity(2, "测试2","0/1 * *  * * ?")
            );
        }
        scheduledTask.refresh(list);
        return "task任务:" + list.toString() + "已经开始运行";
    }

    @PostMapping(value = "/stopCron")
    public String stopCron(@RequestBody List<TaskEntity> list){
        if (CollectionUtils.isEmpty(list)) {
            list = Arrays.asList(
                    new TaskEntity(1, "测试1","0/1 * *  * * ?") ,
                    new TaskEntity(2, "测试2","0/1 * *  * * ?")
            );
        }
        scheduledTask.stop(list);
        List<Integer> collect = list.stream().map(TaskEntity::getTaskId).collect(Collectors.toList());
        return "task任务:" + collect.toString() + "已经停止启动";
    }

}
