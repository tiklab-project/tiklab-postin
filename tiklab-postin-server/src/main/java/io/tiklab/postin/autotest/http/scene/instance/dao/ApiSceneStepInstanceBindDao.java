package io.tiklab.postin.autotest.http.scene.instance.dao;

import io.tiklab.postin.autotest.http.scene.instance.entity.ApiSceneStepInstanceBindEntity;
import io.tiklab.postin.autotest.http.scene.instance.model.ApiSceneStepInstanceBindQuery;
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
 * 接口场景下步骤的公共实例 数据访问
 */
@Repository
public class ApiSceneStepInstanceBindDao{

    private static Logger logger = LoggerFactory.getLogger(ApiSceneStepInstanceBindDao.class);

    @Autowired
    JpaTemplate jpaTemplate;

    /**
     * 创建接口场景下步骤的公共实例
     * @param apiSceneStepInstanceBindEntity
     * @return
     */
    public String createApiSceneStepInstanceBind(ApiSceneStepInstanceBindEntity apiSceneStepInstanceBindEntity) {
        return jpaTemplate.save(apiSceneStepInstanceBindEntity,String.class);
    }

    /**
     * 更新接口场景下步骤的公共实例
     * @param apiSceneStepInstanceBindEntity
     */
    public void updateApiSceneStepInstanceBind(ApiSceneStepInstanceBindEntity apiSceneStepInstanceBindEntity){
        jpaTemplate.update(apiSceneStepInstanceBindEntity);
    }

    /**
     * 删除接口场景下步骤的公共实例
     * @param id
     */
    public void deleteApiSceneStepInstanceBind(String id){
        jpaTemplate.delete(ApiSceneStepInstanceBindEntity.class,id);
    }

    public void deleteApiSceneStepInstanceBind(DeleteCondition deleteCondition){
        jpaTemplate.delete(deleteCondition);
    }

    /**
     * 查找接口场景下步骤的公共实例
     * @param id
     * @return
     */
    public ApiSceneStepInstanceBindEntity findApiSceneStepInstanceBind(String id){
        return jpaTemplate.findOne(ApiSceneStepInstanceBindEntity.class,id);
    }

    /**
    * 查找所有接口场景下步骤的公共实例
    * @return
    */
    public List<ApiSceneStepInstanceBindEntity> findAllApiSceneStepInstanceBind() {
        return jpaTemplate.findAll(ApiSceneStepInstanceBindEntity.class);
    }

    public List<ApiSceneStepInstanceBindEntity> findApiSceneStepInstanceBindList(List<String> idList) {
        return jpaTemplate.findList(ApiSceneStepInstanceBindEntity.class,idList);
    }

    /**
     * 查询接口场景下步骤的实例公共实例
     * @param apiSceneStepInstanceBindQuery
     * @return
     */
    public List<ApiSceneStepInstanceBindEntity> findApiSceneStepInstanceBindList(ApiSceneStepInstanceBindQuery apiSceneStepInstanceBindQuery) {
        QueryCondition queryCondition = QueryBuilders.createQuery(ApiSceneStepInstanceBindEntity.class)
                .eq("apiSceneInstanceId",apiSceneStepInstanceBindQuery.getApiSceneInstanceId())
                .orders(apiSceneStepInstanceBindQuery.getOrderParams())
                .get();
        return jpaTemplate.findList(queryCondition,ApiSceneStepInstanceBindEntity.class);
    }

    /**
     * 按分页查询接口场景下步骤的公共实例
     * @param apiSceneStepInstanceBindQuery
     * @return
     */
    public Pagination<ApiSceneStepInstanceBindEntity> findApiSceneStepInstanceBindPage(ApiSceneStepInstanceBindQuery apiSceneStepInstanceBindQuery) {
        QueryCondition queryCondition = QueryBuilders.createQuery(ApiSceneStepInstanceBindEntity.class)
                .eq("apiSceneInstanceId",apiSceneStepInstanceBindQuery.getApiSceneInstanceId())
                .pagination(apiSceneStepInstanceBindQuery.getPageParam())
                .orders(apiSceneStepInstanceBindQuery.getOrderParams())
                .get();
        return jpaTemplate.findPage(queryCondition,ApiSceneStepInstanceBindEntity.class);
    }
}