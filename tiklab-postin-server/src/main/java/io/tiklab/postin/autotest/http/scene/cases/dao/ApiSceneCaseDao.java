package io.tiklab.postin.autotest.http.scene.cases.dao;

import io.tiklab.postin.autotest.http.scene.cases.entity.ApiSceneCaseEntity;
import io.tiklab.postin.autotest.http.scene.cases.model.ApiSceneCaseQuery;
import io.tiklab.core.page.Pagination;
import io.tiklab.dal.jpa.criterial.condition.DeleteCondition;
import io.tiklab.dal.jpa.criterial.condition.QueryCondition;
import io.tiklab.dal.jpa.criterial.conditionbuilder.QueryBuilders;

import io.tiklab.dal.jpa.JpaTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 接口场景 数据访问
 */
@Repository
public class ApiSceneCaseDao {

    private static Logger logger = LoggerFactory.getLogger(ApiSceneCaseDao.class);

    @Autowired
    JpaTemplate jpaTemplate;

    /**
     * 创建接口场景
     * @param apiSceneCaseEntity
     * @return
     */
    public String createApiSceneCase(ApiSceneCaseEntity apiSceneCaseEntity) {
        return jpaTemplate.save(apiSceneCaseEntity,String.class);
    }

    /**
     * 更新接口场景
     * @param apiSceneCaseEntity
     */
    public void updateApiSceneCase(ApiSceneCaseEntity apiSceneCaseEntity){
        jpaTemplate.update(apiSceneCaseEntity);
    }

    /**
     * 删除接口场景
     * @param id
     */
    public void deleteApiSceneCase(String id){
        jpaTemplate.delete(ApiSceneCaseEntity.class,id);
    }

    public void deleteApiSceneCase(DeleteCondition deleteCondition){
        jpaTemplate.delete(deleteCondition);
    }

    /**
     * 根据id查找接口场景
     * @param id
     * @return
     */
    public ApiSceneCaseEntity findApiSceneCase(String id){
        return jpaTemplate.findOne(ApiSceneCaseEntity.class,id);
    }

    /**
    * 查找所有接口场景
    * @return
    */
    public List<ApiSceneCaseEntity> findAllApiSceneCase() {
        return jpaTemplate.findAll(ApiSceneCaseEntity.class);
    }

    public List<ApiSceneCaseEntity> findApiSceneCaseList(List<String> idList) {
        return jpaTemplate.findList(ApiSceneCaseEntity.class,idList);
    }

    /**
     * 根据查询参数查询接口场景列表
     * @param apiSceneCaseQuery
     * @return
     */
    public List<ApiSceneCaseEntity> findApiSceneCaseList(ApiSceneCaseQuery apiSceneCaseQuery) {
        QueryCondition queryCondition = QueryBuilders.createQuery(ApiSceneCaseEntity.class)
                .eq("testCaseId",apiSceneCaseQuery.getTestCaseId())
                .orders(apiSceneCaseQuery.getOrderParams())
                .get();
        return jpaTemplate.findList(queryCondition, ApiSceneCaseEntity.class);
    }

    /**
     * 根据查询参数按分页查询接口场景列表
     * @param apiSceneCaseQuery
     * @return
     */
    public Pagination<ApiSceneCaseEntity> findApiSceneCasePage(ApiSceneCaseQuery apiSceneCaseQuery) {
        QueryCondition queryCondition = QueryBuilders.createQuery(ApiSceneCaseEntity.class)
                .eq("testCaseId",apiSceneCaseQuery.getTestCaseId())
                .pagination(apiSceneCaseQuery.getPageParam())
                .orders(apiSceneCaseQuery.getOrderParams())
                .get();
        return jpaTemplate.findPage(queryCondition, ApiSceneCaseEntity.class);
    }


}