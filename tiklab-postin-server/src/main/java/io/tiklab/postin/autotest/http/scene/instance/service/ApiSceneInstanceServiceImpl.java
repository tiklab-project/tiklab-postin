package io.tiklab.postin.autotest.http.scene.instance.service;

import java.sql.Timestamp;
import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;

import io.tiklab.core.page.Pagination;
import io.tiklab.core.page.PaginationBuilder;
import io.tiklab.eam.common.context.LoginContext;
import io.tiklab.postin.autotest.common.ifjudgment.model.IfJudgmentInstance;
import io.tiklab.postin.autotest.common.ifjudgment.service.IfJudgmentInstanceService;
import io.tiklab.postin.autotest.common.stepcommon.model.StepCommonInstance;
import io.tiklab.postin.autotest.common.stepcommon.model.StepCommonInstanceQuery;
import io.tiklab.postin.autotest.common.stepcommon.service.StepCommonInstanceService;
import io.tiklab.postin.autotest.http.scene.cases.model.ApiSceneCase;
import io.tiklab.postin.autotest.http.scene.cases.service.ApiSceneCaseService;
import io.tiklab.postin.autotest.http.scene.execute.model.ApiSceneTestResponse;
import io.tiklab.postin.autotest.http.scene.instance.dao.ApiSceneInstanceDao;
import io.tiklab.postin.autotest.http.scene.instance.entity.ApiSceneInstanceEntity;
import io.tiklab.postin.autotest.http.scene.instance.model.ApiSceneInstance;
import io.tiklab.postin.autotest.http.scene.instance.model.ApiSceneInstanceQuery;
import io.tiklab.postin.autotest.http.unit.instance.model.ApiUnitInstance;
import io.tiklab.postin.autotest.http.unit.instance.service.ApiUnitInstanceService;
import io.tiklab.postin.autotest.instance.model.Instance;
import io.tiklab.postin.autotest.instance.service.InstanceService;
import io.tiklab.postin.autotest.test.model.TestCase;
import io.tiklab.postin.common.MagicValue;
import io.tiklab.toolkit.beans.BeanMapper;
import io.tiklab.toolkit.join.JoinTemplate;

/**
* 接口场景实例 服务
*/
@Service
public class ApiSceneInstanceServiceImpl implements ApiSceneInstanceService {

    @Autowired
    ApiSceneInstanceDao apiSceneInstanceDao;

    @Autowired
    JoinTemplate joinTemplate;

    @Autowired
    ApiUnitInstanceService apiUnitInstanceService;

    @Autowired
    StepCommonInstanceService stepCommonInstanceService;

    @Autowired
    IfJudgmentInstanceService ifJudgmentInstanceService;

    @Autowired
    ApiSceneCaseService apiSceneCaseService;

    @Autowired
    InstanceService instanceService;

    @Override
    public String createApiSceneInstance(@NotNull @Valid ApiSceneInstance scenInstance) {
        ApiSceneInstanceEntity apiSceneInstanceEntity = BeanMapper.map(scenInstance, ApiSceneInstanceEntity.class);

        apiSceneInstanceEntity.setCreateTime(new Timestamp(System.currentTimeMillis()));
        String userId = LoginContext.getLoginId();
        apiSceneInstanceEntity.setCreateUser(userId);

        return apiSceneInstanceDao.createApiSceneInstance(apiSceneInstanceEntity);
    }

    @Override
    public void updateApiSceneInstance(@NotNull @Valid ApiSceneInstance scenInstance) {
        ApiSceneInstanceEntity testInstanceEntity = BeanMapper.map(scenInstance, ApiSceneInstanceEntity.class);

        apiSceneInstanceDao.updateApiSceneInstance(testInstanceEntity);
    }

    @Override
    public void deleteApiSceneInstance(@NotNull String id) {
        apiSceneInstanceDao.deleteApiSceneInstance(id);

    }

    @Override
    public ApiSceneInstance findOne(String id) {
        ApiSceneInstanceEntity testInstanceEntity = apiSceneInstanceDao.findApiSceneInstance(id);

        ApiSceneInstance testInstance = BeanMapper.map(testInstanceEntity, ApiSceneInstance.class);
        return testInstance;
    }

    @Override
    public List<ApiSceneInstance> findList(List<String> idList) {
        List<ApiSceneInstanceEntity> testInstanceEntityList =  apiSceneInstanceDao.findApiSceneInstanceList(idList);

        List<ApiSceneInstance> testInstanceList =  BeanMapper.mapList(testInstanceEntityList, ApiSceneInstance.class);
        return testInstanceList;
    }

    @Override
    public ApiSceneInstance findApiSceneInstance(@NotNull String id) {
        ApiSceneInstance apiSceneInstance = findOne(id);

        StepCommonInstanceQuery stepCommonInstanceQuery = new StepCommonInstanceQuery();
        stepCommonInstanceQuery.setInstanceId(id);
        stepCommonInstanceQuery.setCaseType(MagicValue.CASE_TYPE_API_SCENE);
        List<StepCommonInstance> stepCommonInstanceList = stepCommonInstanceService.findStepCommonInstanceList(stepCommonInstanceQuery);
        apiSceneInstance.setStepCommonInstanceList(stepCommonInstanceList);

        joinTemplate.joinQuery(apiSceneInstance);
        return apiSceneInstance;
    }

    @Override
    public List<ApiSceneInstance> findAllApiSceneInstance() {
        List<ApiSceneInstanceEntity> testInstanceEntities =  apiSceneInstanceDao.findAllApiSceneInstance();

        List<ApiSceneInstance> testInstanceList =  BeanMapper.mapList(testInstanceEntities, ApiSceneInstance.class);

        joinTemplate.joinQuery(testInstanceList);
        return testInstanceList;
    }

    @Override
    public List<ApiSceneInstance> findApiSceneInstanceList(ApiSceneInstanceQuery apiSceneInstanceQuery) {
        List<ApiSceneInstanceEntity> testInstanceEntities = apiSceneInstanceDao.findApiSceneInstanceList(apiSceneInstanceQuery);

        List<ApiSceneInstance> testInstanceList = BeanMapper.mapList(testInstanceEntities, ApiSceneInstance.class);

        joinTemplate.joinQuery(testInstanceList);

        return testInstanceList;
    }

    @Override
    public Pagination<ApiSceneInstance> findApiSceneInstancePage(ApiSceneInstanceQuery apiSceneInstanceQuery) {
        Pagination<ApiSceneInstanceEntity>  pagination = apiSceneInstanceDao.findApiSceneInstancePage(apiSceneInstanceQuery);

        List<ApiSceneInstance> testInstanceList = BeanMapper.mapList(pagination.getDataList(), ApiSceneInstance.class);

        joinTemplate.joinQuery(testInstanceList);

        return PaginationBuilder.build(pagination,testInstanceList);
    }

    @Override
    public String saveApiSceneInstanceToSql(ApiSceneInstance apiSceneInstance, ApiSceneTestResponse apiSceneTestResponse) {
        String apiSceneId = apiSceneInstance.getApiSceneId();
        String apiSceneInstanceId = createApiSceneInstance(apiSceneInstance);

        createCommonInstance(apiSceneInstance,apiSceneId,apiSceneInstanceId);

        createWebStepInstance(apiSceneTestResponse.getStepCommonInstanceList(),apiSceneInstanceId);

        return apiSceneInstanceId;
    }

    /**
     * 创建步骤实例
     * @param stepCommonInstanceList
     * @param apiSceneInstanceId
     */
    @Override
    public void createWebStepInstance(List<StepCommonInstance> stepCommonInstanceList, String apiSceneInstanceId){
        // 所有ApiUnitInstance设置apiSceneInstanceId创建
        stepCommonInstanceList.forEach(stepCommonInstance -> {
            //公共的历史创建
            stepCommonInstance.setInstanceId(apiSceneInstanceId);
            String stepInstanceId = stepCommonInstanceService.createStepCommonInstance(stepCommonInstance);
            
            // 将生成的id设置回stepCommonInstance对象
            stepCommonInstance.setId(stepInstanceId);

            if(stepCommonInstance.getApiUnitInstance() != null && stepCommonInstance.getApiUnitInstance().getRequestInstance()!=null){
                ApiUnitInstance apiUnitInstance = stepCommonInstance.getApiUnitInstance();
                apiUnitInstance.setId(stepInstanceId);
                apiUnitInstanceService.saveApiUnitInstanceToSql(apiUnitInstance);
            }

            //if判断历史创建
            if(stepCommonInstance.getIfJudgmentInstance()!=null){
                IfJudgmentInstance ifJudgmentInstance = stepCommonInstance.getIfJudgmentInstance();
                ifJudgmentInstance.setStepInstanceId(apiSceneInstanceId);
                ifJudgmentInstance.setId(stepInstanceId);
                ifJudgmentInstanceService.createIfJudgmentInstance(ifJudgmentInstance);
            }
        });

    }

    /**
     * 更新步骤实例
     * @param stepCommonInstanceList
     * @param apiSceneInstanceId
     */
    @Override
    public void updateWebStepInstance(List<StepCommonInstance> stepCommonInstanceList, String apiSceneInstanceId){
        if(CollectionUtils.isNotEmpty(stepCommonInstanceList)){
            stepCommonInstanceList.forEach(stepCommonInstance -> {
                if(stepCommonInstance != null){
                    stepCommonInstance.setInstanceId(apiSceneInstanceId);
                    
                    if(stepCommonInstance.getId() != null){
                        // 更新现有步骤实例
                        stepCommonInstanceService.updateStepCommonInstance(stepCommonInstance);

                        // 处理API单元实例 - 根据是否有信息决定创建或更新
                        if(stepCommonInstance.getApiUnitInstance() != null&&stepCommonInstance.getApiUnitInstance().getRequestInstance() != null){
                            ApiUnitInstance apiUnitInstance = stepCommonInstance.getApiUnitInstance();
                            apiUnitInstance.setId(stepCommonInstance.getId());
                            
                            // 检查是否已经有API单元实例数据
                            try {
                                ApiUnitInstance existingInstance = apiUnitInstanceService.findApiUnitInstance(stepCommonInstance.getId());
                                if(existingInstance != null){
                                    // 已存在，执行更新（包含深层次实例）
                                    apiUnitInstanceService.updateApiUnitInstanceToSql(apiUnitInstance);
                                } else {
                                    // 不存在，执行创建
                                    apiUnitInstanceService.saveApiUnitInstanceToSql(apiUnitInstance);
                                }
                            } catch (Exception e) {
                                // 如果查询失败，说明不存在，执行创建
                                apiUnitInstanceService.saveApiUnitInstanceToSql(apiUnitInstance);
                            }
                        }

                        // 处理if判断实例 - 根据是否有信息决定创建或更新
                        if(stepCommonInstance.getIfJudgmentInstance() != null){
                            IfJudgmentInstance ifJudgmentInstance = stepCommonInstance.getIfJudgmentInstance();
                            ifJudgmentInstance.setId(stepCommonInstance.getId());
                            
                            // 检查是否已经有if判断实例数据
                            try {
                                IfJudgmentInstance existingInstance = ifJudgmentInstanceService.findIfJudgmentInstance(stepCommonInstance.getId());
                                if(existingInstance != null){
                                    // 已存在，执行更新
                                    ifJudgmentInstanceService.updateIfJudgmentInstance(ifJudgmentInstance);
                                } else {
                                    // 不存在，执行创建
                                    ifJudgmentInstance.setStepInstanceId(apiSceneInstanceId);
                                    ifJudgmentInstanceService.createIfJudgmentInstance(ifJudgmentInstance);
                                }
                            } catch (Exception e) {
                                // 如果查询失败，说明不存在，执行创建
                                ifJudgmentInstance.setStepInstanceId(apiSceneInstanceId);
                                ifJudgmentInstanceService.createIfJudgmentInstance(ifJudgmentInstance);
                            }
                        }
                    } else {
                        // 创建新步骤实例
                        String stepInstanceId = stepCommonInstanceService.createStepCommonInstance(stepCommonInstance);
                        stepCommonInstance.setId(stepInstanceId);

                        // 创建API单元实例（如果有信息）
                        if(stepCommonInstance.getApiUnitInstance() != null){
                            ApiUnitInstance apiUnitInstance = stepCommonInstance.getApiUnitInstance();
                            apiUnitInstance.setId(stepInstanceId);
                            apiUnitInstanceService.saveApiUnitInstanceToSql(apiUnitInstance);
                        }

                        // 创建if判断实例（如果有信息）
                        if(stepCommonInstance.getIfJudgmentInstance() != null){
                            IfJudgmentInstance ifJudgmentInstance = stepCommonInstance.getIfJudgmentInstance();
                            ifJudgmentInstance.setStepInstanceId(apiSceneInstanceId);
                            ifJudgmentInstance.setId(stepInstanceId);
                            ifJudgmentInstanceService.createIfJudgmentInstance(ifJudgmentInstance);
                        }
                    }
                }
            });
        }
    }

    /**
     * 创建公共实例
     * @param apiSceneInstance
     * @param apiSceneId
     * @param apiSceneInstanceId
     */
    private void createCommonInstance(ApiSceneInstance apiSceneInstance,String apiSceneId,String apiSceneInstanceId){
        Instance instance = new Instance();
        instance.setId(apiSceneInstanceId);

//        instance.setBelongId(apiSceneId);
        TestCase testCase = new TestCase();
        testCase.setId(apiSceneId);
        instance.setTestCase(testCase);
        instance.setType(MagicValue.CASE_TYPE_API_SCENE);

        ApiSceneCase apiSceneCase = apiSceneCaseService.findApiSceneCase(apiSceneId);
        instance.setName(apiSceneCase.getTestCase().getName());
        instance.setWorkspaceId(apiSceneCase.getTestCase().getWorkspaceId());

        int executeNum = instanceService.getRecentExecuteNum(apiSceneId);
        instance.setExecuteNumber(executeNum);


        //根据result设置状态：1-成功，0-失败，2-待执行
        if(apiSceneInstance.getResult()==1){
            instance.setStatus(MagicValue.TEST_STATUS_SUCCESS);
        }else if(apiSceneInstance.getResult()==2){
            instance.setStatus(MagicValue.TEST_STATUS_START);
        }else {
            instance.setStatus(MagicValue.TEST_STATUS_FAIL);
        }

        JSONObject instanceMap = new JSONObject();
        instanceMap.put("result",apiSceneInstance.getResult().toString());
        instanceMap.put("testNumber",apiSceneInstance.getTestNumber().toString());
        instanceMap.put("passNumber",apiSceneInstance.getPassNumber().toString());
        instanceMap.put("failNumber",apiSceneInstance.getFailNumber().toString());
        instanceMap.put("elapsedTime",apiSceneInstance.getElapsedTime().toString());
        instanceMap.put("passRate",apiSceneInstance.getPassRate());
        instance.setContent(instanceMap.toString());
        instanceService.createInstance(instance);
    }


}