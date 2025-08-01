package io.tiklab.postin.autotest.http.perf.cases.dao;

import io.tiklab.postin.autotest.http.perf.cases.entity.ApiPerfStepEntity;
import io.tiklab.dal.jpa.criterial.condition.QueryCondition;
import io.tiklab.dal.jpa.criterial.conditionbuilder.QueryBuilders;
import io.tiklab.core.page.Pagination;
import io.tiklab.dal.jpa.JpaTemplate;
import io.tiklab.dal.jpa.criterial.condition.DeleteCondition;
import io.tiklab.postin.autotest.http.perf.cases.model.ApiPerfStepQuery;
import io.tiklab.postin.autotest.http.perf.cases.service.ApiPerfTestDataService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 接口性能下场景步骤 数据访问
 */
@Repository
public class ApiPerfStepDao {

    private static Logger logger = LoggerFactory.getLogger(ApiPerfStepDao.class);

    @Autowired
    JpaTemplate jpaTemplate;

    @Autowired
    ApiPerfTestDataService apiPerfTestDataService;
    /**
     * 创建接口性能场景步骤
     * @param apiPerfStepEntity
     * @return
     */
    public String createApiPerfStep(ApiPerfStepEntity apiPerfStepEntity) {
        return jpaTemplate.save(apiPerfStepEntity,String.class);
    }

    /**
     * 更新接口性能场景步骤
     * @param apiPerfStepEntity
     */
    public void updateApiPerfStep(ApiPerfStepEntity apiPerfStepEntity){
        jpaTemplate.update(apiPerfStepEntity);
    }

    /**
     * 删除接口性能场景步骤
     * @param id
     */
    public void deleteApiPerfStep(String id){
        jpaTemplate.delete(ApiPerfStepEntity.class,id);

        apiPerfTestDataService.deleteAllApiPerfTestData(id);
    }

    public void deleteApiPerfStep(DeleteCondition deleteCondition){
        jpaTemplate.delete(deleteCondition);
    }

    /**
     * 查找接口性能场景步骤
     * @param id
     * @return
     */
    public ApiPerfStepEntity findApiPerfStep(String id){
        return jpaTemplate.findOne(ApiPerfStepEntity.class,id);
    }


    public int findStepNum(String caseId) {
        String stepSql = "Select count(1) as total from autotest_api_perf_step where api_perf_id ='" + caseId+ "'";
        Integer stepNum = jpaTemplate.getJdbcTemplate().queryForObject(stepSql, new Object[]{}, Integer.class);

        return stepNum;
    }

    public Integer isApiSceneExist(String caseId) {
        String sql = " Select count(1) as total from autotest_api_scene_step where api_scene_id = '" + caseId + "'";
        Integer modelTotal = jpaTemplate.getJdbcTemplate().queryForObject(sql, new Object[]{}, Integer.class);

        return modelTotal;
    }


    /**
    * 查找所有接口性能场景步骤
    * @return
    */
    public List<ApiPerfStepEntity> findAllApiPerfStep() {
        return jpaTemplate.findAll(ApiPerfStepEntity.class);
    }

    public List<ApiPerfStepEntity> findApiPerfStepList(List<String> idList) {
        return jpaTemplate.findList(ApiPerfStepEntity.class,idList);
    }

    /**
     * 查询列表接口性能场景步骤
     * @param apiPerfStepQuery
     * @return
     */
    public List<ApiPerfStepEntity> findApiPerfStepList(ApiPerfStepQuery apiPerfStepQuery) {
        QueryCondition queryCondition = QueryBuilders.createQuery(ApiPerfStepEntity.class)
                .eq("apiPerfId", apiPerfStepQuery.getApiPerfId())
                .orders(apiPerfStepQuery.getOrderParams())
                .get();
        return jpaTemplate.findList(queryCondition, ApiPerfStepEntity.class);
    }

    /**
     * 按分页查询接口性能场景步骤
     * @param apiPerfStepQuery
     * @return
     */
    public Pagination<ApiPerfStepEntity> findApiPerfStepPage(ApiPerfStepQuery apiPerfStepQuery) {
        QueryCondition queryCondition = QueryBuilders.createQuery(ApiPerfStepEntity.class)
                .eq("apiPerfId", apiPerfStepQuery.getApiPerfId())
                .pagination(apiPerfStepQuery.getPageParam())
                .orders(apiPerfStepQuery.getOrderParams())
                .get();
        return jpaTemplate.findPage(queryCondition, ApiPerfStepEntity.class);
    }


}