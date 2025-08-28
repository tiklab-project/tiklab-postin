package io.tiklab.postin.autotest.http.unit.execute.service;

import com.alibaba.fastjson.JSONObject;
import io.tiklab.core.exception.ApplicationException;
import io.tiklab.postin.autotest.agentconfig.model.AgentConfig;
import io.tiklab.postin.autotest.agentconfig.service.AgentConfigService;
import io.tiklab.postin.autotest.common.wsTest.service.WebSocketServiceImpl;
import io.tiklab.postin.autotest.common.wstest.WsTestService;
import io.tiklab.postin.autotest.http.unit.cases.model.ApiUnitCase;
import io.tiklab.postin.autotest.http.unit.cases.model.ApiUnitCaseDataConstruction;
import io.tiklab.postin.autotest.http.unit.cases.service.ApiUnitCaseService;
import io.tiklab.postin.autotest.http.unit.execute.model.ApiUnitTestRequest;
import io.tiklab.postin.autotest.http.unit.instance.model.ApiUnitInstance;
import io.tiklab.postin.autotest.http.unit.instance.service.ApiUnitInstanceService;
import io.tiklab.postin.autotest.instance.model.Instance;
import io.tiklab.postin.autotest.instance.service.InstanceService;
import io.tiklab.postin.autotest.test.model.TestCase;
import io.tiklab.postin.common.MagicValue;
import io.tiklab.postin.support.environment.model.EnvServer;
import io.tiklab.postin.support.environment.model.EnvServerQuery;
import io.tiklab.postin.support.environment.service.EnvServerService;
import io.tiklab.postin.support.environment.service.EnvVariableService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * 接口单元测试调度 服务
 */
@Service
public class ApiUnitExecuteDispatchServiceImpl implements ApiUnitExecuteDispatchService {

    private static Logger logger = LoggerFactory.getLogger(ApiUnitExecuteDispatchServiceImpl.class);


    @Autowired
    ApiUnitCaseService apiUnitCaseService;

    @Autowired
    ApiUnitInstanceService apiUnitInstanceService;

    @Autowired
    EnvVariableService variableService;

    @Autowired
    AgentConfigService agentConfigService;

    @Autowired
    InstanceService instanceService;

    @Autowired
    WsTestService wsTestService;

    @Autowired
    EnvServerService apiEnvService;
    @Override
    public ApiUnitInstance execute(ApiUnitTestRequest apiUnitTestRequest) {
        String apiUnitId = apiUnitTestRequest.getApiUnitCase().getId();

        ApiUnitInstance apiUnitInstance;

        try {
             apiUnitInstance = executeStart(apiUnitTestRequest);
        }catch (Exception e){
            throw new ApplicationException(e);
        }

        if(apiUnitInstance==null){
            return null;
        }

        saveInstance(apiUnitInstance,apiUnitId);

        return apiUnitInstance;
    }


    @Override
    public ApiUnitInstance executeStart(ApiUnitTestRequest apiUnitTestRequest) {
        String apiUnitId = apiUnitTestRequest.getApiUnitCase().getId();
        //准备测试的数据
        ApiUnitTestRequest processData = setApiUnitTestRequestData(apiUnitId, apiUnitTestRequest.getApiEnvId());
        processData.setWorkspaceId(apiUnitTestRequest.getWorkspaceId());

        ApiUnitInstance apiUnitInstance = new ApiUnitInstance();

        AgentConfig executeAgent = agentConfigService.getExecuteAgent();
        if(executeAgent!=null) {
            String agentId = executeAgent.getId();

            JSONObject apiUnitObject = new JSONObject();
            apiUnitObject.put("apiUnitTestRequest", processData);
            apiUnitObject.put("type", MagicValue.CASE_TYPE_API_UNIT);
            apiUnitObject.put("caseId", apiUnitId);

            String futureId = agentId + "_" + MagicValue.CASE_TYPE_API_UNIT + "_" + apiUnitId;
            wsTestService.sendMessageExe(agentId, apiUnitObject, futureId);

            try {
                // 从futureMap中获取CompletableFuture实例并获取结果
                CompletableFuture<JSONObject> future = WebSocketServiceImpl.futureMap.get(futureId);
                JSONObject jsonObject = future.get();
                JSONObject apiUnitInstanceObj = jsonObject.getJSONObject("apiUnitInstance");
                apiUnitInstance = apiUnitInstanceObj.toJavaObject(ApiUnitInstance.class);
                return apiUnitInstance;
            } catch (InterruptedException | ExecutionException e) {
                logger.info("执行出错");
            }
        }

        return apiUnitInstance;
    }



    private void saveInstance(ApiUnitInstance apiUnitInstance, String apiUnitId){
        //执行后数据保存
        String apiUnitInstanceId = apiUnitInstanceService.saveApiUnitInstanceToSql(apiUnitInstance);

        Instance instance = new Instance();
        instance.setId(apiUnitInstanceId);
        TestCase testCase = new TestCase();
        testCase.setId(apiUnitId);
        instance.setTestCase(testCase);
        instance.setType(MagicValue.CASE_TYPE_API_UNIT);

        ApiUnitCase apiUnitCase = apiUnitCaseService.findApiUnitCase(apiUnitId);
        instance.setName(apiUnitCase.getTestCase().getName());
        instance.setWorkspaceId(apiUnitCase.getTestCase().getWorkspaceId());

        //获取当前执行次数
        int executeNum = instanceService.getRecentExecuteNum(apiUnitId);
        instance.setExecuteNumber(executeNum);

        //根据result设置成功还是失败
        if(apiUnitInstance.getResult()==1){
            instance.setStatus(MagicValue.TEST_STATUS_SUCCESS);
        }else {
            instance.setStatus(MagicValue.TEST_STATUS_FAIL);
        }

        JSONObject instanceMap = new JSONObject();
        instanceMap.put("url",apiUnitInstance.getRequestInstance().getRequestUrl());
        instanceMap.put("requestType",apiUnitInstance.getRequestInstance().getRequestType());
        instanceMap.put("result",apiUnitInstance.getResult().toString());

        if(apiUnitInstance.getErrMessage()==null){
            if(apiUnitInstance.getStatusCode()!=null){
                instanceMap.put("statusCode",apiUnitInstance.getStatusCode().toString());
            }

            if(apiUnitInstance.getElapsedTime()!=null){
                int time = apiUnitInstance.getElapsedTime().intValue();
                instanceMap.put("elapsedTime", Integer.toString(time));
            }

        }else {
            instanceMap.put("errMessage",apiUnitInstance.getErrMessage());
        }

        instance.setContent(instanceMap.toString());

        instanceService.createInstance(instance);

    }


    /**
     * 设置测试数据
     * @param apiUnitId
     * @param apiEnvId
     * @return
     */
    public ApiUnitTestRequest setApiUnitTestRequestData(String apiUnitId,String apiEnvId){
        ApiUnitTestRequest apiUnitTestRequest = new ApiUnitTestRequest();

        ApiUnitCase apiUnitCase = apiUnitCaseService.findApiUnitCase(apiUnitId);
        apiUnitTestRequest.setApiUnitCase(apiUnitCase);

        ApiUnitCaseDataConstruction apiUnitCaseDataConstruction = apiUnitCaseService.findApiUnitCaseExt(apiUnitCase);
        apiUnitTestRequest.setApiUnitCaseDataConstruction(apiUnitCaseDataConstruction);

        JSONObject variable = variableService.getVariable(apiEnvId);
        apiUnitTestRequest.setVariableJson(variable);

        EnvServerQuery envServerQuery = new EnvServerQuery();
        envServerQuery.setEnvId(apiEnvId);
        List<EnvServer> envServerList = apiEnvService.findEnvServerList(envServerQuery);
        apiUnitTestRequest.setApiPreUrl(envServerList.get(0).getUrl());


        return apiUnitTestRequest;
    }

}
