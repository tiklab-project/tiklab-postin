package io.tiklab.postin.autotest.test.service;

import io.tiklab.core.page.Pagination;
import io.tiklab.core.page.PaginationBuilder;
import io.tiklab.dal.jpa.criterial.condition.DeleteCondition;
import io.tiklab.dal.jpa.criterial.conditionbuilder.DeleteBuilders;
import io.tiklab.eam.common.context.LoginContext;
import io.tiklab.postin.autotest.http.perf.cases.service.ApiPerfCaseService;
import io.tiklab.postin.autotest.http.scene.cases.service.ApiSceneCaseService;
import io.tiklab.postin.autotest.http.unit.cases.service.ApiUnitCaseService;
import io.tiklab.postin.autotest.instance.model.Instance;
import io.tiklab.postin.autotest.instance.model.InstanceQuery;
import io.tiklab.postin.autotest.instance.service.InstanceService;
import io.tiklab.postin.autotest.test.dao.TestCaseDao;
import io.tiklab.postin.autotest.test.entity.TestCasesEntity;
import io.tiklab.postin.autotest.test.model.TestCase;
import io.tiklab.postin.autotest.test.model.TestCaseQuery;
import io.tiklab.postin.common.MagicValue;
import io.tiklab.rpc.annotation.Exporter;

import io.tiklab.toolkit.beans.BeanMapper;
import io.tiklab.toolkit.join.JoinTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
import java.sql.Timestamp;
import java.util.*;
import java.util.stream.Collectors;

/**
* 测试用例 服务
*/
@Service
@Exporter
public class TestCaseServiceImpl implements TestCaseService {

    @Autowired
    TestCaseDao testCaseDao;


    @Autowired
    JoinTemplate joinTemplate;

    @Autowired
    InstanceService instanceService;

    @Autowired
    ApiUnitCaseService apiUnitCaseService;

    @Autowired
    ApiSceneCaseService apiSceneCaseService;

    @Autowired
    ApiPerfCaseService apiPerfCaseService;

    @Override
    public String createTestCase(TestCase testCase) {
        TestCasesEntity testCasesEntity = BeanMapper.map(testCase, TestCasesEntity.class);

        // 设置用例key,  项目key-用例编号
        int i = testCaseDao.bigSort(testCasesEntity.getWorkspaceId());
        int num = i+1;
        testCasesEntity.setSort(num);
        testCasesEntity.setCaseKey("KEY-"+num);

        testCasesEntity.setStatus(0);


        //初始化项目成员
        String userId = LoginContext.getLoginId();
        testCasesEntity.setCreateUser(userId);
        testCasesEntity.setCreateTime(new Timestamp(System.currentTimeMillis()));
        testCasesEntity.setUpdateTime(new Timestamp(System.currentTimeMillis()));

        String id = testCaseDao.createTestCase(testCasesEntity);

        return id;
    }

    @Override
    public void updateTestCase(TestCase testCase) {
        TestCasesEntity testCasesEntity = BeanMapper.map(testCase, TestCasesEntity.class);

        testCasesEntity.setUpdateTime(new Timestamp(System.currentTimeMillis()));

        String userId = LoginContext.getLoginId();
        testCasesEntity.setUpdateUser(userId);

        testCaseDao.updateTestCase(testCasesEntity);
    }

    @Override
    public void deleteTestCase(@NotNull String id,String caseType) {
        //删除用例
        testCaseDao.deleteTestCase(id);

        //删除对应的用例
        switch (caseType) {
            case MagicValue.CASE_TYPE_API_UNIT -> {
                apiUnitCaseService.deleteApiUnitCase(id);
                break;
            }
            case MagicValue.CASE_TYPE_API_SCENE -> {
                apiSceneCaseService.deleteApiSceneCase(id);
                break;
            }
            case MagicValue.CASE_TYPE_API_PERFORM -> {
                apiPerfCaseService.deleteApiPerfCase(id);
                break;
            }
            default -> {
            }
        }
    }

    @Override
    public void deleteAllTestCase( String workspaceId) {
        TestCaseQuery testCaseQuery = new TestCaseQuery();
        testCaseQuery.setWorkspaceId(workspaceId);
        List<TestCase> testCaseList = findTestCaseList(testCaseQuery);
        for(TestCase testCase:testCaseList){
            deleteTestCase(testCase.getId(),testCase.getCaseType());
        }
    }




    @Override
    public void deleteTestCaseByCategoryId(String categoryId) {
        //删除相关联的子表
        DeleteCondition deleteCondition = DeleteBuilders.createDelete(TestCasesEntity.class)
                .eq("categoryId", categoryId)
                .get();
        testCaseDao.deleteTestCase(deleteCondition);
    }

    @Override
    public TestCase findOne(String id) {
        TestCasesEntity testCasesEntity = testCaseDao.findTestCase(id);

        TestCase testCase = BeanMapper.map(testCasesEntity, TestCase.class);

        return testCase;
    }

    @Override
    public List<TestCase> findList(List<String> idList) {
        List<TestCasesEntity> testCaseList = testCaseDao.findTestCaseList(idList);

        List<TestCase> mapList = BeanMapper.mapList(testCaseList, TestCase.class);

        return mapList;
    }

    @Override
    public int findTestCaseNum(String workspaceId) {
        int testCaseNum = testCaseDao.findTestCaseNum(workspaceId);
        return testCaseNum;
    }

    @Override
    public int findTestCaseNumByCategoryId(String categoryId) {
        int testCaseNum = testCaseDao.findTestCaseNumByCategoryId(categoryId);
        return testCaseNum;
    }


    @Override
    public HashMap<String, Integer> findDiffTypeCaseNum(String workspaceId) {
        HashMap<String, Integer> diffTypeCaseNum = testCaseDao.findDiffTypeCaseNum(workspaceId);
        return diffTypeCaseNum;
    }

    @Override
    public TestCase findTestCase(@NotNull String id) {
        TestCase testCase = findOne(id);

        joinTemplate.joinQuery(testCase,new String[]{
                "category",
                "repository",
                "updateUser",
                "createUser",
                "director"
        });

        return testCase;
    }

    @Override
    public List<TestCase> findAllTestCase() {
        List<TestCasesEntity> testCasesEntityList = testCaseDao.findAllTestCase();

        List<TestCase> testCaseList = BeanMapper.mapList(testCasesEntityList, TestCase.class);

        joinTemplate.joinQuery(testCaseList);

        return testCaseList;
    }

    @Override
    public List<TestCase> findTestCaseList(TestCaseQuery testCaseQuery) {
        List<TestCasesEntity> testCasesEntityList = testCaseDao.findTestCaseList(testCaseQuery);

        List<TestCase> testCaseList = BeanMapper.mapList(testCasesEntityList, TestCase.class);

        joinTemplate.joinQuery(testCaseList);

        return testCaseList;
    }

    @Override
    public Pagination<TestCase> findTestCasePage(TestCaseQuery testCaseQuery) {
        Pagination<TestCasesEntity> pagination = testCaseDao.findTestCasePage(testCaseQuery);

        List<TestCase> testCaseList = BeanMapper.mapList(pagination.getDataList(), TestCase.class);

        joinTemplate.joinQuery(testCaseList,new String[]{
                "createUser",
                "repository",
                "updateUser",
                "director"
        });

//        List<TestCase> newTestCaseList = recentInstance(testCaseList);

        return PaginationBuilder.build(pagination, testCaseList);
    }

    @Override
    public HashMap<String, Integer> findDiffTestCaseNum(TestCaseQuery testCaseQuery) {
        HashMap<String, Integer> testCaseDiffNum = testCaseDao.findDiffTestCaseNum(testCaseQuery);
        return testCaseDiffNum;
    }


    @Override
    public int countCasesByCategoryId(String categoryId) {
        int caseNum = testCaseDao.countCasesByCategoryId(categoryId);
        if(caseNum>0){
            return caseNum;
        }
        return 0;
    }

    @Override
    public HashMap<String, Integer> findCreateUserAndDirectorNum(String workspaceId) {
        String loginId = LoginContext.getLoginId();
        Integer createUserNum = testCaseDao.countCreateUser(workspaceId, loginId);
        Integer directorNum = testCaseDao.countDirector(workspaceId, loginId);

        HashMap<String, Integer> countMap = new HashMap<>();
        countMap.put("createUserNum", createUserNum);
        countMap.put("directorNum", directorNum);

        return countMap;
    }



}