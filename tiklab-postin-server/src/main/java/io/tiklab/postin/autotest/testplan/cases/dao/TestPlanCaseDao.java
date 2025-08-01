package io.tiklab.postin.autotest.testplan.cases.dao;

import io.tiklab.postin.common.MagicValue;
import io.tiklab.postin.autotest.testplan.cases.entity.TestPlanCaseEntity;
import io.tiklab.core.page.Pagination;
import io.tiklab.dal.jpa.criterial.condition.DeleteCondition;
import io.tiklab.dal.jpa.criterial.condition.QueryCondition;
import io.tiklab.dal.jpa.criterial.conditionbuilder.QueryBuilders;
import io.tiklab.postin.autotest.testplan.cases.model.TestPlanCaseQuery;
import io.tiklab.dal.jpa.JpaTemplate;
import io.tiklab.postin.autotest.testplan.cases.entity.PlanCaseEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * 测试计划绑定的用例 服务接口
 */
@Repository
public class TestPlanCaseDao {

    private static Logger logger = LoggerFactory.getLogger(TestPlanCaseDao.class);

    @Autowired
    JpaTemplate jpaTemplate;

    /**
     * 创建绑定的用例
     * @param testPlanCaseEntity
     * @return
     */
    public String createTestPlanCase(TestPlanCaseEntity testPlanCaseEntity) {
        return jpaTemplate.save(testPlanCaseEntity,String.class);
    }

    /**
     * 更新绑定的用例
     * @param testPlanCaseEntity
     */
    public void updateTestPlanCase(TestPlanCaseEntity testPlanCaseEntity){
        jpaTemplate.update(testPlanCaseEntity);
    }

    /**
     * 删除绑定的用例
     * @param id
     */
    public void deleteTestPlanCase(String id){
        jpaTemplate.delete(TestPlanCaseEntity.class,id);
    }

    public void deleteTestPlanCase(DeleteCondition deleteCondition){
        jpaTemplate.delete(deleteCondition);
    }

    /**
     * 根据id查找绑定的用例
     * @param id
     * @return
     */
    public TestPlanCaseEntity findTestPlanCase(String id){
        return jpaTemplate.findOne(TestPlanCaseEntity.class,id);
    }

    /**
    * 查找所有绑定的用例
    * @return
    */
    public List<TestPlanCaseEntity> findAllTestPlanCase() {
        return jpaTemplate.findAll(TestPlanCaseEntity.class);
    }

    public List<TestPlanCaseEntity> findTestPlanCaseList(List<String> idList) {
        return jpaTemplate.findList(TestPlanCaseEntity.class,idList);
    }

    /**
     * 查询绑定的用例 列表
     * @param testPlanCaseQuery
     * @return
     */
    public List<TestPlanCaseEntity> findTestPlanCaseList(TestPlanCaseQuery testPlanCaseQuery) {
        QueryCondition queryCondition = QueryBuilders.createQuery(TestPlanCaseEntity.class)
                .eq("testPlanId", testPlanCaseQuery.getTestPlanId())
                .orders(testPlanCaseQuery.getOrderParams())
                .get();
        return jpaTemplate.findList(queryCondition, TestPlanCaseEntity.class);
    }

    /**
     * 按分页查询绑定的用例
     * @param testPlanCaseQuery
     * @return
     */
    public Pagination<TestPlanCaseEntity> findTestPlanCasePage(TestPlanCaseQuery testPlanCaseQuery) {
        QueryCondition queryCondition = QueryBuilders.createQuery(TestPlanCaseEntity.class)
                .eq("testPlanId", testPlanCaseQuery.getTestPlanId())
                .orders(testPlanCaseQuery.getOrderParams())
                .pagination(testPlanCaseQuery.getPageParam())
                .get();
        return jpaTemplate.findPage(queryCondition, TestPlanCaseEntity.class);
    }

    /**
     * 查询测试计划绑定的用例总数
     * @param testPlanId
     * @return
     */
    public int findPlanCaseNum(String testPlanId) {
        String sql = "Select count(1) as total from autotest_test_plan_detail where test_plan_id = '" + testPlanId+ "'";
        Integer total = jpaTemplate.getJdbcTemplate().queryForObject(sql, new Object[]{}, Integer.class);

        return total;
    }


    /**
     * 查询绑定的用例 列表
     * @param testPlanCaseQuery
     * @return
     */
    public List<PlanCaseEntity> findPlanCaseList(TestPlanCaseQuery testPlanCaseQuery) {
        StringBuilder modelSqlBuilder = new StringBuilder();
        modelSqlBuilder.append("SELECT autotest_test_plan_detail.id AS plan_case_id,autotest_testcase.id, autotest_testcase.create_user, autotest_testcase.create_time, autotest_testcase.case_type, autotest_testcase.category_id, autotest_testcase.name ")
                .append("FROM autotest_test_plan_detail ")
                .append("JOIN autotest_testcase ON autotest_testcase.id = autotest_test_plan_detail.test_case_id ")
                .append("JOIN autotest_test_plan ON autotest_test_plan.id = autotest_test_plan_detail.test_plan_id ")
                .append("WHERE autotest_test_plan.id = '").append(testPlanCaseQuery.getTestPlanId()).append("'");

        if(testPlanCaseQuery.getCaseTypeList()!=null&&testPlanCaseQuery.getCaseTypeList().length>0){
            StringBuilder caseTypeList = new StringBuilder();
            for (int i = 0; i < testPlanCaseQuery.getCaseTypeList().length; i++) {
                String s = testPlanCaseQuery.getCaseTypeList()[i];
                caseTypeList.append("'").append(s).append("'");

                if (i < testPlanCaseQuery.getCaseTypeList().length - 1) {
                    caseTypeList.append(",");
                }
            }

            modelSqlBuilder.append(" AND autotest_testcase.case_type in (").append(caseTypeList).append(")");
        }

        String modelSql = modelSqlBuilder.toString();
        List<PlanCaseEntity> planCaseEntityList = jpaTemplate.getJdbcTemplate()
                .query(modelSql,new BeanPropertyRowMapper<>(PlanCaseEntity.class));

        return planCaseEntityList;
    }


    /**
     * 按分页查询绑定的用例
     * @param testPlanCaseQuery
     * @return
     */
    public Pagination<PlanCaseEntity> findPlanCasePage(TestPlanCaseQuery testPlanCaseQuery) {
        StringBuilder modelSqlBuilder = new StringBuilder();
        modelSqlBuilder.append("SELECT autotest_test_plan_detail.id AS plan_case_id, autotest_testcase.id, autotest_testcase.create_user, autotest_testcase.create_time, ")
                .append("autotest_testcase.case_type, autotest_testcase.category_id, autotest_testcase.name ");

        modelSqlBuilder.append("FROM autotest_test_plan_detail ")
                .append("JOIN autotest_testcase ON autotest_testcase.id = autotest_test_plan_detail.test_case_id ")
                .append("JOIN autotest_test_plan ON autotest_test_plan.id = autotest_test_plan_detail.test_plan_id ");

        modelSqlBuilder.append("WHERE autotest_test_plan.id = '").append(testPlanCaseQuery.getTestPlanId()).append("'");

        if (testPlanCaseQuery.getName() != null) {
            modelSqlBuilder.append(" AND autotest_testcase.name LIKE '%").append(testPlanCaseQuery.getName()).append("%'");
        }

        String categoryId = testPlanCaseQuery.getCategoryId();
        if (categoryId != null && !"nullstring".equals(categoryId)) {
            // 如果 categoryId 不为 null 且不是 "nullstring"，则按指定 categoryId 查询
            modelSqlBuilder.append(" AND autotest_testcase.category_id = '").append(categoryId).append("'");
        } else if ("nullstring".equals(categoryId)) {
            // 如果 categoryId 是 "nullstring"，则只查询 category_id 为 null 的记录
            modelSqlBuilder.append(" AND autotest_testcase.category_id IS NULL");
        }

        if (testPlanCaseQuery.getCaseType() != null) {
            modelSqlBuilder.append(" AND autotest_testcase.case_type = '").append(testPlanCaseQuery.getCaseType()).append("'");
        }

        if (testPlanCaseQuery.getCaseTypeList() != null && testPlanCaseQuery.getCaseTypeList().length > 0) {
            String caseTypeList = String.join(",", Arrays.stream(testPlanCaseQuery.getCaseTypeList())
                    .map(type -> "'" + type + "'")
                    .toArray(String[]::new));
            modelSqlBuilder.append(" AND autotest_testcase.case_type IN (").append(caseTypeList).append(")");
        }

        String modelSql = modelSqlBuilder.toString();
        Pagination<PlanCaseEntity> page = jpaTemplate.getJdbcTemplate().findPage(
                modelSql, new Object[]{}, testPlanCaseQuery.getPageParam(), new BeanPropertyRowMapper<>(PlanCaseEntity.class)
        );
        return page;
    }



    /**
     * 查询未关联的用例
     * @param testPlanCaseQuery
     * @return
     */
    public Pagination<PlanCaseEntity> findTestCasePage(TestPlanCaseQuery testPlanCaseQuery) {
        StringBuilder modelSqlBuilder = new StringBuilder();
        modelSqlBuilder.append("SELECT  autotest_testcase.id AS plan_case_id, autotest_testcase.id, autotest_testcase.director,  autotest_testcase.create_user, autotest_testcase.create_time, autotest_testcase.case_type, autotest_testcase.category_id, autotest_testcase.name, autotest_testcase.status,autotest_testcase.priority_level ")
                .append(" FROM autotest_testcase ")
                .append(" WHERE autotest_testcase.workspace_id = '").append(testPlanCaseQuery.getWorkspaceId()).append("'")
                .append(" AND NOT EXISTS (  ")
                .append(" SELECT 1   ")
                .append(" FROM autotest_test_plan_detail  ")
                .append(" JOIN autotest_test_plan ON autotest_test_plan.id = autotest_test_plan_detail.test_plan_id ")
                .append(" WHERE autotest_test_plan_detail.test_case_id = autotest_testcase.id ")
                .append(" AND autotest_test_plan.id = '").append(testPlanCaseQuery.getTestPlanId()).append("')");

        if (testPlanCaseQuery.getName() != null) {
            modelSqlBuilder.append(" AND autotest_testcase.name LIKE '%").append(testPlanCaseQuery.getName()).append("%'");
        }

        if(testPlanCaseQuery.getCaseTypeList()!=null&&testPlanCaseQuery.getCaseTypeList().length>0){
            StringBuilder caseTypeList = new StringBuilder();
            for (int i = 0; i < testPlanCaseQuery.getCaseTypeList().length; i++) {
                String s = testPlanCaseQuery.getCaseTypeList()[i];
                caseTypeList.append("'").append(s).append("'");

                if (i < testPlanCaseQuery.getCaseTypeList().length - 1) {
                    caseTypeList.append(",");
                }
            }

            modelSqlBuilder.append(" AND autotest_testcase.case_type in (").append(caseTypeList).append(")");
        }

        String modelSql = modelSqlBuilder.toString();

        Pagination<PlanCaseEntity> page = jpaTemplate.getJdbcTemplate().findPage(modelSql, new Object[]{}, testPlanCaseQuery.getPageParam(), new BeanPropertyRowMapper<>(PlanCaseEntity.class));
        return page;
    }

    /**
     * 通过用例id，判断是否用例被绑定
     * @param caseId
     * @return
     */
    public Integer isCaseExist(String caseId) {
        String sql = "Select count(1) as total from autotest_test_plan_detail where test_case_id = '" + caseId + "'";
        Integer modelTotal = jpaTemplate.getJdbcTemplate().queryForObject(sql, new Object[]{}, Integer.class);

        return modelTotal;
    }

    /**
     * 获取不同用例类型的数量
     * @param testPlanId
     * @return
     */
    public List<Map<String, Object>> getCaseTypeNum(String testPlanId){
        String sql = "SELECT tc.case_type, COUNT(*) AS total " +
                "FROM autotest_test_plan_detail AS tpd " +
                "INNER JOIN autotest_testcase AS tc ON tpd.test_case_id = tc.id " +
                "INNER JOIN autotest_test_plan AS tp ON tpd.test_plan_id = tp.id " +
                "WHERE tp.id = ? " +
                "GROUP BY tc.case_type";

        List<Map<String, Object>> maps = jpaTemplate.getJdbcTemplate().queryForList(sql, testPlanId);

        return maps;
    }



}