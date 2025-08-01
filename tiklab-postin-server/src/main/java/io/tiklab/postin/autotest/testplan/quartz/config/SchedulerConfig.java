package io.tiklab.postin.autotest.testplan.quartz.config;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.Date;

@Service
public class SchedulerConfig {
    
    private static final Logger logger = LoggerFactory.getLogger(SchedulerConfig.class);

    @Autowired
    JobDetailConfig jobDetailConfig;

    @Autowired
    TriggerConfig triggerConfig;

    // 从 Factory 中获取 Scheduler 实例
    private static final SchedulerFactory schedulerFactory =  new StdSchedulerFactory();

    public void scheduler(String group,String testPlanId,String quartzPlanId,String cron,Integer exeType) {
        try {
            Scheduler scheduler = schedulerFactory.getScheduler();

            JobDetail jobDetail = jobDetailConfig.jobDetail(group, testPlanId, quartzPlanId,cron,exeType);

            String triggerName = "triggerName"+ "_" + group + "_" + quartzPlanId+ "_" +testPlanId;
            logger.info("create scheduler ... {}", triggerName);

            // 添加异常检查和日志
            try {
                CronExpression cronExpression = new CronExpression(cron);
                Date nextValidTimeAfter = cronExpression.getNextValidTimeAfter(new Date());

                if (nextValidTimeAfter == null) {
                    logger.error("Cron expression {} will never fire!", cron);
                    throw new RuntimeException("Cron expression will never fire: " + cron);
                }
            } catch (ParseException e) {
                logger.error("Invalid cron expression: {}", cron, e);
                throw new RuntimeException("Invalid cron expression: " + cron, e);
            }

            Trigger trigger = triggerConfig.trigger(jobDetail, group, cron, triggerName);

            scheduler.scheduleJob(jobDetail, trigger);
            // 启动
            if (!scheduler.isShutdown()) {
                scheduler.start();
            }

        }catch (SchedulerException e){
            throw new RuntimeException("Error while initializing scheduler", e);
        }
    }

    public void removeJob(String group, String testPlanId, String cron, String quartzPlanId) {

        try {
            Scheduler scheduler = schedulerFactory.getScheduler();

            //trigger名称
            String triggerName = "triggerName"+ "_" + group + "_" + quartzPlanId+ "_" +testPlanId;
            TriggerKey triggerKey = TriggerKey.triggerKey(triggerName,group);

            //job名称
            String jobName = group + "_" + quartzPlanId+ "_" +testPlanId;
            JobKey jobKey = JobKey.jobKey(jobName,group);

            // 原有的删除逻辑
            scheduler.pauseTrigger(triggerKey);
            scheduler.unscheduleJob(triggerKey);

            int size = scheduler.getTriggersOfJob(jobKey).size();

            if (size <= 1) {
                scheduler.deleteJob(jobKey);
                logger.warn("Job触发完成：" + jobKey + "，移除Job");
            }
        } catch (Exception e) {
            logger.error("Error removing job", e);
            throw new RuntimeException(e);
        }
    }
    

}
