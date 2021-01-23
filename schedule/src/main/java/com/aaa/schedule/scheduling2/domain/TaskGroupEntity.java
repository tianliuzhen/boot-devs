package com.aaa.schedule.scheduling2.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 这里为方法分组，把多个方法归为一个类里面。
 * 自定义一个  含有多个方法的类，
 * 该类的多个方法，设置不用的cron
 *
 * @author liuzhen.tian
 * @version 1.0 LogTask.java  2020/12/11 9:59
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TaskGroupEntity {
    /**
     * 任务id，这里使用类名
     * 用于注册到任务解析器选择器
     */
    private String task;
    /**
     * 任务类说明
     */
    private String desc;

    /**
     * 该类的方法
     */
    private List<TaskMethods> taskMethodsList;

    public TaskGroupEntity(String task, String desc) {
        this.task = task;
        this.desc = desc;
    }
}
