package io.tiklab.postin.autotest.testplan.instance.dao;

import io.tiklab.postin.autotest.testplan.instance.entity.TestPlanInstanceEntity;
import io.tiklab.postin.autotest.testplan.instance.model.TestPlanInstanceQuery;
import io.tiklab.core.page.Pagination;
import io.tiklab.dal.jpa.JpaTemplate;
import io.tiklab.dal.jpa.criterial.condition.DeleteCondition;
import io.tiklab.dal.jpa.criterial.condition.QueryCondition;
import io.tiklab.dal.jpa.criterial.conditionbuilder.QueryBuilders;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 测试计划实例 数据访问
 */
@Repository
public class TestPlanInstanceDao{

    private static Logger logger = LoggerFactory.getLogger(TestPlanInstanceDao.class);

    @Autowired
    JpaTemplate jpaTemplate;

    /**
     * 创建测试计划实例
     * @param testPlanInstanceEntity
     * @return
     */
    public String createTestPlanInstance(TestPlanInstanceEntity testPlanInstanceEntity) {
        return jpaTemplate.save(testPlanInstanceEntity,String.class);
    }

    /**
     * 更新测试计划实例
     * @param testPlanInstanceEntity
     */
    public void updateTestPlanInstance(TestPlanInstanceEntity testPlanInstanceEntity){
        jpaTemplate.update(testPlanInstanceEntity);
    }

    /**
     * 删除测试计划实例
     * @param id
     */
    public void deleteTestPlanInstance(String id){
        jpaTemplate.delete(TestPlanInstanceEntity.class,id);
    }

    public void deleteTestPlanInstance(DeleteCondition deleteCondition){
        jpaTemplate.delete(deleteCondition);
    }

    /**
     * 根据id查找测试计划实例
     * @param id
     * @return
     */
    public TestPlanInstanceEntity findTestPlanInstance(String id){
        return jpaTemplate.findOne(TestPlanInstanceEntity.class,id);
    }

    public TestPlanInstanceEntity findRecentPlanInstance(String testPlanId){
        try {
            String sql = "SELECT * FROM autotest_test_plan_instance WHERE test_plan_id = ? ORDER BY create_time DESC LIMIT 1";

            TestPlanInstanceEntity testPlanInstanceEntity = jpaTemplate.getJdbcTemplate()
                    .queryForObject(sql, new Object[]{testPlanId}, new BeanPropertyRowMapper<>(TestPlanInstanceEntity.class));

            return testPlanInstanceEntity;
        } catch (EmptyResultDataAccessException e) {
            return null;
        } catch (DataAccessException e) {
            return null;
        }

    }


    /**
    * 查找所有测试计划实例
    * @return
    */
    public List<TestPlanInstanceEntity> findAllTestPlanInstance() {
        return jpaTemplate.findAll(TestPlanInstanceEntity.class);
    }

    public List<TestPlanInstanceEntity> findTestPlanInstanceList(List<String> idList) {
        return jpaTemplate.findList(TestPlanInstanceEntity.class,idList);
    }

    /**
     * 根据查询参数查询测试计划实例列表
     * @param testPlanInstanceQuery
     * @return
     */
    public List<TestPlanInstanceEntity> findTestPlanInstanceList(TestPlanInstanceQuery testPlanInstanceQuery) {
        QueryCondition queryCondition = QueryBuilders.createQuery(TestPlanInstanceEntity.class)
                .eq("testPlanId", testPlanInstanceQuery.getTestPlanId())
                .eq("workspaceId",testPlanInstanceQuery.getWorkspaceId())
                .orders(testPlanInstanceQuery.getOrderParams())
                .get();
        return jpaTemplate.findList(queryCondition,TestPlanInstanceEntity.class);
    }

    /**
     * 根据查询参数按分页查询测试计划实例
     * @param testPlanInstanceQuery
     * @return
     */
    public Pagination<TestPlanInstanceEntity> findTestPlanInstancePage(TestPlanInstanceQuery testPlanInstanceQuery) {
        QueryCondition queryCondition = QueryBuilders.createQuery(TestPlanInstanceEntity.class)
                .eq("testPlanId", testPlanInstanceQuery.getTestPlanId())
                .eq("workspaceId",testPlanInstanceQuery.getWorkspaceId())
                .orders(testPlanInstanceQuery.getOrderParams())
                .pagination(testPlanInstanceQuery.getPageParam())
                .get();
        return jpaTemplate.findPage(queryCondition,TestPlanInstanceEntity.class);
    }


    public Pagination<TestPlanInstanceEntity> findPlanCasePage(TestPlanInstanceQuery testPlanInstanceQuery){
        StringBuilder modelSqlBuilder = new StringBuilder();
        modelSqlBuilder.append("SELECT autotest_test_plan_instance.* ")
                .append(" FROM autotest_test_plan_instance ")
                .append(" JOIN autotest_test_plan on autotest_test_plan_instance.test_plan_id = autotest_test_plan.id");

        if(testPlanInstanceQuery.getTestPlanId() != null){
            modelSqlBuilder.append(" WHERE autotest_test_plan_instance.test_plan_id = '").append(testPlanInstanceQuery.getTestPlanId()).append("'");
        }

        if (testPlanInstanceQuery.getName() != null) {
            if (testPlanInstanceQuery.getTestPlanId() != null) {
                modelSqlBuilder.append(" OR");
            }else {
                modelSqlBuilder.append(" WHERE");
            }
            modelSqlBuilder.append(" autotest_test_plan.name LIKE '%").append(testPlanInstanceQuery.getName()).append("%'");
        }

        if(testPlanInstanceQuery.getCreateUser() !=null){
            if (testPlanInstanceQuery.getName() != null||testPlanInstanceQuery.getTestPlanId() != null) {
                modelSqlBuilder.append(" OR");
            }else {
                modelSqlBuilder.append(" WHERE");
            }
            modelSqlBuilder.append(" autotest_test_plan_instance.create_user =  '").append(testPlanInstanceQuery.getCreateUser()).append("'");
        }

        String modelSql = modelSqlBuilder.toString();

        Pagination<TestPlanInstanceEntity> page = jpaTemplate.getJdbcTemplate().findPage(modelSql, new Object[]{}, testPlanInstanceQuery.getPageParam(), new BeanPropertyRowMapper<>(TestPlanInstanceEntity.class));
        return page;
    }

}