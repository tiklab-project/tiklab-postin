package io.tiklab.postin.autotest.http.scene.cases.service;

import io.tiklab.postin.autotest.category.model.CategoryAuto;
import io.tiklab.postin.autotest.instance.service.InstanceService;
import io.tiklab.postin.autotest.http.perf.cases.service.ApiPerfStepService;
import io.tiklab.postin.autotest.http.scene.cases.model.ApiSceneCase;
import io.tiklab.postin.autotest.http.scene.cases.model.ApiSceneCaseQuery;
import io.tiklab.postin.autotest.common.stepcommon.service.StepCommonService;
import io.tiklab.postin.autotest.test.model.TestCase;
import io.tiklab.postin.autotest.test.model.TestCaseQuery;
import io.tiklab.postin.autotest.test.service.TestCaseService;
import io.tiklab.postin.autotest.category.service.CategoryAutoService;
import io.tiklab.postin.autotest.http.scene.cases.dao.ApiSceneCaseDao;
import io.tiklab.postin.autotest.http.scene.cases.entity.ApiSceneCaseEntity;
import io.tiklab.postin.support.environment.service.EnvVariableService;
import io.tiklab.toolkit.beans.BeanMapper;
import io.tiklab.core.page.Pagination;
import io.tiklab.core.page.PaginationBuilder;
import io.tiklab.toolkit.join.JoinTemplate;

import io.tiklab.user.user.model.User;
import io.tiklab.user.user.service.UserProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

/**
* 接口场景 服务
*/
@Service
public class ApiSceneCaseServiceImpl implements ApiSceneCaseService {

    @Autowired
    ApiSceneCaseDao apiSceneCaseDao;

    @Autowired
    JoinTemplate joinTemplate;

    @Autowired
    TestCaseService testCaseService;

    @Autowired
    CategoryAutoService categoryAutoService;

    @Autowired
    UserProcessor userService;

    @Autowired
    StepCommonService stepCommonService;

    @Autowired
    EnvVariableService variableService;

    @Autowired
    InstanceService instanceService;

    @Autowired
    ApiPerfStepService apiPerfStepService;


    @Override
    public String createApiSceneCase(@NotNull @Valid ApiSceneCase apiSceneCase) {
        ApiSceneCaseEntity apiSceneCaseEntity = BeanMapper.map(apiSceneCase, ApiSceneCaseEntity.class);
        String id = apiSceneCaseDao.createApiSceneCase(apiSceneCaseEntity);

        //把testCaseId 设置成与 apiSceneId 一样
        apiSceneCaseEntity.setTestCaseId(id);
        apiSceneCaseEntity.setId(id);
        apiSceneCaseDao.updateApiSceneCase(apiSceneCaseEntity);

        //添加testCase
        TestCase testCase = apiSceneCase.getTestCase();
        testCase.setId(id);
        testCaseService.createTestCase(testCase);

        return id;
    }

    @Override
    public void updateApiSceneCase(@NotNull @Valid ApiSceneCase apiSceneCase) {
        ApiSceneCaseEntity apiSceneCaseEntity = BeanMapper.map(apiSceneCase, ApiSceneCaseEntity.class);

        apiSceneCaseEntity.setTestCaseId(apiSceneCase.getId());
        apiSceneCaseDao.updateApiSceneCase(apiSceneCaseEntity);


        testCaseService.updateTestCase(apiSceneCase.getTestCase());
    }

    @Override
    public void deleteApiSceneCase(@NotNull String id) {
        apiSceneCaseDao.deleteApiSceneCase(id);

        stepCommonService.deleteAllStepCommon(id);

        instanceService.deleteAllInstance(id);
    }

    @Override
    public ApiSceneCase findOne(String id) {
        ApiSceneCaseEntity apiSceneCaseEntity = apiSceneCaseDao.findApiSceneCase(id);

        ApiSceneCase apiSceneCase = BeanMapper.map(apiSceneCaseEntity, ApiSceneCase.class);
        joinTemplate.joinQuery(apiSceneCase,new String[]{
                "testCase"
        });
        return apiSceneCase;
    }

    @Override
    public List<ApiSceneCase> findList(List<String> idList) {
        List<ApiSceneCaseEntity> apiSceneCaseEntityList =  apiSceneCaseDao.findApiSceneCaseList(idList);

        List<ApiSceneCase> apiSceneCaseList =  BeanMapper.mapList(apiSceneCaseEntityList, ApiSceneCase.class);
        return apiSceneCaseList;
    }

    @Override
    public ApiSceneCase findApiSceneCase(@NotNull String id) {
        ApiSceneCase apiSceneCase = findOne(id);
        TestCase testCase = testCaseService.findTestCase(id);
        apiSceneCase.setTestCase(testCase);

        //步骤数量
        int apiSceneStepNum = stepCommonService.findStepNum(id);
        apiSceneCase.setStepNum(apiSceneStepNum);


        int instanceNum = instanceService.findInstanceNum(id);
        apiSceneCase.setInstanceNum(instanceNum);

        if(testCase.getCategory()!=null) {
            CategoryAuto categoryAuto = categoryAutoService.findCategory(testCase.getCategory().getId());
            apiSceneCase.getTestCase().setCategory(categoryAuto);
        }
        if(testCase.getUpdateUser()!=null){
            User updateUser = userService.findUser(testCase.getUpdateUser().getId());
            apiSceneCase.getTestCase().setUpdateUser(updateUser);
        }




        return apiSceneCase;
    }

    @Override
    public List<ApiSceneCase> findAllApiSceneCase() {
        List<ApiSceneCaseEntity> apiSceneCaseEntityList =  apiSceneCaseDao.findAllApiSceneCase();

        List<ApiSceneCase> apiSceneCaseList =  BeanMapper.mapList(apiSceneCaseEntityList, ApiSceneCase.class);

        joinTemplate.joinQuery(apiSceneCaseList);
        return apiSceneCaseList;
    }

    @Override
    public List<ApiSceneCase> findApiSceneCaseList(ApiSceneCaseQuery apiSceneCaseQuery) {
        List<ApiSceneCaseEntity> apiSceneCaseEntityList = apiSceneCaseDao.findApiSceneCaseList(apiSceneCaseQuery);

        List<ApiSceneCase> apiSceneCaseList = BeanMapper.mapList(apiSceneCaseEntityList, ApiSceneCase.class);

        joinTemplate.joinQuery(apiSceneCaseList);

        return apiSceneCaseList;
    }

    @Override
    public List<ApiSceneCase> findApiSceneCaseListByTestCase(TestCaseQuery testCaseQuery){

        List<TestCase> testCaseList = testCaseService.findTestCaseList(testCaseQuery);

        List<ApiSceneCase> apiSceneCaseList = new ArrayList<>();

        if(!ObjectUtils.isEmpty(testCaseList)){
            for (TestCase testcase: testCaseList){
                //因为中间层testcase与 跟在下面的场景id相同，所有直接通过id查询出一个
                ApiSceneCase apiSceneCase = findApiSceneCase(testcase.getId());

                apiSceneCaseList.add(apiSceneCase);

            }
        }

        return apiSceneCaseList;
    }

    @Override
    public Boolean isApiSceneBind(String id) {
        Boolean apiSceneExist = apiPerfStepService.isApiSceneExist(id);

        if(apiSceneExist){
            return true;
        }else {
            return false;
        }
    }

    @Override
    public Pagination<ApiSceneCase> findApiSceneCasePage(ApiSceneCaseQuery apiSceneCaseQuery) {

        Pagination<ApiSceneCaseEntity>  pagination = apiSceneCaseDao.findApiSceneCasePage(apiSceneCaseQuery);


        List<ApiSceneCase> apiSceneCaseList = BeanMapper.mapList(pagination.getDataList(), ApiSceneCase.class);
        joinTemplate.joinQuery(apiSceneCaseList);

        return PaginationBuilder.build(pagination,apiSceneCaseList);
    }





}