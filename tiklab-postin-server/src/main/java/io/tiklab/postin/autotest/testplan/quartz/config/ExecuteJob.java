package io.tiklab.postin.autotest.testplan.quartz.config;

import io.tiklab.postin.common.MagicValue;
import io.tiklab.postin.autotest.testplan.cases.model.TestPlan;
import io.tiklab.postin.autotest.testplan.cases.service.TestPlanCaseService;
import io.tiklab.postin.autotest.testplan.cases.service.TestPlanService;
import io.tiklab.postin.autotest.testplan.execute.model.TestPlanTestData;
import io.tiklab.postin.autotest.testplan.execute.model.TestPlanTestResponse;
import io.tiklab.postin.autotest.testplan.execute.service.TestPlanExecuteDispatchService;
import io.tiklab.postin.autotest.testplan.quartz.model.QuartzPlan;
import io.tiklab.postin.autotest.testplan.quartz.service.QuartzPlanService;
import io.tiklab.postin.support.environment.model.EnvServer;
import io.tiklab.postin.support.environment.service.EnvServerService;
import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class ExecuteJob implements Job {
    private static final Logger logger = LoggerFactory.getLogger(ExecuteJob.class);

    public static QuartzPlanService quartzPlanService;
    public static SchedulerConfig schedulerConfig;
    public static TestPlanExecuteDispatchService testPlanExecuteDispatchService;
    public static TestPlanService testPlanService;
    public static EnvServerService apiEnvService;
    public static TestPlanCaseService testPlanCaseService;

    @Autowired
    public  void setQuartzPlanService(QuartzPlanService quartzPlanService) {
        ExecuteJob.quartzPlanService = quartzPlanService;
    }

    @Autowired
    public void setSchedulerConfig(SchedulerConfig schedulerConfig) {
        ExecuteJob.schedulerConfig = schedulerConfig;
    }

    @Autowired
    public void setTestPlanExecuteDispatchService(TestPlanExecuteDispatchService testPlanExecuteDispatchService) {
        ExecuteJob.testPlanExecuteDispatchService = testPlanExecuteDispatchService;
    }

    @Autowired
    public void setTestPlanService(TestPlanService testPlanService) {
        ExecuteJob.testPlanService = testPlanService;
    }

    @Autowired
    public void setApiEnvService(EnvServerService apiEnvService) {
        ExecuteJob.apiEnvService = apiEnvService;
    }

    @Autowired
    public void setTestPlanCaseService(TestPlanCaseService testPlanCaseService) {
        ExecuteJob.testPlanCaseService = testPlanCaseService;
    }


    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        JobDataMap map = jobExecutionContext.getMergedJobDataMap();
        String testPlanId = (String)map.get("testPlanId");
        String quartzPlanId = (String)map.get("quartzPlanId");
        String group = (String)map.get("group");
        String cron = (String)map.get("cron");
        int exeType = (int)map.get("exeType");
        logger.info("组：{}，测试计划：{},当前定时任务: {}, 定时任务触发开始",group,testPlanId,quartzPlanId);

        TestPlan testPlan = testPlanService.findTestPlan(testPlanId);
        if(testPlan==null){return;}
        TestPlanTestData testPlanTestData = new TestPlanTestData();
        testPlanTestData.setTestPlanId(testPlanId);
        testPlanTestData.setWorkspaceId(testPlan.getWorkspaceId());

        Map<String, Integer> caseTypeNum = testPlanCaseService.getCaseTypeNum(testPlanId);
        if(!(caseTypeNum.get(MagicValue.CASE_TYPE_API_UNIT) ==null) || !(caseTypeNum.get(MagicValue.CASE_TYPE_API_SCENE) ==null)){
            if(testPlan.getApiEnvId()==null){
                logger.error("Api Env is null");
                updateQuartz(group,testPlanId,quartzPlanId,cron,exeType);
                return;
            }

            EnvServer apiEnv = apiEnvService.findEnvServer(testPlan.getApiEnvId());
            if(apiEnv==null){
                return;
            }

            testPlanTestData.setApiPreUrl(apiEnv.getUrl());
            testPlanTestData.setApiEnvId(apiEnv.getId());
        }

        logger.info(" --------------  start execute plan ------------------ ");
        //执行测试计划
        testPlanExecuteDispatchService.execute(testPlanTestData);

        //循环获取返回结果
        loopExeResult(testPlanId);
        //执行完成再获取一下返回结果，用于更新历史数据
        testPlanExecuteDispatchService.exeResult(testPlanId);

        if(exeType==1){
            schedulerConfig.removeJob(group,testPlanId,cron,quartzPlanId);
        }

        updateQuartz(group,testPlanId,quartzPlanId,cron,exeType);
    }

    private void loopExeResult(String testPlanId){
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        TestPlanTestResponse testPlanTestResponse = testPlanExecuteDispatchService.exeResult(testPlanId);

        if(testPlanTestResponse.getStatus()==1){
            loopExeResult(testPlanId);
        }

        testPlanExecuteDispatchService.cleanUpExecutionData(testPlanId);
    }

    private void updateQuartz(String group,String testPlanId,String quartzPlanId,String cron,Integer exeType){
        QuartzPlan quartzPlan = new QuartzPlan();
        quartzPlan.setId(quartzPlanId);
        quartzPlan.setTestPlanId(testPlanId);
        quartzPlan.setState(1);
        quartzPlanService.updateQuartzPlanState(quartzPlan);

        logger.info("update ----  success");
    }


}
