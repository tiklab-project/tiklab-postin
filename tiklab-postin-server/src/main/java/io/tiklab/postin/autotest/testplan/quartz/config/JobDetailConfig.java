package io.tiklab.postin.autotest.testplan.quartz.config;

import io.tiklab.postin.autotest.testplan.quartz.utils.QuartzUtils;
import org.quartz.JobBuilder;
import org.quartz.JobDataMap;
import org.quartz.JobDetail;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class JobDetailConfig {

    public JobDetail jobDetail(String group,String testPlanId,String quartzPlanId,String cron,Integer exeType) {

        JobDataMap jobDataMap = new JobDataMap();
        jobDataMap.put("group",group);
        jobDataMap.put("testPlanId",testPlanId);
        jobDataMap.put("quartzPlanId",quartzPlanId);
        jobDataMap.put("cron",cron);

        if(exeType == 1){
            Map<String, String> map = QuartzUtils.cronWeek(cron);
            String weekTime = map.get("weekTime");
            jobDataMap.put("weekTime",weekTime);
        }

        jobDataMap.put("exeType",exeType);

        //job名称
        String jobName = group + "_" + quartzPlanId+ "_" +testPlanId;

        //指定任务描述具体的实现类
        return JobBuilder.newJob(ExecuteJob.class)
                .setJobData(jobDataMap)
                // 指定任务的名称
                .withIdentity(jobName,group)
                .build();
    }


}
