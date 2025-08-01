package io.tiklab.postin.autotest.testplan.quartz.config;

import org.quartz.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class TriggerConfig {
    private static final Logger logger = LoggerFactory.getLogger(TriggerConfig.class);

    public Trigger trigger(JobDetail jobDetail, String group, String cron, String triggerName) {
        logger.info("Creating trigger with cron: {}, triggerName: {}", cron, triggerName);

        TriggerBuilder<CronTrigger> triggerBuilder = TriggerBuilder.newTrigger()
                .withIdentity(triggerName, group)
                .withSchedule(CronScheduleBuilder.cronSchedule(cron));

        triggerBuilder.forJob(jobDetail);

        return triggerBuilder.build();
    }
}
