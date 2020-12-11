package com.aaa.schedule.task;

import com.aaa.schedule.domain.TaskEntity;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.CronTask;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.annotation.PreDestroy;
import java.util.Date;
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
public class ScheduledTask implements SchedulingConfigurer {

    private volatile ScheduledTaskRegistrar registrar;

    private final ConcurrentHashMap<Integer, ScheduledFuture<?>> scheduledFutures = new ConcurrentHashMap<Integer, ScheduledFuture<?>>();
    private final ConcurrentHashMap<Integer, CronTask> cronTasks = new ConcurrentHashMap<Integer, CronTask>();

    @Override
    public void configureTasks(ScheduledTaskRegistrar registrar) {
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
        for (TaskEntity TaskEntity : tasks) {
            String expression = TaskEntity.getExpression();
            //计划任务表达式为空则跳过
            if(!StringUtils.hasLength(expression)){
                continue;
            }
            //计划任务已存在并且表达式未发生变化则跳过
            if (scheduledFutures.containsKey(TaskEntity.getTaskId())
                    && cronTasks.get(TaskEntity.getTaskId()).getExpression().equals(expression)) {
                continue;
            }
            //如果策略执行时间发生了变化，则取消当前策略的任务
            if(scheduledFutures.containsKey(TaskEntity.getTaskId())){
                scheduledFutures.get(TaskEntity.getTaskId()).cancel(false);
                scheduledFutures.remove(TaskEntity.getTaskId());
                cronTasks.remove(TaskEntity.getTaskId());
            }
            //业务逻辑处理
            CronTask task = cronTask(TaskEntity, expression);


            //执行业务
            ScheduledFuture<?> future = registrar.getScheduler().schedule(task.getRunnable(), task.getTrigger());
            cronTasks.put(TaskEntity.getTaskId(), task);
            scheduledFutures.put(TaskEntity.getTaskId(), future);
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
                    //这里可以采用策略，模式
                    if (TaskEntity.getTaskId() ==1) {
                        System.out.println(TaskEntity.getTaskId() +"----"+ new Date());

                    }
                    if (TaskEntity.getTaskId() ==2){
                        System.out.println(TaskEntity.getTaskId() +"----"+ new Date());
                    }
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

