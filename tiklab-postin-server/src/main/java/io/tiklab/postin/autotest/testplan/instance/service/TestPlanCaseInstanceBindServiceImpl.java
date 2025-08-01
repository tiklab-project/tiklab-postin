package io.tiklab.postin.autotest.testplan.instance.service;

import io.tiklab.postin.common.MagicValue;
import io.tiklab.postin.autotest.http.perf.instance.model.ApiPerfInstance;
import io.tiklab.postin.autotest.http.perf.instance.service.ApiPerfInstanceService;
import io.tiklab.postin.autotest.http.scene.instance.model.ApiSceneInstance;
import io.tiklab.postin.autotest.http.scene.instance.service.ApiSceneInstanceService;
import io.tiklab.postin.autotest.http.unit.instance.model.ApiUnitInstance;
import io.tiklab.postin.autotest.http.unit.instance.service.ApiUnitInstanceService;
import io.tiklab.postin.autotest.testplan.instance.dao.TestPlanCaseInstanceBindDao;
import io.tiklab.postin.autotest.testplan.instance.model.TestPlanCaseInstanceBind;
import io.tiklab.postin.autotest.testplan.instance.model.TestPlanCaseInstanceBindQuery;
import io.tiklab.rpc.annotation.Exporter;
import io.tiklab.postin.autotest.testplan.instance.entity.TestPlanCaseInstanceBindEntity;
import io.tiklab.toolkit.beans.BeanMapper;
import io.tiklab.core.page.Pagination;
import io.tiklab.core.page.PaginationBuilder;
import io.tiklab.toolkit.join.JoinTemplate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;


/**
* 测试计划下用例的实例中间层 服务
*/
@Service
@Exporter
public class TestPlanCaseInstanceBindServiceImpl implements TestPlanCaseInstanceBindService {

    @Autowired
    TestPlanCaseInstanceBindDao testPlanCaseInstanceBindDao;

    @Autowired
    JoinTemplate joinTemplate;

    @Autowired
    ApiUnitInstanceService apiUnitInstanceService;

    @Autowired
    ApiSceneInstanceService apiSceneInstanceService;

    @Autowired
    ApiPerfInstanceService apiPerfInstanceService;


    @Override
    public String createTestPlanCaseInstanceBind(@NotNull @Valid TestPlanCaseInstanceBind testPlanCaseInstanceBind) {
        TestPlanCaseInstanceBindEntity testPlanCaseInstanceBindEntity = BeanMapper.map(testPlanCaseInstanceBind, TestPlanCaseInstanceBindEntity.class);

        return testPlanCaseInstanceBindDao.createTestPlanCaseInstanceBind(testPlanCaseInstanceBindEntity);
    }

    @Override
    public void updateTestPlanCaseInstanceBind(@NotNull @Valid TestPlanCaseInstanceBind testPlanCaseInstanceBind) {
        TestPlanCaseInstanceBindEntity testPlanCaseInstanceBindEntity = BeanMapper.map(testPlanCaseInstanceBind, TestPlanCaseInstanceBindEntity.class);

        testPlanCaseInstanceBindDao.updateTestPlanCaseInstanceBind(testPlanCaseInstanceBindEntity);
    }

    @Override
    public void deleteTestPlanCaseInstanceBind(@NotNull String id) {
        testPlanCaseInstanceBindDao.deleteTestPlanCaseInstanceBind(id);


    }

    @Override
    public TestPlanCaseInstanceBind findOne(String id) {
        TestPlanCaseInstanceBindEntity testPlanCaseInstanceBindEntity = testPlanCaseInstanceBindDao.findTestPlanCaseInstanceBind(id);

        TestPlanCaseInstanceBind testPlanCaseInstanceBind = BeanMapper.map(testPlanCaseInstanceBindEntity, TestPlanCaseInstanceBind.class);
        return testPlanCaseInstanceBind;
    }

    @Override
    public List<TestPlanCaseInstanceBind> findList(List<String> idList) {
        List<TestPlanCaseInstanceBindEntity> testPlanCaseInstanceBindEntityList =  testPlanCaseInstanceBindDao.findTestPlanCaseInstanceBindList(idList);

        List<TestPlanCaseInstanceBind> testPlanCaseInstanceBindList =  BeanMapper.mapList(testPlanCaseInstanceBindEntityList,TestPlanCaseInstanceBind.class);
        return testPlanCaseInstanceBindList;
    }

    @Override
    public TestPlanCaseInstanceBind findTestPlanCaseInstanceBind(@NotNull String id) {
        TestPlanCaseInstanceBind testPlanCaseInstanceBind = findOne(id);

        joinTemplate.joinQuery(testPlanCaseInstanceBind,new String[]{
                "testCase",
                "testPlan"
        });

        return testPlanCaseInstanceBind;
    }

    @Override
    public List<TestPlanCaseInstanceBind> findAllTestPlanCaseInstanceBind() {
        List<TestPlanCaseInstanceBindEntity> testPlanCaseInstanceBindEntityList =  testPlanCaseInstanceBindDao.findAllTestPlanCaseInstanceBind();

        List<TestPlanCaseInstanceBind> testPlanCaseInstanceBindList =  BeanMapper.mapList(testPlanCaseInstanceBindEntityList,TestPlanCaseInstanceBind.class);

        joinTemplate.joinQuery(testPlanCaseInstanceBindList,new String[]{
                "testCase",
                "testPlan"
        });

        return testPlanCaseInstanceBindList;
    }

    @Override
    public List<TestPlanCaseInstanceBind> findTestPlanCaseInstanceBindList(TestPlanCaseInstanceBindQuery testPlanCaseInstanceBindQuery) {
        List<TestPlanCaseInstanceBindEntity> testPlanCaseInstanceBindEntityList = testPlanCaseInstanceBindDao.findTestPlanCaseInstanceBindList(testPlanCaseInstanceBindQuery);

        List<TestPlanCaseInstanceBind> testPlanCaseInstanceBindList = BeanMapper.mapList(testPlanCaseInstanceBindEntityList,TestPlanCaseInstanceBind.class);

        joinTemplate.joinQuery(testPlanCaseInstanceBindList,new String[]{
                "testCase",
                "testPlan"
        });

        return testPlanCaseInstanceBindList;
    }

    @Override
    public Pagination<TestPlanCaseInstanceBind> findTestPlanCaseInstanceBindPage(TestPlanCaseInstanceBindQuery testPlanCaseInstanceBindQuery) {
        Pagination<TestPlanCaseInstanceBindEntity>  pagination = testPlanCaseInstanceBindDao.findTestPlanCaseInstanceBindPage(testPlanCaseInstanceBindQuery);

        List<TestPlanCaseInstanceBind> testPlanCaseInstanceBindList = BeanMapper.mapList(pagination.getDataList(),TestPlanCaseInstanceBind.class);

        joinTemplate.joinQuery(testPlanCaseInstanceBindList,new String[]{
                "testCase",
                "testPlan"
        });

        if(testPlanCaseInstanceBindList != null && testPlanCaseInstanceBindList.size() > 0){
            processBindCaseInstance(testPlanCaseInstanceBindList);
        }

        return PaginationBuilder.build(pagination,testPlanCaseInstanceBindList);
    }

    public void processBindCaseInstance(List<TestPlanCaseInstanceBind> testPlanCaseInstanceBindList){
        for(TestPlanCaseInstanceBind testPlanCaseInstanceBind : testPlanCaseInstanceBindList){
            switch (testPlanCaseInstanceBind.getCaseType()){
                case MagicValue.CASE_TYPE_API_UNIT -> {
                    ApiUnitInstance apiUnitInstance = apiUnitInstanceService.findApiUnitInstance(testPlanCaseInstanceBind.getCaseInstanceId());
                    testPlanCaseInstanceBind.setApiUnitInstance(apiUnitInstance);
                }
                case MagicValue.CASE_TYPE_API_SCENE -> {
                    ApiSceneInstance apiSceneInstance = apiSceneInstanceService.findOne(testPlanCaseInstanceBind.getCaseInstanceId());
                    testPlanCaseInstanceBind.setApiSceneInstance(apiSceneInstance);
                }
                case MagicValue.CASE_TYPE_API_PERFORM -> {
                    ApiPerfInstance apiPerfInstance = apiPerfInstanceService.findOne(testPlanCaseInstanceBind.getCaseInstanceId());
                    testPlanCaseInstanceBind.setApiPerfInstance(apiPerfInstance);
                }
            }
        }
    }
}

