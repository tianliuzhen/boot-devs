package com.aaa.schedule.scheduling.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author liuzhen.tian
 * @version 1.0 LogTask.java  2020/12/11 9:59
 */
@Data
@NoArgsConstructor
public class TaskEntity {
    /**
     * 任务id
     */
    private int taskId;
    /**
     * 任务说明
     */
    private String desc;
    /**
     * cron 表达式
     */
    private String expression;

    public TaskEntity(int taskId, String desc, String expression) {
        this.taskId = taskId;
        this.desc = desc;
        this.expression = expression;
    }
}
