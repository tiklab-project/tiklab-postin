package io.tiklab.postin.autotest.test.dao;

import io.tiklab.core.page.Pagination;
import io.tiklab.dal.jpa.JpaTemplate;
import io.tiklab.dal.jpa.criterial.condition.DeleteCondition;
import io.tiklab.dal.jpa.criterial.condition.QueryCondition;
import io.tiklab.dal.jpa.criterial.conditionbuilder.QueryBuilders;
import io.tiklab.postin.autotest.test.entity.TestCasesEntity;
import io.tiklab.postin.autotest.test.model.TestCaseQuery;
import org.apache.commons.lang.ArrayUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 测试用例 数据访问
 */
@Repository
public class TestCaseDao {

    private static Logger logger = LoggerFactory.getLogger(TestCaseDao.class);

    @Autowired
    JpaTemplate jpaTemplate;

    /**
     * 创建测试用例
     * @param testCasesEntity
     * @return
     */
    public String createTestCase(TestCasesEntity testCasesEntity) {
        return jpaTemplate.save(testCasesEntity,String.class);
    }

    /**
     * 更新测试用例
     * @param testCasesEntity
     */
    public void updateTestCase(TestCasesEntity testCasesEntity){
        jpaTemplate.update(testCasesEntity);
    }

    /**
     * 删除测试用例
     * @param id
     */
    public void deleteTestCase(String id){
        jpaTemplate.delete(TestCasesEntity.class,id);
    }

    public void deleteTestCase(DeleteCondition deleteCondition){
        jpaTemplate.delete(deleteCondition);
    }

    /**
     * 通过目录id删除
     * @param id
     * @return
     */
    public TestCasesEntity findTestCase(String id){
        return jpaTemplate.findOne(TestCasesEntity.class,id);
    }

    /**
     * 查询总数
     * @param workspaceId
     * @return
     */
    public int findTestCaseNum(String workspaceId) {
        String caseSql = "Select count(1) as total from autotest_testcase where workspace_id = '" + workspaceId+ "'";
        Integer caseTotal = jpaTemplate.getJdbcTemplate().queryForObject(caseSql, new Object[]{}, Integer.class);

        return caseTotal;
    }

    /**
     * 查询指定目录下的测试用例数量
     * @param categoryId
     * @return
     */
    public int findTestCaseNumByCategoryId(String categoryId) {
        String caseSql = "Select count(1) as total from autotest_testcase where category_id = '" + categoryId+ "'";
        Integer caseTotal = jpaTemplate.getJdbcTemplate().queryForObject(caseSql, new Object[]{}, Integer.class);

        return caseTotal;
    }


    /**
     * 查询不同用例类型的数量，功能，接口，ui，性能
     * @param workspaceId
     * @return
     */
    public HashMap<String, Integer> findDiffTypeCaseNum(String workspaceId) {
        HashMap<String, Integer> diffTypeCaseNum = new HashMap<>();

        int testCaseNum = findTestCaseNum(workspaceId);
        diffTypeCaseNum.put("all",testCaseNum);

        String functionSql = "Select count(1) as total from autotest_testcase where workspace_id = '" + workspaceId+ "'" + " AND test_type = 'function'";
        Integer functionTotal = jpaTemplate.getJdbcTemplate().queryForObject(functionSql, new Object[]{}, Integer.class);
        diffTypeCaseNum.put("function", functionTotal);

        String apiSql = "Select count(1) as total from autotest_testcase where workspace_id = '" + workspaceId+ "'" + " AND test_type = 'api'";
        Integer apiTotal = jpaTemplate.getJdbcTemplate().queryForObject(apiSql, new Object[]{}, Integer.class);
        diffTypeCaseNum.put("api", apiTotal);

        String uiSql = "Select count(1) as total from autotest_testcase where workspace_id = '" + workspaceId+ "'" + " AND test_type = 'ui'";
        Integer uiTotal = jpaTemplate.getJdbcTemplate().queryForObject(uiSql, new Object[]{}, Integer.class);
        diffTypeCaseNum.put("ui", uiTotal);

        String performSql = "Select count(1) as total from autotest_testcase where workspace_id = '" + workspaceId+ "'" + " AND test_type = 'perform'";
        Integer performTotal = jpaTemplate.getJdbcTemplate().queryForObject(performSql, new Object[]{}, Integer.class);
        diffTypeCaseNum.put("perform", performTotal);

        return diffTypeCaseNum;
    }

    /**
    * 查找所有测试用例
    * @return
    */
    public List<TestCasesEntity> findAllTestCase() {
        return jpaTemplate.findAll(TestCasesEntity.class);
    }

    public List<TestCasesEntity> findTestCaseList(List<String> idList) {
        return jpaTemplate.findList(TestCasesEntity.class,idList);
    }

    public int bigSort(String workspaceId){
        try {
            String sql = "select coalesce(max(sort), 0) from autotest_testcase where workspace_id = ?";
            return jpaTemplate.getJdbcTemplate().queryForObject(sql, new Object[]{workspaceId}, Integer.class);
        } catch (Exception e) {
            logger.error("bigSort error:", e);
            return 0;
        }
    }

    /**
     * 根据查询参数查询测试用例列表
     * @param testCaseQuery
     * @return
     */
    public List<TestCasesEntity> findTestCaseList(TestCaseQuery testCaseQuery) {
        QueryBuilders queryBuilders = QueryBuilders.createQuery(TestCasesEntity.class)
                .eq("workspaceId",testCaseQuery.getWorkspaceId())
                .eq("categoryId", testCaseQuery.getCategoryId())
                .eq("apiId",testCaseQuery.getApiId())
                .eq("testType",testCaseQuery.getTestType())
                .eq("caseType",testCaseQuery.getCaseType())
                .eq("createUser",testCaseQuery.getCreateUser())
                .eq("status",testCaseQuery.getStatus())
                .like("name", testCaseQuery.getName())
                .orders(testCaseQuery.getOrderParams());

        if(ArrayUtils.isNotEmpty(testCaseQuery.getInList())) {
            queryBuilders.in("workspaceId", testCaseQuery.getInList());
        }

        if(ArrayUtils.isNotEmpty(testCaseQuery.getCaseTypeList())) {
            queryBuilders.in("caseType", testCaseQuery.getCaseTypeList());
        }

        if(ArrayUtils.isNotEmpty(testCaseQuery.getNotInList())) {
            queryBuilders.notIn("id", testCaseQuery.getNotInList());
        }

        QueryCondition queryCondition = queryBuilders.get();

        return jpaTemplate.findList(queryCondition, TestCasesEntity.class);
    }

    /**
     * 根据查询参数按分页查询测试用例
     * @param testCaseQuery
     * @return
     */
    public Pagination<TestCasesEntity> findTestCasePage(TestCaseQuery testCaseQuery) {
        QueryBuilders queryBuilders = QueryBuilders.createQuery(TestCasesEntity.class)
                .eq("workspaceId", testCaseQuery.getWorkspaceId())
                .eq("apiId",testCaseQuery.getApiId())
                .eq("testType", testCaseQuery.getTestType())
                .eq("caseType", testCaseQuery.getCaseType())
                .eq("status", testCaseQuery.getStatus())
                .eq("createUser", testCaseQuery.getCreateUser())
                .eq("director", testCaseQuery.getDirector())
                .like("name", testCaseQuery.getName())
                .in("categoryId", testCaseQuery.getCategoryIds())
                .pagination(testCaseQuery.getPageParam())
                .orders(testCaseQuery.getOrderParams());

        String categoryId = testCaseQuery.getCategoryId();
        if (categoryId != null && !"nullstring".equals(categoryId)) {
            // 如果 categoryId 不为 null 且不是 "nullstring"，则按指定 categoryId 查询
            queryBuilders.eq("categoryId", categoryId);
        } else if ("nullstring".equals(categoryId)) {
            // 如果 categoryId 是 "nullstring"，则只查询 category_id 为 null 的记录
            queryBuilders.isNull("categoryId");
        }

        if(ArrayUtils.isNotEmpty(testCaseQuery.getNotInList())) {
            queryBuilders.notIn("id", testCaseQuery.getNotInList());
        }

        if(ArrayUtils.isNotEmpty(testCaseQuery.getInList())) {
            queryBuilders.in("workspaceId", testCaseQuery.getInList());
        }

        if(ArrayUtils.isNotEmpty(testCaseQuery.getCaseTypeList())) {
            queryBuilders.in("caseType", testCaseQuery.getCaseTypeList());
        }

        QueryCondition queryCondition = queryBuilders.get();

        return jpaTemplate.findPage(queryCondition, TestCasesEntity.class);
    }


    public HashMap<String, Integer> findDiffTestCaseNum(TestCaseQuery testCaseQuery) {
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT ")
                .append("COUNT(*) AS total_count, ")
                .append("COUNT(CASE WHEN test_type = 'perform' THEN 1 END) AS perform_count, ")
                .append("COUNT(CASE WHEN test_type = 'api' THEN 1 END) AS api_count, ")
                .append("COUNT(CASE WHEN test_type = 'function' THEN 1 END) AS function_count, ")
                .append("COUNT(CASE WHEN test_type = 'ui' THEN 1 END) AS ui_count ")
                .append("FROM autotest_testcase WHERE 1=1 ");

        List<Object> params = new ArrayList<>();

        if (testCaseQuery.getWorkspaceId() != null) {
            sql.append("AND workspace_id = ? ");
            params.add(testCaseQuery.getWorkspaceId());
        }

        if (testCaseQuery.getCreateUser() != null && !testCaseQuery.getCreateUser().isEmpty()) {
            sql.append("AND create_user = ? ");
            params.add(testCaseQuery.getCreateUser());
        }

        if (testCaseQuery.getDirector() != null && !testCaseQuery.getDirector().isEmpty()) {
            sql.append("AND director = ? ");
            params.add(testCaseQuery.getDirector());
        }

        if (testCaseQuery.getCategoryIds() != null && testCaseQuery.getCategoryIds().length > 0) {
            String placeholders = Arrays.stream(testCaseQuery.getCategoryIds())
                    .map(id -> "?")
                    .collect(Collectors.joining(", "));
            sql.append("AND category_id IN (" + placeholders + ") ");
            Collections.addAll(params, testCaseQuery.getCategoryIds());
        }

        Map<String, Object> result = jpaTemplate.getJdbcTemplate().queryForMap(sql.toString(), params.toArray());

        HashMap<String, Integer> resultMap = new HashMap<>();
        resultMap.put("total", ((Number) result.get("total_count")).intValue());
        resultMap.put("perform", ((Number) result.get("perform_count")).intValue());
        resultMap.put("api", ((Number) result.get("api_count")).intValue());
        resultMap.put("function", ((Number) result.get("function_count")).intValue());
        resultMap.put("ui", ((Number) result.get("ui_count")).intValue());

        return resultMap;
    }


    /**
     * 获取该分类下用例数量
     * @param categoryId
     * @return
     */
    public int countCasesByCategoryId(String categoryId) {
        String sql = "SELECT COUNT(*) FROM autotest_testcase WHERE category_id = '" + categoryId+ "'";
        try {
            return jpaTemplate.getJdbcTemplate().queryForObject(sql, Integer.class);
        }catch (Exception e){
            return 0;
        }
    }

    public Integer countCreateUser(String workspaceId, String createUser) {
        String sql = "SELECT COUNT(*) FROM autotest_testcase WHERE workspace_id = ? AND create_user = ?";
        try {
            return jpaTemplate.getJdbcTemplate().queryForObject(sql, Integer.class, workspaceId, createUser);
        } catch (Exception e) {
            return 0;
        }
    }

    public Integer countDirector(String workspaceId, String director) {
        String sql = "SELECT COUNT(*) FROM autotest_testcase WHERE workspace_id = ? AND director = ?";
        try {
            return jpaTemplate.getJdbcTemplate().queryForObject(sql, Integer.class, workspaceId, director);
        } catch (Exception e) {
            return 0;
        }
    }
}