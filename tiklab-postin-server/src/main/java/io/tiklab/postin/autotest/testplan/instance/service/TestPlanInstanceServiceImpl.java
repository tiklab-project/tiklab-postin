package io.tiklab.postin.autotest.testplan.instance.service;

import io.tiklab.eam.common.context.LoginContext;
import io.tiklab.postin.autotest.instance.model.Instance;
import io.tiklab.postin.autotest.instance.service.InstanceService;
import io.tiklab.postin.autotest.testplan.instance.dao.TestPlanInstanceDao;
import io.tiklab.postin.autotest.testplan.instance.model.TestPlanCaseInstanceBind;
import io.tiklab.postin.autotest.testplan.instance.model.TestPlanCaseInstanceBindQuery;
import io.tiklab.postin.autotest.testplan.instance.model.TestPlanInstance;
import io.tiklab.postin.autotest.testplan.instance.model.TestPlanInstanceQuery;
import io.tiklab.rpc.annotation.Exporter;
import io.tiklab.postin.autotest.testplan.instance.entity.TestPlanInstanceEntity;
import io.tiklab.toolkit.beans.BeanMapper;
import io.tiklab.core.page.Pagination;
import io.tiklab.core.page.PaginationBuilder;
import io.tiklab.toolkit.join.JoinTemplate;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;
import java.util.List;

/**
* 测试计划实例 服务
*/
@Service
@Exporter
public class TestPlanInstanceServiceImpl implements TestPlanInstanceService {

    @Autowired
    TestPlanInstanceDao testPlanInstanceDao;

    @Autowired
    JoinTemplate joinTemplate;

    @Autowired
    TestPlanCaseInstanceBindService testPlanCaseInstanceBindService;

    @Autowired
    InstanceService instanceService;

    @Override
    public String createTestPlanInstance(@NotNull @Valid TestPlanInstance testPlanInstance) {
        testPlanInstance.setCreateTime(new Timestamp(System.currentTimeMillis()));
        testPlanInstance.setCreateUser(LoginContext.getLoginId());

        TestPlanInstanceEntity testPlanInstanceEntity = BeanMapper.map(testPlanInstance, TestPlanInstanceEntity.class);

        return testPlanInstanceDao.createTestPlanInstance(testPlanInstanceEntity);
    }

    @Override
    public void updateTestPlanInstance(@NotNull @Valid TestPlanInstance testPlanInstance) {
        TestPlanInstanceEntity testPlanInstanceEntity = BeanMapper.map(testPlanInstance, TestPlanInstanceEntity.class);

        testPlanInstanceDao.updateTestPlanInstance(testPlanInstanceEntity);
    }

    @Override
    public void deleteTestPlanInstance(@NotNull String id) {
        testPlanInstanceDao.deleteTestPlanInstance(id);

        TestPlanCaseInstanceBindQuery testPlanCaseInstanceBindQuery = new TestPlanCaseInstanceBindQuery();
        testPlanCaseInstanceBindQuery.setTestPlanInstanceId(id);
        List<TestPlanCaseInstanceBind> testPlanCaseInstanceBindList = testPlanCaseInstanceBindService.findTestPlanCaseInstanceBindList(testPlanCaseInstanceBindQuery);
        if(CollectionUtils.isNotEmpty(testPlanCaseInstanceBindList)&&testPlanCaseInstanceBindList.size()>0){
            for(TestPlanCaseInstanceBind testPlanCaseInstanceBind:testPlanCaseInstanceBindList){
                testPlanCaseInstanceBindService.deleteTestPlanCaseInstanceBind(testPlanCaseInstanceBind.getId());
            }
        }

    }

    @Override
    public TestPlanInstance findOne(String id) {
        TestPlanInstanceEntity testPlanInstanceEntity = testPlanInstanceDao.findTestPlanInstance(id);

        TestPlanInstance testPlanInstance = BeanMapper.map(testPlanInstanceEntity, TestPlanInstance.class);
        return testPlanInstance;
    }

    @Override
    public List<TestPlanInstance> findList(List<String> idList) {
        List<TestPlanInstanceEntity> testPlanInstanceEntityList =  testPlanInstanceDao.findTestPlanInstanceList(idList);

        List<TestPlanInstance> testPlanInstanceList =  BeanMapper.mapList(testPlanInstanceEntityList,TestPlanInstance.class);
        return testPlanInstanceList;
    }

    @Override
    public TestPlanInstance findTestPlanInstance(@NotNull String id) {
        TestPlanInstance testPlanInstance = findOne(id);
        joinTemplate.joinQuery(testPlanInstance);

        Instance instance = instanceService.findInstance(id);
        testPlanInstance.setInstance(instance);

        return testPlanInstance;
    }

    @Override
    public TestPlanInstance findRecentPlanInstance(String testPlanId) {
        TestPlanInstanceEntity recentPlanInstanceEntity = testPlanInstanceDao.findRecentPlanInstance(testPlanId);
        TestPlanInstance planInstance = BeanMapper.map(recentPlanInstanceEntity, TestPlanInstance.class);

        return planInstance;
    }

    @Override
    public List<TestPlanInstance> findAllTestPlanInstance() {
        List<TestPlanInstanceEntity> testPlanInstanceEntityList =  testPlanInstanceDao.findAllTestPlanInstance();

        List<TestPlanInstance> testPlanInstanceList =  BeanMapper.mapList(testPlanInstanceEntityList,TestPlanInstance.class);

        joinTemplate.joinQuery(testPlanInstanceList);

        return testPlanInstanceList;
    }

    @Override
    public List<TestPlanInstance> findTestPlanInstanceList(TestPlanInstanceQuery testPlanInstanceQuery) {
        List<TestPlanInstanceEntity> testPlanInstanceEntityList = testPlanInstanceDao.findTestPlanInstanceList(testPlanInstanceQuery);

        List<TestPlanInstance> testPlanInstanceList = BeanMapper.mapList(testPlanInstanceEntityList,TestPlanInstance.class);

        joinTemplate.joinQuery(testPlanInstanceList);

        return testPlanInstanceList;
    }

    @Override
    public Pagination<TestPlanInstance> findTestPlanInstancePage(TestPlanInstanceQuery testPlanInstanceQuery) {
        Pagination<TestPlanInstanceEntity>  pagination = testPlanInstanceDao.findPlanCasePage(testPlanInstanceQuery);

        List<TestPlanInstance> testPlanInstanceList = BeanMapper.mapList(pagination.getDataList(),TestPlanInstance.class);

        joinTemplate.joinQuery(testPlanInstanceList);

        return PaginationBuilder.build(pagination,testPlanInstanceList);
    }


}