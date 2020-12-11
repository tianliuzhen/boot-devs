package com.aaa.schedule.task;

import com.aaa.schedule.domain.ScheduledtaskEntity;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
/**
 * 这种配置无法，无法实时刷新 新修改的cron 配置
 * @author liuzhen.tian
 * @version 1.0 ElasticsearchSchedultaskController.java  2020/12/10 17:59
 */
@Component
public class MyScheduledSimleTask implements SchedulingConfigurer {


    @Override
    public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {
        List<ScheduledtaskEntity> list = new ArrayList<>();
        try {
            list.forEach((cron)->{
                taskRegistrar.addTriggerTask(
                        //1.添加任务内容(Runnable)
                        () -> System.out.println("执行定时任务1: " + LocalDateTime.now().toLocalTime()),
                        //2.设置执行周期(Trigger)
                        triggerContext -> {
                            //2.1 从数据库获取执行周期
                            String cronExpression = cron.getCron();
                            //2.2 合法性校验.
                            if (StringUtils.hasLength(cronExpression)) {
                                // Omitted Code ..
                            }
                            //2.3 返回执行周期(Date)
                            return new CronTrigger(cronExpression).nextExecutionTime(triggerContext);
                        }
                );

            });

        }catch (Exception e){
            e.printStackTrace();
        }
    }

}
