package io.tiklab.postin.autotest.http.scene.instance.dao;

import io.tiklab.core.page.Pagination;
import io.tiklab.dal.jpa.JpaTemplate;
import io.tiklab.dal.jpa.criterial.condition.DeleteCondition;
import io.tiklab.dal.jpa.criterial.condition.QueryCondition;
import io.tiklab.dal.jpa.criterial.conditionbuilder.QueryBuilders;
import io.tiklab.postin.autotest.http.scene.instance.entity.ApiSceneInstanceEntity;
import io.tiklab.postin.autotest.http.scene.instance.model.ApiSceneInstanceQuery;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 接口场景历史实例 数据访问
 */
@Repository
public class ApiSceneInstanceDao {

    private static Logger logger = LoggerFactory.getLogger(ApiSceneInstanceDao.class);

    @Autowired
    JpaTemplate jpaTemplate;

    /**
     * 创建接口场景历史实例
     * @param testInstanceEntity
     * @return
     */
    public String createApiSceneInstance(ApiSceneInstanceEntity testInstanceEntity) {
        return jpaTemplate.save(testInstanceEntity,String.class);
    }

    /**
     * 更新
     * @param testInstanceEntity
     */
    public void updateApiSceneInstance(ApiSceneInstanceEntity testInstanceEntity){
        jpaTemplate.update(testInstanceEntity);
    }

    /**
     * 删除接口场景历史实例
     * @param id
     */
    public void deleteApiSceneInstance(String id){
        jpaTemplate.delete(ApiSceneInstanceEntity.class,id);
    }

    public void deleteApiSceneInstance(DeleteCondition deleteCondition){
        jpaTemplate.delete(deleteCondition);
    }

    /**
     * 通过id查找接口场景历史实例
     * @param id
     * @return
     */
    public ApiSceneInstanceEntity findApiSceneInstance(String id){
        return jpaTemplate.findOne(ApiSceneInstanceEntity.class,id);
    }

    /**
    * 查找所有接口场景历史实例
    * @return
    */
    public List<ApiSceneInstanceEntity> findAllApiSceneInstance() {
        return jpaTemplate.findAll(ApiSceneInstanceEntity.class);
    }

    public List<ApiSceneInstanceEntity> findApiSceneInstanceList(List<String> idList) {
        return jpaTemplate.findList(ApiSceneInstanceEntity.class,idList);
    }

    /**
     * 根据查询参数查询接口场景历史实例列表
     * @param apiSceneInstanceQuery
     * @return
     */
    public List<ApiSceneInstanceEntity> findApiSceneInstanceList(ApiSceneInstanceQuery apiSceneInstanceQuery) {
        QueryCondition queryCondition = QueryBuilders.createQuery(ApiSceneInstanceEntity.class)
                .eq("apiSceneId", apiSceneInstanceQuery.getApiSceneId())
                .orders(apiSceneInstanceQuery.getOrderParams())
                .get();
        return jpaTemplate.findList(queryCondition, ApiSceneInstanceEntity.class);
    }

    /**
     * 根据查询按分页查询接口场景历史实例
     * @param apiSceneInstanceQuery
     * @return
     */
    public Pagination<ApiSceneInstanceEntity> findApiSceneInstancePage(ApiSceneInstanceQuery apiSceneInstanceQuery) {
        QueryCondition queryCondition = QueryBuilders.createQuery(ApiSceneInstanceEntity.class)
                .eq("apiSceneId", apiSceneInstanceQuery.getApiSceneId())
                .orders(apiSceneInstanceQuery.getOrderParams())
                .pagination(apiSceneInstanceQuery.getPageParam())
                .get();
        return jpaTemplate.findPage(queryCondition, ApiSceneInstanceEntity.class);
    }
}