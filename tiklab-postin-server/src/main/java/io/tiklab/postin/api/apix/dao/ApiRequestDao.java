package io.tiklab.postin.api.apix.dao;

import io.tiklab.core.page.Pagination;
import io.tiklab.dal.jpa.JpaTemplate;
import io.tiklab.dal.jpa.criterial.condition.DeleteCondition;
import io.tiklab.dal.jpa.criterial.condition.QueryCondition;
import io.tiklab.dal.jpa.criterial.conditionbuilder.QueryBuilders;

import io.tiklab.postin.api.apix.entity.ApiRequestEntity;
import io.tiklab.postin.api.apix.model.ApiRequestQuery;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 定义
 * http协议
 * 请求区 数据访问
 */
@Repository
public class ApiRequestDao{

    private static Logger logger = LoggerFactory.getLogger(ApiRequestDao.class);

    @Autowired
    JpaTemplate jpaTemplate;

    /**
     * 创建
     * @param apiRequestEntity
     * @return
     */
    public String createApiRequest(ApiRequestEntity apiRequestEntity) {
        return jpaTemplate.save(apiRequestEntity,String.class);
    }

    /**
     * 更新
     * @param apiRequestEntity
     */
    public void updateApiRequest(ApiRequestEntity apiRequestEntity){
        jpaTemplate.update(apiRequestEntity);
    }

    /**
     * 删除
     * @param id
     */
    public void deleteApiRequest(String id){
        jpaTemplate.delete(ApiRequestEntity.class,id);
    }

    public void deleteApiRequest(DeleteCondition deleteCondition){
        jpaTemplate.delete(deleteCondition);
    }

    /**
     * 查找
     * @param id
     * @return
     */
    public ApiRequestEntity findApiRequest(String id){
        return jpaTemplate.findOne(ApiRequestEntity.class,id);
    }

    /**
    * 查找所有
    * @return
    */
    public List<ApiRequestEntity> findAllApiRequest() {
        return jpaTemplate.findAll(ApiRequestEntity.class);
    }

    public List<ApiRequestEntity> findApiRequestList(List<String> idList) {
        return jpaTemplate.findList(ApiRequestEntity.class,idList);
    }

    /**
     * 查找列表
     * @param apiRequestQuery
     * @return
     */
    public List<ApiRequestEntity> findApiRequestList(ApiRequestQuery apiRequestQuery) {
        QueryCondition queryCondition = QueryBuilders.createQuery(ApiRequestEntity.class)
                .eq("apiId", apiRequestQuery.getApiId())
                .orders(apiRequestQuery.getOrderParams())
                .get();
        return jpaTemplate.findList(queryCondition,ApiRequestEntity.class);
    }

    /**
     * 按分页查找列表
     * @param apiRequestQuery
     * @return
     */
    public Pagination<ApiRequestEntity> findApiRequestPage(ApiRequestQuery apiRequestQuery) {
        QueryCondition queryCondition = QueryBuilders.createQuery(ApiRequestEntity.class)
                .eq("apiId", apiRequestQuery.getApiId())
                .orders(apiRequestQuery.getOrderParams())
                .pagination(apiRequestQuery.getPageParam())
                .get();
        return jpaTemplate.findPage(queryCondition,ApiRequestEntity.class);
    }
}