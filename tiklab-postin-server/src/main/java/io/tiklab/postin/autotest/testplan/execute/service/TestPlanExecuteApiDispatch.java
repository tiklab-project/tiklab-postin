package io.tiklab.postin.autotest.testplan.execute.service;

import io.tiklab.postin.common.MagicValue;
import io.tiklab.postin.autotest.http.perf.execute.model.ApiPerfTestRequest;
import io.tiklab.postin.autotest.http.perf.execute.model.ApiPerfTestResponse;
import io.tiklab.postin.autotest.http.perf.execute.service.ApiPerfExecuteDispatchService;
import io.tiklab.postin.autotest.http.perf.instance.model.ApiPerfInstance;
import io.tiklab.postin.autotest.http.perf.instance.service.ApiPerfInstanceService;
import io.tiklab.postin.autotest.testplan.execute.model.TestPlanTestData;
import io.tiklab.postin.autotest.testplan.instance.model.TestPlanCaseInstanceBind;
import io.tiklab.postin.autotest.testplan.instance.service.TestPlanCaseInstanceBindService;
import io.tiklab.postin.autotest.http.scene.cases.model.ApiSceneCase;
import io.tiklab.postin.autotest.http.scene.instance.model.ApiSceneInstance;
import io.tiklab.postin.autotest.http.scene.execute.model.ApiSceneTestRequest;
import io.tiklab.postin.autotest.http.scene.execute.model.ApiSceneTestResponse;
import io.tiklab.postin.autotest.http.scene.instance.service.ApiSceneInstanceService;
import io.tiklab.postin.autotest.http.scene.execute.service.ApiSceneExecuteDispatchService;
import io.tiklab.postin.autotest.http.unit.cases.model.ApiUnitCase;
import io.tiklab.postin.autotest.http.unit.instance.model.ApiUnitInstance;
import io.tiklab.postin.autotest.http.unit.execute.model.ApiUnitTestRequest;
import io.tiklab.postin.autotest.http.unit.instance.service.ApiUnitInstanceService;
import io.tiklab.postin.autotest.http.unit.execute.service.ApiUnitExecuteDispatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Objects;

/**
 * 测试计划中接口的执行测试
 */
@Component
public class TestPlanExecuteApiDispatch {

    @Autowired
    ApiUnitExecuteDispatchService apiUnitExecuteDispatchService;

    @Autowired
    ApiUnitInstanceService apiUnitInstanceService;

    @Autowired
    ApiSceneInstanceService apiSceneInstanceService;

    @Autowired
    ApiSceneExecuteDispatchService apiSceneExecuteDispatchService;

    @Autowired
    TestPlanCaseInstanceBindService testPlanCaseInstanceBindService;

    @Autowired
    ApiPerfExecuteDispatchService apiPerfExecuteDispatchService;

    @Autowired
    ApiPerfInstanceService apiPerfInstanceService;

    /**
     * 执行接口单元用例
     * @param testPlanCaseInstanceBind
     * @param testPlanTestData
     * @return
     */
    public TestPlanCaseInstanceBind exeApiUnit(TestPlanCaseInstanceBind testPlanCaseInstanceBind, TestPlanTestData testPlanTestData){
        String caseId = testPlanCaseInstanceBind.getCaseId();



        //构造执行api 单元用例 所需的参数
        ApiUnitTestRequest apiUnitTestRequest = new ApiUnitTestRequest();

        ApiUnitCase apiUnitCase = new ApiUnitCase();
        apiUnitCase.setId(caseId);
        apiUnitTestRequest.setApiUnitCase(apiUnitCase);
        apiUnitTestRequest.setApiEnvId(testPlanTestData.getApiEnvId());

        try {
            //执行
            ApiUnitInstance apiUnitInstance = apiUnitExecuteDispatchService.executeStart(apiUnitTestRequest);

            //保存历史
            String apiUnitInstanceId = apiUnitInstanceService.saveApiUnitInstanceToSql(apiUnitInstance);

            //测试计划历史 与 绑定用例的历史 公共历史表
            testPlanCaseInstanceBind.setCaseInstanceId(apiUnitInstanceId);
            testPlanCaseInstanceBind.setResult(apiUnitInstance.getResult());
            testPlanCaseInstanceBind.setStatus(0);
            testPlanCaseInstanceBind.setElapsedTime(apiUnitInstance.getElapsedTime().intValue());
            testPlanCaseInstanceBindService.updateTestPlanCaseInstanceBind(testPlanCaseInstanceBind);
        }catch (Exception e){
            testPlanCaseInstanceBind.setStatus(0);
        }

        return testPlanCaseInstanceBind;
    }


    /**
     * 执行接口场景用例
     * @param testPlanTestData
     * @return
     */
    public TestPlanCaseInstanceBind exeApiScene(TestPlanCaseInstanceBind testPlanCaseInstanceBind, TestPlanTestData testPlanTestData){
        ApiSceneTestRequest apiSceneTestRequest = new ApiSceneTestRequest();

        ApiSceneCase apiSceneCase = new ApiSceneCase();
        String caseId = testPlanCaseInstanceBind.getCaseId();
        apiSceneCase.setId(caseId);

        apiSceneTestRequest.setApiSceneCase(apiSceneCase);
        apiSceneTestRequest.setApiEnvId(testPlanTestData.getApiEnvId());

        try {
            //执行
            ApiSceneTestResponse apiSceneTestResponse = apiSceneExecuteDispatchService.executeStart(apiSceneTestRequest);
            ApiSceneInstance apiSceneInstance = apiSceneTestResponse.getApiSceneInstance();

            //保存历史
            String apiSceneInstanceId = apiSceneInstanceService.createApiSceneInstance(apiSceneInstance);
            //步骤历史
            apiSceneInstanceService.createWebStepInstance(apiSceneTestResponse.getStepCommonInstanceList(),apiSceneInstanceId);

            //测试计划历史 与 绑定用例的历史 公共历史
            testPlanCaseInstanceBind.setCaseInstanceId(apiSceneInstanceId);
            testPlanCaseInstanceBind.setResult(apiSceneTestResponse.getApiSceneInstance().getResult());
            testPlanCaseInstanceBind.setStatus(0);
            testPlanCaseInstanceBind.setElapsedTime(apiSceneTestResponse.getApiSceneInstance().getElapsedTime());
            testPlanCaseInstanceBindService.updateTestPlanCaseInstanceBind(testPlanCaseInstanceBind);
        }catch (Exception e){
            testPlanCaseInstanceBind.setStatus(0);
        }

        return testPlanCaseInstanceBind;
    }

    public void exeApiPerform(TestPlanCaseInstanceBind testPlanCaseInstanceBind, TestPlanTestData testPlanTestData){
        ApiPerfTestRequest apiPerfTestRequest = new ApiPerfTestRequest();
        apiPerfTestRequest.setApiPerfId(testPlanCaseInstanceBind.getCaseId());
        apiPerfTestRequest.setApiEnvId(testPlanTestData.getApiEnvId());
        //使用默认agent
        apiPerfTestRequest.setAgentId("agent-default_localhost");

        apiPerfExecuteDispatchService.executeStart(apiPerfTestRequest);
    }


    public TestPlanCaseInstanceBind apiPerformResult(TestPlanCaseInstanceBind testPlanCaseInstanceBind){
        String caseId = testPlanCaseInstanceBind.getCaseId();

        ApiPerfTestRequest apiPerfTestRequest = new ApiPerfTestRequest();
        apiPerfTestRequest.setApiPerfId(caseId);
        try {
            ApiPerfTestResponse apiPerfTestResponse = apiPerfExecuteDispatchService.getResult(apiPerfTestRequest);
            if(apiPerfTestResponse != null && apiPerfTestResponse.getApiPerfInstance() != null){
                ApiPerfInstance apiPerfInstance = apiPerfTestResponse.getApiPerfInstance();

                Integer elapsedTime = apiPerfInstance.getElapsedTime();
                testPlanCaseInstanceBind.setElapsedTime(elapsedTime);
                String status = apiPerfInstance.getStatus();

                if(Objects.equals(status, MagicValue.TEST_STATUS_START)){
                    testPlanCaseInstanceBind.setStatus(1);
                }else {
                    testPlanCaseInstanceBind.setStatus(0);
                }

                if(Objects.equals(status, MagicValue.TEST_STATUS_COMPLETE)){
                    testPlanCaseInstanceBind.setResult(0);
                }

                if(!Objects.equals(status, MagicValue.TEST_STATUS_START)){
                    String apiPerfInstanceId = apiPerfInstanceService.createApiPerfInstance(apiPerfInstance);
                    testPlanCaseInstanceBind.setCaseInstanceId(apiPerfInstanceId);
                    testPlanCaseInstanceBindService.updateTestPlanCaseInstanceBind(testPlanCaseInstanceBind);
                }


            }else {
                testPlanCaseInstanceBind.setResult(0);
            }

        }catch (Exception e){
            testPlanCaseInstanceBind.setStatus(0);
        }

        return testPlanCaseInstanceBind;
    }

    /**
     * 清楚数据
     * @param apiPerfId
     */
    public void cleanUpData(String apiPerfId){
        apiPerfExecuteDispatchService.cleanUpData(apiPerfId);
    }


}
