package com.aaa.schedule.scheduling.task;

import com.aaa.schedule.scheduling.domain.TaskEntity;
import com.aaa.schedule.scheduling.service.TaskSolverChooser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.CronTask;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.annotation.PreDestroy;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledFuture;

/**
 * @author liuzhen.tian
 * @version 1.0 ScheduledTask.java  2020/12/11 9:58
 */
@Component
public class MyScheduledTask implements SchedulingConfigurer {

    private volatile ScheduledTaskRegistrar registrar;

    private final ConcurrentHashMap<Integer, ScheduledFuture<?>> scheduledFutures = new ConcurrentHashMap<>();
    private final ConcurrentHashMap<Integer, CronTask> cronTasks = new ConcurrentHashMap<>();

    @Autowired
    private TaskSolverChooser taskSolverChooser;

    @Override
    public void configureTasks(ScheduledTaskRegistrar registrar) {
        //默认是单线程 org.springframework.scheduling.config.ScheduledTaskRegistrar.scheduleTasks

        //设置20个线程,默认单线程,如果不设置的话，不能同时并发执行任务
        registrar.setScheduler(Executors.newScheduledThreadPool(10));
        this.registrar = registrar;
    }

    /**
     * 修改 cron 需要 调用该方法
     */
    public void refresh(List<TaskEntity> tasks){
        //取消已经删除的策略任务
        Set<Integer> sids = scheduledFutures.keySet();
        for (Integer sid : sids) {
            if(!exists(tasks, sid)){
                scheduledFutures.get(sid).cancel(false);
            }
        }
        for (TaskEntity taskEntity : tasks) {
            String expression = taskEntity.getExpression();
            //计划任务表达式为空则跳过
            if(!StringUtils.hasLength(expression)){
                continue;
            }
            //计划任务已存在并且表达式未发生变化则跳过
            if (scheduledFutures.containsKey(taskEntity.getTaskId())
                    && cronTasks.get(taskEntity.getTaskId()).getExpression().equals(expression)) {
                continue;
            }
            //如果策略执行时间发生了变化，则取消当前策略的任务
            if(scheduledFutures.containsKey(taskEntity.getTaskId())){
                scheduledFutures.get(taskEntity.getTaskId()).cancel(false);
                scheduledFutures.remove(taskEntity.getTaskId());
                cronTasks.remove(taskEntity.getTaskId());
            }
            //业务逻辑处理
            CronTask task = cronTask(taskEntity, expression);


            //执行业务
            registrar.addTriggerTask(task.getRunnable(), task.getTrigger());
            ScheduledFuture<?> future = registrar.getScheduler().schedule(task.getRunnable(), task.getTrigger());
            cronTasks.put(taskEntity.getTaskId(), task);
            scheduledFutures.put(taskEntity.getTaskId(), future);
        }
    }

    /**
     * 停止 cron 运行
     */
    public void stop(List<TaskEntity> tasks){
        tasks.forEach(item->{
            if (scheduledFutures.containsKey(item.getTaskId())) {
                // mayInterruptIfRunning设成false话，不允许在线程运行时中断，设成true的话就允许。
                scheduledFutures.get(item.getTaskId()).cancel(false);
                scheduledFutures.remove(item.getTaskId());
            }
        });
    }

    /**
     * 业务逻辑处理
     */
    public CronTask cronTask(TaskEntity TaskEntity, String expression)  {
        return new CronTask(() -> {
                    //每个计划任务实际需要执行的具体业务逻辑
                    //采用策略，模式 ，执行我们的job
                   taskSolverChooser.getTask(TaskEntity.getTaskId()).HandlerJob();
                }, expression);
    }

    private boolean exists(List<TaskEntity> tasks, Integer tid){
        for(TaskEntity TaskEntity:tasks){
            if(TaskEntity.getTaskId() == tid){
                return true;
            }
        }
        return false;
    }

    @PreDestroy
    public void destroy() {
        this.registrar.destroy();
    }

}

