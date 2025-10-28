package io.tiklab.postin.autotest.http.scene.execute.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;

import io.tiklab.core.exception.ApplicationException;
import io.tiklab.postin.autotest.agentconfig.model.AgentConfig;
import io.tiklab.postin.autotest.agentconfig.service.AgentConfigService;
import io.tiklab.postin.autotest.common.stepcommon.model.StepCommon;
import io.tiklab.postin.autotest.common.stepcommon.model.StepCommonInstance;
import io.tiklab.postin.autotest.common.stepcommon.model.StepCommonQuery;
import io.tiklab.postin.autotest.common.stepcommon.service.StepCommonService;
import io.tiklab.postin.autotest.common.wstest.WsTestService;
import io.tiklab.postin.autotest.http.scene.execute.model.ApiSceneTestRequest;
import io.tiklab.postin.autotest.http.scene.execute.model.ApiSceneTestResponse;
import io.tiklab.postin.autotest.http.scene.instance.model.ApiSceneInstance;
import io.tiklab.postin.autotest.http.scene.instance.service.ApiSceneInstanceService;
import io.tiklab.postin.autotest.http.unit.cases.model.ApiUnitCase;
import io.tiklab.postin.autotest.http.unit.instance.model.ApiUnitInstance;
import io.tiklab.postin.autotest.instance.model.Instance;
import io.tiklab.postin.autotest.instance.service.InstanceService;
import io.tiklab.postin.common.ErrorCode;
import io.tiklab.postin.common.MagicValue;
import io.tiklab.postin.support.environment.model.EnvServer;
import io.tiklab.postin.support.environment.model.EnvServerQuery;
import io.tiklab.postin.support.environment.service.EnvServerService;
import io.tiklab.postin.support.environment.service.EnvVariableService;

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

    @Autowired
    InstanceService instanceService;

    // 定时器，不断更新数据库结果
    private final ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(10);
    
    // 是否存在正在执行的场景测试
    private static final Set<String> apiSceneIdSet = new HashSet<>();
    
    // apiSceneId：对应的响应结果
    private final static Map<String, ApiSceneTestResponse> sceneTestResponseMap = new ConcurrentHashMap<>();
    
    // 用例id : agentId
    private final Map<String, String> apiSceneIdAndAgentIdMap = new ConcurrentHashMap<>();
    
    // 定时任务映射
    private final Map<String, ScheduledFuture<?>> scheduleFutureMap = new ConcurrentHashMap<>();
    /**
     * 测试用例执行
     * @param apiSceneTestRequest
     * @return
     */
    @Override
    public ApiSceneTestResponse execute(ApiSceneTestRequest apiSceneTestRequest) {
        String apiSceneId = apiSceneTestRequest.getApiSceneCase().getId();

        // 检查 apiSceneId 是否已存在，如果存在则返回缓存的结果
        if (!apiSceneIdSet.add(apiSceneId)) {
            // 如果正在执行中，返回初始化的响应
            return getInitApiSceneTestResponse(apiSceneId);
        }

        // 获取步骤列表用于创建初始化实例
        List<StepCommon> stepCommonList = getStepCommonList(apiSceneId);
        
        ApiSceneTestResponse initResponse = createInitInstance(stepCommonList, apiSceneId);
        String apiSceneInstanceId = initResponse.getApiSceneInstance().getId();
        apiSceneTestRequest.setInitApiSceneTestResponse(initResponse);

        try {
            long startTime = System.currentTimeMillis();

            // 执行场景测试
            executeStart(apiSceneTestRequest);

            //防止还没执行就开始获取结果保存
            Thread.sleep(1100);

            //不断获取场景测试结果存入更新数据库
            ScheduledFuture<?> scheduledFuture = scheduledExecutorService.scheduleAtFixedRate(() -> {
                //获取数据
                ApiSceneTestResponse apiSceneTestResponse = getResult(apiSceneId);

                long endTime = System.currentTimeMillis();
                long elapsedTime = endTime - startTime - 1100;
                apiSceneTestResponse.getApiSceneInstance().setElapsedTime((int)elapsedTime);
                updateApiSceneInstance(apiSceneTestResponse, apiSceneId,apiSceneInstanceId);

                if(!Objects.equals(apiSceneTestResponse.getApiSceneInstance().getResult(), 2)){

                    closeThread(apiSceneId);
                }
            }, 1, 2, TimeUnit.SECONDS);
            scheduleFutureMap.put(apiSceneId, scheduledFuture);
        }catch (ApplicationException e){
            //更新状态为失败
            updateApiSceneInstanceStatus(apiSceneInstanceId, MagicValue.TEST_STATUS_FAIL);
            throw e;
        }catch (Exception e){
            //更新状态为失败
            updateApiSceneInstanceStatus(apiSceneInstanceId, MagicValue.TEST_STATUS_FAIL);
            throw new ApplicationException(ErrorCode.EXECUTE_CASE_ERROR, e.getMessage());
        }

        // 返回初始化的响应，让前端立即显示
        return initResponse;
    }

    private void closeThread(String caseId) {
        ScheduledFuture<?> scheduledFuture = scheduleFutureMap.remove(caseId);
        if (scheduledFuture != null) {
            scheduledFuture.cancel(true);
        }
    }

    @Override
    public void executeStart(ApiSceneTestRequest apiSceneTestRequest){
        String apiSceneId = apiSceneTestRequest.getApiSceneCase().getId();
        List<StepCommon> stepCommonList = getStepCommonList(apiSceneId);
        apiSceneTestRequest.setStepCommonList(stepCommonList);

        JSONObject variable = variableService.getVariable(apiSceneTestRequest.getApiEnvId());
        apiSceneTestRequest.setVariableJson(variable);

        EnvServerQuery envServerQuery = new EnvServerQuery();
        envServerQuery.setEnvId(apiSceneTestRequest.getApiEnvId());
        List<EnvServer> envServerList = apiEnvService.findEnvServerList(envServerQuery);
        apiSceneTestRequest.setApiPreUrl(envServerList.get(0).getUrl());

        AgentConfig executeAgent = agentConfigService.getExecuteAgent();
        if(executeAgent!=null){
            String agentId = executeAgent.getId();
            apiSceneIdAndAgentIdMap.put(apiSceneId, agentId);

            JSONObject apiUnitObject = new JSONObject();
            apiUnitObject.put("apiSceneTestRequest",apiSceneTestRequest);
            apiUnitObject.put("type",MagicValue.CASE_TYPE_API_SCENE);
            apiUnitObject.put("caseId",apiSceneId);

            //发送到agent执行
            wsTestService.sendMessageExe(agentId,apiUnitObject,null);
        }else {
            throw new ApplicationException(ErrorCode.EXECUTE_CASE_ERROR, "没有可用的测试执行节点");
        }
    }

    @Override
    public ApiSceneInstance result(String apiSceneId) {
        String apiSceneInstanceId = instanceService.getRecentExecuteInstanceId(apiSceneId);
        ApiSceneInstance apiSceneInstance = apiSceneInstanceService.findApiSceneInstance(apiSceneInstanceId);
        return apiSceneInstance;
    }

    @Override
    public ApiSceneTestResponse getResult(String apiSceneId){
        ApiSceneTestResponse apiSceneTestResponse = new ApiSceneTestResponse();
        try {
            synchronized (sceneTestResponseMap) {
                ApiSceneTestResponse response = sceneTestResponseMap.get(apiSceneId);
                if (response != null) {
                    apiSceneTestResponse = response;
                } else {
                    // 如果没有缓存结果，返回初始化的实例
                    apiSceneTestResponse = getInitApiSceneTestResponse(apiSceneId);
                }
            }

            if (!Objects.equals(apiSceneTestResponse.getApiSceneInstance().getResult(), 2)) {
                synchronized (sceneTestResponseMap) {
                    sceneTestResponseMap.remove(apiSceneId);
                }
                apiSceneIdSet.remove(apiSceneId);
            }
        } catch (Exception e) {
            synchronized (sceneTestResponseMap) {
                sceneTestResponseMap.remove(apiSceneId);
            }
            apiSceneIdSet.remove(apiSceneId);

            logger.error("getResult error:{}",e.getMessage());
            ApiSceneInstance apiSceneInstance = getInitApiSceneInstance(apiSceneId);
            apiSceneInstance.setResult(0); // 0表示失败
            apiSceneTestResponse.setApiSceneInstance(apiSceneInstance);
        }

        return apiSceneTestResponse;
    }


    /**
     * 初始实例
     */
    private ApiSceneInstance getInitApiSceneInstance(String caseId){
        ApiSceneInstance apiSceneInstance = new ApiSceneInstance();
        apiSceneInstance.setTestNumber(0);
        apiSceneInstance.setApiSceneId(caseId);
        apiSceneInstance.setPassNumber(0);
        apiSceneInstance.setPassRate("0.00%");
        apiSceneInstance.setFailNumber(0);
        apiSceneInstance.setElapsedTime(0);
        apiSceneInstance.setResult(2); // 2表示待执行状态

        return apiSceneInstance;
    }

    /**
     * 获取初始化的测试响应
     */
    private ApiSceneTestResponse getInitApiSceneTestResponse(String apiSceneId){
        ApiSceneTestResponse apiSceneTestResponse = new ApiSceneTestResponse();
        ApiSceneInstance apiSceneInstance = getInitApiSceneInstance(apiSceneId);
        apiSceneTestResponse.setApiSceneInstance(apiSceneInstance);
        apiSceneTestResponse.setStepCommonInstanceList(new ArrayList<>());
        return apiSceneTestResponse;
    }

    /**
     * 更新实例
     * @param apiSceneTestResponse
     * @param apiSceneId
     */
    private void updateApiSceneInstance(ApiSceneTestResponse apiSceneTestResponse,String apiSceneId,String apiSceneInstanceId) {
        if(apiSceneInstanceId!=null&&apiSceneTestResponse.getApiSceneInstance()!=null){
            //请求一次获取一次结果，存入数据库
            ApiSceneInstance apiSceneInstance = apiSceneTestResponse.getApiSceneInstance();
            apiSceneInstance.setApiSceneId(apiSceneId);
            apiSceneInstance.setId(apiSceneInstanceId);
            apiSceneInstanceService.updateApiSceneInstance(apiSceneInstance);

            Instance instance = instanceService.findInstance(apiSceneInstanceId);
            // 更新公共实例
            JSONObject instanceMap = new JSONObject();
            instanceMap.put("testNumber",apiSceneInstance.getTestNumber().toString());
            instanceMap.put("passNumber",apiSceneInstance.getPassNumber().toString());
            instanceMap.put("passRate",apiSceneInstance.getPassRate());
            instanceMap.put("failNumber",apiSceneInstance.getFailNumber().toString());
            instanceMap.put("elapsedTime",apiSceneInstance.getElapsedTime());
            instance.setContent(instanceMap.toString());
            // 根据result值设置status字符串
            String statusStr = MagicValue.TEST_STATUS_FAIL; // 默认失败
            if (apiSceneInstance.getResult() == 1) {
                statusStr = MagicValue.TEST_STATUS_SUCCESS;
            } else if (apiSceneInstance.getResult() == 2) {
                statusStr = MagicValue.TEST_STATUS_START;
            }
            instance.setStatus(statusStr);
            instanceService.updateInstance(instance);

            // 更新步骤实例列表
            if(apiSceneTestResponse.getStepCommonInstanceList() != null){
                apiSceneInstanceService.updateWebStepInstance(apiSceneTestResponse.getStepCommonInstanceList(), apiSceneInstanceId);
            }
        
        }else {
            updateApiSceneInstanceStatus(apiSceneId,MagicValue.TEST_STATUS_FAIL);
        }
    }

    /**
     * 状态更改
     * @param apiSceneInstanceId
     */
    private void updateApiSceneInstanceStatus(String apiSceneInstanceId,String status){
        ApiSceneInstance apiSceneInstance = apiSceneInstanceService.findApiSceneInstance(apiSceneInstanceId);
        // 根据状态字符串设置result值：fail->0, success->1, start->2
        int resultValue = 0; // 默认失败
        if (MagicValue.TEST_STATUS_SUCCESS.equals(status)) {
            resultValue = 1;
        } else if (MagicValue.TEST_STATUS_START.equals(status)) {
            resultValue = 2;
        }
        apiSceneInstance.setResult(resultValue);
        apiSceneInstanceService.updateApiSceneInstance(apiSceneInstance);

        Instance instance = instanceService.findInstance(apiSceneInstanceId);
        instance.setStatus(status);
        instanceService.updateInstance(instance);
    }

    /**
     * 初始化实例
     * @param stepCommonList
     * @param apiSceneId
     */
    public ApiSceneTestResponse createInitInstance(List<StepCommon> stepCommonList, String apiSceneId){

        ApiSceneInstance apiSceneInstance = new ApiSceneInstance();
        apiSceneInstance.setTestNumber(stepCommonList!=null?stepCommonList.size():0);
        apiSceneInstance.setElapsedTime(0);
        apiSceneInstance.setPassNumber(0);
        apiSceneInstance.setPassRate("0.00%");
        apiSceneInstance.setFailNumber(0);
        apiSceneInstance.setApiSceneId(apiSceneId);
        apiSceneInstance.setResult(2); // 2表示待执行状态

        // 测试结果列表
        List<StepCommonInstance> stepCommonInstanceList = new ArrayList<>();

        if(CollectionUtils.isNotEmpty(stepCommonList)){
            for(StepCommon stepCommon : stepCommonList){
                if(stepCommon != null) {
                    StepCommonInstance stepCommonInstance = new StepCommonInstance();
                    stepCommonInstance.setSort(stepCommonInstanceList.size()+1);

                    String type = stepCommon.getType();
                    stepCommonInstance.setType(type);

                    if(type.equals(MagicValue.TEST_STEP_SCRIPT)||type.equals(MagicValue.TEST_STEP_DATABASE)){
                        stepCommonInstance.setName(stepCommon.getName());
                    }

                    if(type.equals(MagicValue.CASE_TYPE_API_SCENE)){
                        String name = stepCommon.getApiSceneStep().getApiUnit().getTestCase().getName();
//                        ApiUnitInstance apiUnitInstance = new ApiUnitInstance();
//                        apiUnitInstance.setApiUnit(apiUnit);
//                        stepCommonInstance.setApiUnitInstance(apiUnitInstance);

                        stepCommonInstance.setName(name);
                    }



                    //默认待执行2
                    stepCommonInstance.setResult(2);

                    stepCommonInstanceList.add(stepCommonInstance);
                }
            }
        }
        
        ApiSceneTestResponse apiSceneTestResponse = new ApiSceneTestResponse();
        apiSceneTestResponse.setStepCommonInstanceList(stepCommonInstanceList);
        apiSceneTestResponse.setApiSceneInstance(apiSceneInstance);

        String instanceId = saveInstance(apiSceneTestResponse, apiSceneId);
        apiSceneTestResponse.getApiSceneInstance().setId(instanceId);

        return apiSceneTestResponse;
    }


    /**
     * 保存历史
     * @return
     * @param apiSceneTestResponse
     * @param apiSceneId
     */
    private String saveInstance(ApiSceneTestResponse apiSceneTestResponse, String apiSceneId){
        ApiSceneInstance apiSceneInstance = apiSceneTestResponse.getApiSceneInstance();
        apiSceneInstance.setApiSceneId(apiSceneId);

        String instanceId = apiSceneInstanceService.saveApiSceneInstanceToSql(apiSceneInstance, apiSceneTestResponse);

        return instanceId;
    }

    /**
     * 获取步骤列表
     * @param apiSceneId
     * @return
     */
    private List<StepCommon> getStepCommonList(String apiSceneId) {
        StepCommonQuery stepCommonQuery = new StepCommonQuery();
        stepCommonQuery.setCaseId(apiSceneId);
        stepCommonQuery.setCaseType(MagicValue.CASE_TYPE_API_SCENE);
        return stepCommonService.findStepCommonList(stepCommonQuery);
    }

    /**
     * 处理从agent接收到的测试数据
     * @param message
     */
    @Override
    public void getAgentTestData(JSONObject message) {
        try {
            ApiSceneTestResponse response = message.getJSONObject("apiSceneTestResponse").toJavaObject(ApiSceneTestResponse.class);
            String caseId = message.getString("caseId");
            sceneTestResponseMap.put(caseId, response);
        } catch (Exception e) {
            logger.error("apiSceneTestResponse---getAgentTestData: " + e.getMessage());
        }
    }

}



