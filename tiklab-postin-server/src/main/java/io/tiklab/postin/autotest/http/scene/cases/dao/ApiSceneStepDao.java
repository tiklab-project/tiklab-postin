package io.tiklab.postin.autotest.http.scene.cases.dao;

import io.tiklab.core.page.Pagination;
import io.tiklab.dal.jpa.JpaTemplate;
import io.tiklab.dal.jpa.criterial.condition.DeleteCondition;
import io.tiklab.dal.jpa.criterial.condition.QueryCondition;
import io.tiklab.dal.jpa.criterial.conditionbuilder.QueryBuilders;
import io.tiklab.postin.autotest.http.scene.cases.entity.ApiSceneStepEntity;
import io.tiklab.postin.autotest.http.scene.cases.entity.ApiSceneStepWillBindCaseEntity;
import io.tiklab.postin.autotest.http.scene.cases.model.ApiSceneStepQuery;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 接口场景下步骤 数据访问
 */
@Repository
public class ApiSceneStepDao{

    private static Logger logger = LoggerFactory.getLogger(ApiSceneStepDao.class);

    @Autowired
    JpaTemplate jpaTemplate;

    /**
     * 创建步骤
     * @param apiSceneStepEntity
     * @return
     */
    public String createApiSceneStep(ApiSceneStepEntity apiSceneStepEntity) {
        return jpaTemplate.save(apiSceneStepEntity,String.class);
    }


    /**
     * 更新步骤
     * @param apiSceneStepEntity
     */
    public void updateApiSceneStep(ApiSceneStepEntity apiSceneStepEntity){
        jpaTemplate.update(apiSceneStepEntity);
    }

    /**
     * 删除步骤
     * @param id
     */
    public void deleteApiSceneStep(String id){
        jpaTemplate.delete(ApiSceneStepEntity.class,id);
    }

    public void deleteApiSceneStep(DeleteCondition deleteCondition){
        jpaTemplate.delete(deleteCondition);
    }

    /**
     * 根据id查找步骤
     * @param id
     * @return
     */
    public ApiSceneStepEntity findApiSceneStep(String id){
        return jpaTemplate.findOne(ApiSceneStepEntity.class,id);
    }


    /**
    * 查找所有步骤
    * @return
    */
    public List<ApiSceneStepEntity> findAllApiSceneStep() {
        return jpaTemplate.findAll(ApiSceneStepEntity.class);
    }

    public List<ApiSceneStepEntity> findApiSceneStepList(List<String> idList) {
        return jpaTemplate.findList(ApiSceneStepEntity.class,idList);
    }

    /**
     * 根据查询参数查找查询步骤列表
     * @param apiSceneStepQuery
     * @return
     */
    public List<ApiSceneStepEntity> findApiSceneStepList(ApiSceneStepQuery apiSceneStepQuery) {
        QueryCondition queryCondition = QueryBuilders.createQuery(ApiSceneStepEntity.class)
                .eq("apiSceneId",apiSceneStepQuery.getApiSceneId())
                .orders(apiSceneStepQuery.getOrderParams())
                .get();
        return jpaTemplate.findList(queryCondition,ApiSceneStepEntity.class);
    }

    /**
     * 根据查询参数查找按分页查询步骤
     * @param apiSceneStepQuery
     * @return
     */
    public Pagination<ApiSceneStepEntity> findApiSceneStepPage(ApiSceneStepQuery apiSceneStepQuery) {
        QueryCondition queryCondition = QueryBuilders.createQuery(ApiSceneStepEntity.class)
                .eq("apiSceneId",apiSceneStepQuery.getApiSceneId())
                .orders(apiSceneStepQuery.getOrderParams())
                .pagination(apiSceneStepQuery.getPageParam())
                .get();
        return jpaTemplate.findPage(queryCondition,ApiSceneStepEntity.class);
    }

    /**
     * 查询未关联的用例
     * @param apiSceneStepQuery
     * @return
     */
    public Pagination<ApiSceneStepWillBindCaseEntity> findApiSceneStepWillBindCasePage(ApiSceneStepQuery apiSceneStepQuery) {
        StringBuilder modelSqlBuilder = new StringBuilder();
        modelSqlBuilder.append("SELECT autotest_api_unit.id, autotest_api_unit.path, autotest_api_unit.method_type ,autotest_testcase.name,autotest_testcase.create_user,autotest_testcase.case_type ")
                .append(" FROM autotest_api_unit ")
                .append(" JOIN autotest_testcase on autotest_api_unit.testcase_id = autotest_testcase.id ")
                .append(" WHERE autotest_testcase.workspace_id = '").append(apiSceneStepQuery.getWorkspaceId()).append("'");

        if (apiSceneStepQuery.getName() != null) {
            modelSqlBuilder.append(" And autotest_testcase.name LIKE '%").append(apiSceneStepQuery.getName()).append("%'");
        }

        modelSqlBuilder .append(" AND NOT EXISTS (  ")
                .append(" SELECT 1 FROM autotest_api_scene_step  ")
                .append(" JOIN autotest_api_scene ON autotest_api_scene.id = autotest_api_scene_step.api_scene_id ")
                .append(" WHERE autotest_api_scene_step.api_unit_id = autotest_testcase.id  ")
                .append(" AND autotest_api_scene.id =  '").append(apiSceneStepQuery.getApiSceneId()).append("')");

        String modelSql = modelSqlBuilder.toString();

        Pagination<ApiSceneStepWillBindCaseEntity> page = jpaTemplate.getJdbcTemplate().findPage(modelSql, new Object[]{}, apiSceneStepQuery.getPageParam(), new BeanPropertyRowMapper<>(ApiSceneStepWillBindCaseEntity.class));
        return page;
    }


    public Integer isApiUnitExist(String apiUnitId) {
        String sql = "Select count(1) as total from autotest_api_scene_step where api_unit_id = '" + apiUnitId + "'";
        Integer modelTotal = jpaTemplate.getJdbcTemplate().queryForObject(sql, new Object[]{}, Integer.class);

        return modelTotal;
    }


}