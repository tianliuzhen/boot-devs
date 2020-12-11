package com.aaa.schedule.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;

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
public class TaskSolverChooser implements ApplicationContextAware {

    private ApplicationContext applicationContext;

    private Map<Integer, TaskService> chooseMap = new HashMap<>(16);

    /**
     * 拿到spring context 上下文
     */
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    @PostConstruct
    private void registerToTaskSolver(){
        Map<String, TaskService> taskServiceMap = applicationContext.getBeansOfType(TaskService.class);
        for (TaskService value : taskServiceMap.values()) {
            chooseMap.put(value.jobId(), value);
            log.info("task {} 处理器: {} 注册成功",new Object[]{value.jobId(),value});
        }
    }

    /**
     * 获取需要的job
     */
    public TaskService getTask(Integer jobId){
        return chooseMap.get(jobId);
    }
}
