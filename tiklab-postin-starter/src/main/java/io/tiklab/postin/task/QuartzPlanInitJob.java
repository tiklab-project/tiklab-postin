package io.tiklab.postin.task;

import io.tiklab.core.exception.ApplicationException;
import io.tiklab.install.runner.TiklabApplicationRunner;
import io.tiklab.postin.autotest.testplan.quartz.config.SchedulerConfig;
import io.tiklab.postin.autotest.testplan.quartz.model.QuartzPlan;
import io.tiklab.postin.autotest.testplan.quartz.model.QuartzTimePlan;
import io.tiklab.postin.autotest.testplan.quartz.service.QuartzPlanService;
import io.tiklab.postin.autotest.testplan.quartz.service.QuartzTimePlanService;
import io.tiklab.postin.autotest.testplan.quartz.utils.QuartzUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Scheduled;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Configuration
public class QuartzPlanInitJob implements TiklabApplicationRunner {
    private static final Logger logger = LoggerFactory.getLogger(QuartzPlanInitJob.class);

    @Autowired
    SchedulerConfig schedulerConfig;

    @Autowired
    QuartzPlanService quartzPlanService;

    @Autowired
    QuartzTimePlanService quartzTimePlanService;

    @Override
    public void run() {
        try {
            List<QuartzTimePlan> quartzTimePlanList = quartzTimePlanService.findAllQuartzTimePlan();
            if (Objects.isNull(quartzTimePlanList) || quartzTimePlanList.isEmpty()){
                return;
            }
            logger.info("Load scheduled tasks......");
            for (QuartzTimePlan quartzTimePlan : quartzTimePlanList) {
                String quartzPlanId = quartzTimePlan.getQuartzPlanId();

                if(quartzTimePlan.getExeType()==1){
                    String time = QuartzUtils.weekTime(quartzTimePlan.getCron());
                    Date date = QuartzUtils.StringChengeDate(time);
                    if (date.getTime() < System.currentTimeMillis()){
                        continue;
                    }
                }

                QuartzPlan quartzPlan = quartzPlanService.findQuartzPlan(quartzPlanId);

                schedulerConfig.scheduler(
                        "test",
                        quartzPlan.getTestPlanId(),
                        quartzPlanId,
                        quartzTimePlan.getCron(),
                        quartzTimePlan.getExeType()
                );
            }
            logger.info("Timed task loading completed!");
        }catch (Exception e) {
            logger.error(" Timed task loading  error : " + e.getMessage());
        }
    }


    @Scheduled(cron = "0 01 00 ? * 2")
    public void refreshTrigger() {
        List<QuartzTimePlan> quartzTimePlanList = quartzTimePlanService.findAllQuartzTimePlan();
        if (quartzTimePlanList.isEmpty()){
            return;
        }

        List<QuartzTimePlan> timeList = quartzTimePlanList.stream()
                .filter(triggerTime -> triggerTime.getExeType() != 1).toList();

        for (QuartzTimePlan quartzTimePlan : timeList) {
            String cron = quartzTimePlan.getCron();
            String quartzPlanId = quartzTimePlan.getQuartzPlanId();
            String testPlanId = quartzTimePlan.getTestPlanId();


            String[] split = cron.split(" ");

            int[] ints = {
                    Integer.parseInt(split[6]),
                    Integer.parseInt(split[4]),
                    Integer.parseInt(split[3]),
                    Integer.parseInt(split[2]),
                    Integer.parseInt(split[1])
            };

            // 指定时间
            LocalDateTime specifiedDateTime = LocalDateTime.of(ints[0],ints[1], ints[2], ints[3], ints[4], 0);

            Instant instant = specifiedDateTime.atZone(java.time.ZoneId.systemDefault()).toInstant();
            long timestamp = instant.toEpochMilli();

            if (timestamp > System.currentTimeMillis()){
                continue;
            }

            // 获取指定时间的7天后
            LocalDateTime dateTime = specifiedDateTime.plusDays(7);

            int year = dateTime.getYear();
            int month = dateTime.getMonthValue();
            int day = dateTime.getDayOfMonth();
            int hour = ints[3];
            int minute = ints[4];
            String newCron = "00 " + minute + " " + hour + " " + day + " " + month + " ? " + year;

            quartzTimePlan.setCron(newCron);
            quartzTimePlanService.updateQuartzTimePlan(quartzTimePlan);

            try {
                schedulerConfig.scheduler(
                        "test",
                        testPlanId,
                        quartzPlanId,
                        quartzTimePlan.getCron(),
                        quartzTimePlan.getExeType()
                );
            } catch (Exception e) {
                throw new ApplicationException(e);
            }
        }

    }





}
