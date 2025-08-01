package io.tiklab.postin.autotest.http.perf.cases.service;


import io.tiklab.postin.autotest.http.scene.cases.model.ApiSceneCase;
import io.tiklab.postin.autotest.http.scene.cases.service.ApiSceneCaseService;
import io.tiklab.postin.autotest.http.unit.cases.model.ApiUnitCase;
import io.tiklab.postin.autotest.http.unit.cases.service.ApiUnitCaseService;
import io.tiklab.postin.autotest.test.model.TestCase;
import io.tiklab.postin.autotest.test.model.TestCaseQuery;
import io.tiklab.postin.autotest.test.service.TestCaseService;
import io.tiklab.postin.autotest.http.perf.cases.dao.ApiPerfStepDao;
import io.tiklab.postin.autotest.http.perf.cases.entity.ApiPerfStepEntity;
import io.tiklab.core.page.Pagination;
import io.tiklab.core.page.PaginationBuilder;
import io.tiklab.postin.common.MagicValue;
import io.tiklab.toolkit.beans.BeanMapper;
import io.tiklab.toolkit.join.JoinTemplate;
import io.tiklab.postin.autotest.http.perf.cases.model.ApiPerfStep;
import io.tiklab.postin.autotest.http.perf.cases.model.ApiPerfStepQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
* 接口性能下场景步骤 服务
*/
@Service
public class ApiPerfStepServiceImpl implements ApiPerfStepService {

    @Autowired
    ApiPerfStepDao apiPerfStepDao;

    @Autowired
    ApiUnitCaseService apiUnitCaseService;

    @Autowired
    ApiSceneCaseService apiSceneCaseService;

    @Autowired
    TestCaseService testCaseService;

    @Autowired
    JoinTemplate joinTemplate;

    @Override
    public String createApiPerfStep(@NotNull @Valid ApiPerfStep apiPerfStep) {
        ApiPerfStepEntity apiPerfStepEntity = BeanMapper.map(apiPerfStep, ApiPerfStepEntity.class);

        //初始值
        apiPerfStepEntity.setCreateTime(new Timestamp(System.currentTimeMillis()));
        apiPerfStepEntity.setExecuteCount(1);
        apiPerfStepEntity.setThreadCount(1);
        apiPerfStepEntity.setExecuteType(1);

        return apiPerfStepDao.createApiPerfStep(apiPerfStepEntity);
    }

    @Override
    public void updateApiPerfStep(@NotNull @Valid ApiPerfStep apiPerfStep) {
        ApiPerfStepEntity apiPerfStepEntity = BeanMapper.map(apiPerfStep, ApiPerfStepEntity.class);

        apiPerfStepDao.updateApiPerfStep(apiPerfStepEntity);
    }

    @Override
    public void deleteApiPerfStep(@NotNull String id) {
        apiPerfStepDao.deleteApiPerfStep(id);
    }

    @Override
    public void deleteAllApiPerfStep( String caseId) {
        ApiPerfStepQuery apiPerfStepQuery = new ApiPerfStepQuery();
        apiPerfStepQuery.setApiPerfId(caseId);
        List<ApiPerfStep> apiPerfStepList = findApiPerfStepList(apiPerfStepQuery);
        for(ApiPerfStep apiPerfStep : apiPerfStepList){
            apiPerfStepDao.deleteApiPerfStep(apiPerfStep.getId());
        }

    }


    @Override
    public ApiPerfStep findOne(String id) {
        ApiPerfStepEntity apiPerfStepEntity = apiPerfStepDao.findApiPerfStep(id);

        ApiPerfStep apiPerfStep = BeanMapper.map(apiPerfStepEntity, ApiPerfStep.class);
        return apiPerfStep;
    }

    @Override
    public List<ApiPerfStep> findList(List<String> idList) {
        List<ApiPerfStepEntity> apiPerfStepEntityList =  apiPerfStepDao.findApiPerfStepList(idList);

        List<ApiPerfStep> apiPerfStepList =  BeanMapper.mapList(apiPerfStepEntityList, ApiPerfStep.class);
        return apiPerfStepList;
    }

    @Override
    public ApiPerfStep findApiPerfStep(@NotNull String id) {
        ApiPerfStep apiPerfStep = findOne(id);

        joinTemplate.joinQuery(apiPerfStep);

        return apiPerfStep;
    }

    @Override
    public int findApiPerfStepNum(String caseId) {
        int stepNum = apiPerfStepDao.findStepNum(caseId);
        return stepNum;
    }

    @Override
    public Boolean isApiSceneExist(String caseId) {
        Integer apiSceneExist = apiPerfStepDao.isApiSceneExist(caseId);

        if(apiSceneExist!=null&& apiSceneExist>0){
            return true;
        }else {
            return false;
        }
    }

    @Override
    public List<ApiPerfStep> findAllApiPerfStep() {
        List<ApiPerfStepEntity> apiPerfStepEntityList =  apiPerfStepDao.findAllApiPerfStep();

        List<ApiPerfStep> apiPerfStepList =  BeanMapper.mapList(apiPerfStepEntityList, ApiPerfStep.class);

        joinTemplate.joinQuery(apiPerfStepList);

        return apiPerfStepList;
    }

    @Override
    public List<ApiPerfStep> findApiPerfStepList(ApiPerfStepQuery apiPerfStepQuery) {
        List<ApiPerfStepEntity> apiPerfStepEntityList = apiPerfStepDao.findApiPerfStepList(apiPerfStepQuery);
        List<ApiPerfStep> apiPerfStepList = BeanMapper.mapList(apiPerfStepEntityList, ApiPerfStep.class);
        joinTemplate.joinQuery(apiPerfStepList);

        if (!apiPerfStepList.isEmpty()) {
            for (ApiPerfStep apiPerfStep : apiPerfStepList) {
                String caseType = apiPerfStep.getCaseType();
                String caseId = apiPerfStep.getCaseId();

                if (MagicValue.CASE_TYPE_API_UNIT.equals(caseType)) {
                    ApiUnitCase apiUnitCase = apiUnitCaseService.findApiUnitCase(caseId);
                    apiPerfStep.setApiUnitCase(apiUnitCase);
                }
                if (MagicValue.CASE_TYPE_API_SCENE.equals(caseType)) {
                    ApiSceneCase apiSceneCase = apiSceneCaseService.findApiSceneCase(caseId);
                    apiPerfStep.setApiScene(apiSceneCase);
                }
            }
        }
        return apiPerfStepList;
    }

    @Override
    public Pagination<ApiPerfStep> findApiPerfStepPage(ApiPerfStepQuery apiPerfStepQuery) {
        Pagination<ApiPerfStepEntity>  pagination = apiPerfStepDao.findApiPerfStepPage(apiPerfStepQuery);

        List<ApiPerfStep> apiPerfStepList = BeanMapper.mapList(pagination.getDataList(), ApiPerfStep.class);

        joinTemplate.joinQuery(apiPerfStepList);

        return PaginationBuilder.build(pagination, apiPerfStepList);
    }

    @Override
    public void bindApiScene(List<ApiPerfStep> apiSceneStepList) {
        //循环添加
        for(ApiPerfStep apiPerfStep : apiSceneStepList){
            createApiPerfStep(apiPerfStep);
        }
    }

    @Override
    public Pagination<TestCase> findApiPerfStepWillBindCasePage(ApiPerfStepQuery apiPerfStepQuery){
        TestCaseQuery testCaseQuery = new TestCaseQuery();
        String[] caseList = new String[]{MagicValue.CASE_TYPE_API_UNIT, MagicValue.CASE_TYPE_API_SCENE};
        testCaseQuery.setCaseTypeList(caseList);
        testCaseQuery.setWorkspaceId(apiPerfStepQuery.getWorkspaceId());
        testCaseQuery.setPageParam(apiPerfStepQuery.getPageParam());
        testCaseQuery.setName(apiPerfStepQuery.getName());
        Pagination<TestCase> testCasePage = testCaseService.findTestCasePage(testCaseQuery);

        List<ApiPerfStepEntity> apiPerfStepEntityList = apiPerfStepDao.findApiPerfStepList(apiPerfStepQuery);
        List<ApiPerfStep> apiPerfStepList = BeanMapper.mapList(apiPerfStepEntityList, ApiPerfStep.class);

        if (testCasePage.getDataList() != null && apiPerfStepList != null) {

            Set<String> apiPerfStepCaseIds = apiPerfStepList.stream()
                    .map(ApiPerfStep::getCaseId)
                    .collect(Collectors.toSet());


            testCasePage.setDataList(testCasePage.getDataList().stream()
                    .filter(testCase -> !apiPerfStepCaseIds.contains(testCase.getId()))
                    .collect(Collectors.toList()));
        }

        return testCasePage;
    }

}