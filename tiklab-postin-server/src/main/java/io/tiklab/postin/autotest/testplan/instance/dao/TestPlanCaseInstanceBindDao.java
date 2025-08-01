package io.tiklab.postin.autotest.testplan.instance.dao;

import io.tiklab.postin.autotest.testplan.instance.entity.TestPlanCaseInstanceBindEntity;
import io.tiklab.postin.autotest.testplan.instance.model.TestPlanCaseInstanceBindQuery;
import io.tiklab.core.page.Pagination;
import io.tiklab.dal.jpa.JpaTemplate;
import io.tiklab.dal.jpa.criterial.condition.DeleteCondition;
import io.tiklab.dal.jpa.criterial.condition.QueryCondition;
import io.tiklab.dal.jpa.criterial.conditionbuilder.QueryBuilders;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 测试计划下用例的实例中间层 数据访问
 */
@Repository
public class TestPlanCaseInstanceBindDao{

    private static Logger logger = LoggerFactory.getLogger(TestPlanCaseInstanceBindDao.class);

    @Autowired
    JpaTemplate jpaTemplate;

    /**
     * 创建用例的实例中间层
     * @param testPlanCaseInstanceBindEntity
     * @return
     */
    public String createTestPlanCaseInstanceBind(TestPlanCaseInstanceBindEntity testPlanCaseInstanceBindEntity) {
        return jpaTemplate.save(testPlanCaseInstanceBindEntity,String.class);
    }

    /**
     * 更新用例的实例中间层
     * @param testPlanCaseInstanceBindEntity
     */
    public void updateTestPlanCaseInstanceBind(TestPlanCaseInstanceBindEntity testPlanCaseInstanceBindEntity){
        jpaTemplate.update(testPlanCaseInstanceBindEntity);
    }

    /**
     * 删除用例的实例中间层
     * @param id
     */
    public void deleteTestPlanCaseInstanceBind(String id){
        jpaTemplate.delete(TestPlanCaseInstanceBindEntity.class,id);
    }

    public void deleteTestPlanCaseInstanceBind(DeleteCondition deleteCondition){
        jpaTemplate.delete(deleteCondition);
    }

    /**
     * 查找用例的实例中间层
     * @param id
     * @return
     */
    public TestPlanCaseInstanceBindEntity findTestPlanCaseInstanceBind(String id){
        return jpaTemplate.findOne(TestPlanCaseInstanceBindEntity.class,id);
    }

    /**
    * 查找所有用例的实例中间层
    * @return
    */
    public List<TestPlanCaseInstanceBindEntity> findAllTestPlanCaseInstanceBind() {
        return jpaTemplate.findAll(TestPlanCaseInstanceBindEntity.class);
    }

    public List<TestPlanCaseInstanceBindEntity> findTestPlanCaseInstanceBindList(List<String> idList) {
        return jpaTemplate.findList(TestPlanCaseInstanceBindEntity.class,idList);
    }

    /**
     * 根据查询参数查询用例的实例中间层列表
     * @param testPlanCaseInstanceBindQuery
     * @return
     */
    public List<TestPlanCaseInstanceBindEntity> findTestPlanCaseInstanceBindList(TestPlanCaseInstanceBindQuery testPlanCaseInstanceBindQuery) {
        QueryCondition queryCondition = QueryBuilders.createQuery(TestPlanCaseInstanceBindEntity.class)
                .eq("caseInstanceId", testPlanCaseInstanceBindQuery.getCaseInstanceId())
                .eq("testPlanInstanceId",testPlanCaseInstanceBindQuery.getTestPlanInstanceId())
                .eq("caseId",testPlanCaseInstanceBindQuery.getCaseId())
                .eq("testPlanId",testPlanCaseInstanceBindQuery.getTestPlanId())
                .orders(testPlanCaseInstanceBindQuery.getOrderParams())
                .get();
        return jpaTemplate.findList(queryCondition,TestPlanCaseInstanceBindEntity.class);
    }

    /**
     * 根据查询参数按分页查询用例的实例中间层
     * @param testPlanCaseInstanceBindQuery
     * @return
     */
    public Pagination<TestPlanCaseInstanceBindEntity> findTestPlanCaseInstanceBindPage(TestPlanCaseInstanceBindQuery testPlanCaseInstanceBindQuery) {
        QueryCondition queryCondition = QueryBuilders.createQuery(TestPlanCaseInstanceBindEntity.class)
                .eq("caseInstanceId", testPlanCaseInstanceBindQuery.getCaseInstanceId())
                .eq("testPlanInstanceId",testPlanCaseInstanceBindQuery.getTestPlanInstanceId())
                .eq("caseId",testPlanCaseInstanceBindQuery.getCaseId())
                .eq("testPlanId",testPlanCaseInstanceBindQuery.getTestPlanId())
                .orders(testPlanCaseInstanceBindQuery.getOrderParams())
                .pagination(testPlanCaseInstanceBindQuery.getPageParam())
                .get();
        return jpaTemplate.findPage(queryCondition,TestPlanCaseInstanceBindEntity.class);
    }
}