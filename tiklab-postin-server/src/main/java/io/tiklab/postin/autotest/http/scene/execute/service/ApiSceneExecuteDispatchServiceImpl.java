package io.tiklab.postin.autotest.http.scene.execute.service;

import com.alibaba.fastjson.JSONObject;
import io.tiklab.core.exception.ApplicationException;
import io.tiklab.postin.common.MagicValue;
import io.tiklab.postin.autotest.agentconfig.model.AgentConfig;
import io.tiklab.postin.autotest.agentconfig.service.AgentConfigService;
import io.tiklab.postin.autotest.http.scene.execute.model.ApiSceneTestRequest;
import io.tiklab.postin.autotest.http.scene.execute.model.ApiSceneTestResponse;
import io.tiklab.postin.autotest.http.scene.instance.model.ApiSceneInstance;
import io.tiklab.postin.autotest.http.scene.instance.service.ApiSceneInstanceService;
import io.tiklab.postin.autotest.common.stepcommon.model.StepCommon;
import io.tiklab.postin.autotest.common.stepcommon.model.StepCommonQuery;
import io.tiklab.postin.autotest.common.stepcommon.service.StepCommonService;

import io.tiklab.postin.autotest.common.wsTest.service.WebSocketServiceImpl;
import io.tiklab.postin.autotest.common.wstest.WsTestService;
import io.tiklab.postin.support.environment.model.EnvServer;
import io.tiklab.postin.support.environment.model.EnvServerQuery;
import io.tiklab.postin.support.environment.service.EnvServerService;
import io.tiklab.postin.support.environment.service.EnvVariableService;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * 接口场景测试调度 服务
 */
@Service
public class ApiSceneExecuteDispatchServiceImpl implements ApiSceneExecuteDispatchService {

    static Logger logger = LoggerFactory.getLogger(ApiSceneExecuteDispatchServiceImpl.class);


    @Autowired
    ApiSceneInstanceService apiSceneInstanceService;

    @Autowired
    AgentConfigService agentConfigService;

    @Autowired
    EnvVariableService variableService;

    @Autowired
    StepCommonService stepCommonService;

    @Autowired
    WsTestService wsTestService;

    @Autowired
    EnvServerService apiEnvService;
    /**
     * 测试用例执行
     * @param apiSceneTestRequest
     * @return
     */
    @Override
    public ApiSceneTestResponse execute(ApiSceneTestRequest apiSceneTestRequest) {
        //执行
        ApiSceneTestResponse apiSceneTestResponse = executeStart(apiSceneTestRequest);

        String apiSceneId = apiSceneTestRequest.getApiSceneCase().getId();
        //保存实例，存入数据库
        saveInstance( apiSceneTestResponse,apiSceneId);

        return apiSceneTestResponse;
    }

    @Override
    public ApiSceneTestResponse executeStart(ApiSceneTestRequest apiSceneTestRequest){
        String apiSceneId = apiSceneTestRequest.getApiSceneCase().getId();
        StepCommonQuery stepCommonQuery = new StepCommonQuery();
        stepCommonQuery.setCaseId(apiSceneId);
        stepCommonQuery.setCaseType(MagicValue.CASE_TYPE_API_SCENE);
        List<StepCommon> stepCommonList = stepCommonService.findStepCommonList(stepCommonQuery);
        apiSceneTestRequest.setStepCommonList(stepCommonList);

        JSONObject variable = variableService.getVariable(apiSceneTestRequest.getApiEnvId());
        apiSceneTestRequest.setVariableJson(variable);

        EnvServerQuery envServerQuery = new EnvServerQuery();
        envServerQuery.setEnvId(apiSceneTestRequest.getApiEnvId());
        List<EnvServer> envServerList = apiEnvService.findEnvServerList(envServerQuery);
        apiSceneTestRequest.setApiPreUrl(envServerList.get(0).getUrl());

        List<AgentConfig> agentConfigList = agentConfigService.getAgentList();
        if( CollectionUtils.isNotEmpty(agentConfigList)){
            AgentConfig agentConfig = agentConfigList.get(0);
            String agentId = agentConfig.getId();

            JSONObject apiUnitObject = new JSONObject();
            apiUnitObject.put("apiSceneTestRequest",apiSceneTestRequest);
            apiUnitObject.put("type",MagicValue.CASE_TYPE_API_SCENE);
            apiUnitObject.put("caseId",apiSceneId);

            String futureId = agentId + "_" + MagicValue.CASE_TYPE_API_SCENE+ "_" +apiSceneId;
            wsTestService.sendMessageExe(agentId,apiUnitObject,futureId);

            try {
                // 从futureMap中获取CompletableFuture实例并获取结果
                CompletableFuture<JSONObject> future =  WebSocketServiceImpl.futureMap.get(futureId);
                JSONObject jsonObject = future.get();
                JSONObject apiSceneTestResponseObj = jsonObject.getJSONObject("apiSceneTestResponse");
                ApiSceneTestResponse apiSceneTestResponse = apiSceneTestResponseObj.toJavaObject(ApiSceneTestResponse.class);
                return apiSceneTestResponse;
            } catch (InterruptedException | ExecutionException e) {
                throw new ApplicationException(("执行出错"));
            }
        }else {
            throw new ApplicationException("不是内嵌agent，请到设置中配置agent");
        }
    }

    /**
     * 保存历史
     * @return
     * @param apiSceneTestResponse
     * @param apiSceneId
     */
    private void saveInstance(ApiSceneTestResponse apiSceneTestResponse, String apiSceneId){
        ApiSceneInstance apiSceneInstance = apiSceneTestResponse.getApiSceneInstance();
        apiSceneInstance.setApiSceneId(apiSceneId);

        apiSceneInstanceService.saveApiSceneInstanceToSql(apiSceneInstance,apiSceneTestResponse);
    }




}



