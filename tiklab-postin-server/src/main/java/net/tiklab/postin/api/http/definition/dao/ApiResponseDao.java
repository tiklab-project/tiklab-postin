package net.tiklab.postin.api.http.definition.dao;

import net.tiklab.postin.api.http.definition.entity.ApiResponseEntity;
import net.tiklab.postin.api.http.definition.model.ApiResponseQuery;
import net.tiklab.core.page.Pagination;
import net.tiklab.dal.jpa.JpaTemplate;
import net.tiklab.dal.jpa.criterial.condition.DeleteCondition;
import net.tiklab.dal.jpa.criterial.condition.QueryCondition;
import net.tiklab.dal.jpa.criterial.conditionbuilder.QueryBuilders;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 定义
 * http协议
 * 响应区 数据访问
 */
@Repository
public class ApiResponseDao {

    private static Logger logger = LoggerFactory.getLogger(ApiResponseDao.class);

    @Autowired
    JpaTemplate jpaTemplate;

    /**
     * 创建
     * @param apiResponseEntity
     * @return
     */
    public String createApiResponse(ApiResponseEntity apiResponseEntity) {
        return jpaTemplate.save(apiResponseEntity,String.class);
    }

    /**
     * 更新
     * @param apiResponseEntity
     */
    public void updateApiResponse(ApiResponseEntity apiResponseEntity){
        jpaTemplate.update(apiResponseEntity);
    }

    /**
     * 删除
     * @param id
     */
    public void deleteApiResponse(String id){
        jpaTemplate.delete(ApiResponseEntity.class,id);
    }

    /**
     * 通过条件删除
     * @param deleteCondition
     */
    public void deleteApiResponseList(DeleteCondition deleteCondition){
        jpaTemplate.delete(deleteCondition);
    }


    /**
     * 查找
     * @param id
     * @return
     */
    public ApiResponseEntity findApiResponse(String id){
        return jpaTemplate.findOne(ApiResponseEntity.class,id);
    }

    /**
    * 查找所有
    * @return
    */
    public List<ApiResponseEntity> findAllApiResponse() {
        return jpaTemplate.findAll(ApiResponseEntity.class);
    }

    /**
     * 查找列表
     * @param apiResponseQuery
     * @return
     */
    public List<ApiResponseEntity> findApiResponseList(ApiResponseQuery apiResponseQuery) {
        QueryCondition queryCondition = QueryBuilders.createQuery(ApiResponseEntity.class)
                .eq("httpId", apiResponseQuery.getHttpId())
                .orders(apiResponseQuery.getOrderParams())
                .get();
        return jpaTemplate.findList(queryCondition, ApiResponseEntity.class);
    }

    /**
     * 按分页查找列表
     * @param apiResponseQuery
     * @return
     */
    public Pagination<ApiResponseEntity> findApiResponsePage(ApiResponseQuery apiResponseQuery) {
        QueryCondition queryCondition = QueryBuilders.createQuery(ApiResponseEntity.class)
                .eq("httpId", apiResponseQuery.getHttpId())
                .orders(apiResponseQuery.getOrderParams())
                .get();

        return jpaTemplate.findPage(queryCondition, ApiResponseEntity.class);
    }
}