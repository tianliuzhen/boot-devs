package com.aaa.schedule.scheduling2.task;

import com.aaa.schedule.scheduling2.domain.TaskGroupEntity;
import com.aaa.schedule.scheduling2.domain.TaskMethods;
import com.aaa.schedule.scheduling2.service.TaskGroupService;
import com.aaa.schedule.scheduling2.service.TaskGroupSolverChooser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.CronTask;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledFuture;
import java.util.stream.Collectors;

/**
 * 分组的task
 * @author liuzhen.tian
 * @version 1.0 MyGroupScheduledTask.java  2021/1/23 17:14
 */
@Component
public class MyGroupScheduledTask implements SchedulingConfigurer {

    private volatile ScheduledTaskRegistrar registrar;

    /**
     * 控制每个task,管理每个task生命周期,这里也是每个task的核心
    */
    private final ConcurrentHashMap<String, ScheduledFuture<?>> scheduledFutures = new ConcurrentHashMap<>();
    /**
     * 用于存放已经执行的task
     */
    private final ConcurrentHashMap<String, CronTask> cronTasks = new ConcurrentHashMap<>();

    /**
     * 自定义的task类选择器
     */
    @Autowired
    private TaskGroupSolverChooser taskGroupSolverChooser;

    @Override
    public void configureTasks(ScheduledTaskRegistrar registrar) {
        //设置20个线程,默认单线程,如果不设置的话，不能同时并发执行任务
        registrar.setScheduler(Executors.newScheduledThreadPool(10));
        this.registrar = registrar;
    }

    /**
     * 修改 cron 需要 调用该方法
     */
    public void refresh(List<TaskGroupEntity> tasks){
        //取消已经删除的策略任务
        cancelIsDel(tasks);

        //执行每一个类，每个类含有不同的cron方法
        for (TaskGroupEntity task : tasks) {
            //拿到每个类的类名
            String taskName = task.getTask();
            //得到 task父类解析器
            TaskGroupService taskChooser = taskGroupSolverChooser.getTask(taskName);

            //得到类下面需要执行的方法
            List<TaskMethods> taskMethodsList = task.getTaskMethodsList();
            for (TaskMethods taskMethods : taskMethodsList) {
                //要是cron 为空 跳过
                if(!StringUtils.hasLength(taskMethods.getMethodName())){
                    return;
                }
                //计划任务已存在并且表达式未发生变化则跳过
                if (scheduledFutures.containsKey(taskMethods.getMethodName())
                        && cronTasks.get(taskMethods.getMethodName()).getExpression().equals(taskMethods.getMethodCron())) {
                    return;
                }
                //如果策略执行时间发生了变化，则取消当前策略的任务
                if(scheduledFutures.containsKey(taskMethods.getMethodName())) {
                    scheduledFutures.get(taskMethods.getMethodName()).cancel(false);
                    scheduledFutures.remove(taskMethods.getMethodName());
                    cronTasks.remove(taskMethods.getMethodName());
                }
                //业务逻辑处理，利用反射动态执行方法
                CronTask cronTask = cronTask(taskMethods, taskChooser);

                //执行业务
                ScheduledFuture<?> future = registrar.getScheduler().schedule(cronTask.getRunnable(), cronTask.getTrigger());
                cronTasks.put(taskMethods.getMethodName(), cronTask);
                scheduledFutures.put(taskMethods.getMethodName(), future);

            }

        }
    }

    /**
     * 停止 cron 运行
     */
    public void stop(List<TaskGroupEntity> tasks){
        List<String> allTask = new ArrayList<>();
        //得到方法名集合
        if (!CollectionUtils.isEmpty(tasks)) {
            allTask = tasks.stream()
                    .flatMap(taskMethods -> taskMethods.getTaskMethodsList().stream())
                    .map(TaskMethods::getMethodName).collect(Collectors.toList());
        }
        for (String task : allTask) {
            if (scheduledFutures.containsKey(task)) {
                // mayInterruptIfRunning设成false话，不允许在线程运行时中断，设成true的话就允许。
                scheduledFutures.get(task).cancel(false);
                scheduledFutures.remove(task);
                cronTasks.remove(task);
            }
        }
    }

    /**
     * 利用反射动态执行方法
     * 业务逻辑处理
     */
    public CronTask cronTask(TaskMethods taskMethods,TaskGroupService taskChooser)  {
        return new CronTask(() -> {
            try {
                Method declaredMethod = taskChooser.getClass().getDeclaredMethod(taskMethods.getMethodName());
                declaredMethod.invoke(taskChooser.getClass().newInstance());
            } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException | InstantiationException e) {
                e.printStackTrace();
            }

        }, taskMethods.getMethodCron());
    }


    /**
     * 取消已经删除的策略任务
     * 用于初始化时，取消不存在的task
     * @param tasks
     * @return void
     */
    private void cancelIsDel(List<TaskGroupEntity> tasks) {
        List<String> allTask = new ArrayList<>();
        //得到方法名集合
        if (!CollectionUtils.isEmpty(tasks)) {
            //得到每个类的所有方法
            allTask = tasks.stream()
                    .flatMap(taskMethods -> taskMethods.getTaskMethodsList().stream())
                    .map(TaskMethods::getMethodName).collect(Collectors.toList());
        }
        Set<String> jobs = scheduledFutures.keySet();
        for (String job : jobs) {
            //如果task 当前不在，已经运行的 scheduledFutures 里面，则取消掉
            if (!allTask.contains(job)) {
                //让当前线程，执行完再结束。
                scheduledFutures.get(job).cancel(false);
            }
        }

    }


}
