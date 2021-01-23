package com.aaa.schedule.scheduling2.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

/**
 * 任务解析器选择器
 * @author liuzhen.tian
 * @version 1.0 TaskSolverChooser.java  2020/12/11 15:41
 */
@Slf4j
@Component
public class TaskGroupSolverChooser implements ApplicationContextAware {

    private ApplicationContext applicationContext;

    private Map<String, TaskGroupService> chooseMap = new HashMap<>(16);

    /**
     * 拿到spring context 上下文
     */
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    @PostConstruct
    private void registerToTaskSolver(){
        Map<String, TaskGroupService> taskServiceMap = applicationContext.getBeansOfType(TaskGroupService.class);
        for (TaskGroupService value : taskServiceMap.values()) {
            chooseMap.put(value.jobId(), value);
            log.info("task {} 处理器: {} 注册成功",new Object[]{value.jobId(),value});
        }
    }

    /**
     * 获取需要的job
     */
    public TaskGroupService getTask(String jobId){
        return chooseMap.get(jobId);
    }
}
