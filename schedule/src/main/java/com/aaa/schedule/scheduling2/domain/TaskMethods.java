package com.aaa.schedule.scheduling2.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

/**
 * @author liuzhen.tian
 * @version 1.0 TaskMehods.java  2021/1/23 17:04
 */
@Data
@Builder
@AllArgsConstructor
public class TaskMethods {

    /**
     * 该方法的cron表达式
     */
    private String methodCron;

    /**
     * 方法名
     * 命名规则：  类名+方法名
     */
    private String methodName;

    /**
     * 该方法描述
     */
    private String methodDesc;
}
