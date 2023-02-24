package net.tiklab.postin.apidef.http.definition.dao;

import net.tiklab.core.page.Pagination;
import net.tiklab.dal.jpa.JpaTemplate;
import net.tiklab.dal.jpa.criterial.condition.DeleteCondition;
import net.tiklab.dal.jpa.criterial.condition.QueryCondition;
import net.tiklab.dal.jpa.criterial.conditionbuilder.QueryBuilders;
import net.tiklab.postin.apidef.http.definition.entity.ApiRequestEntity;
import net.tiklab.postin.apidef.http.definition.model.ApiRequestQuery;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * ApiRequestDao
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
    * findAllApiRequest
    * @return
    */
    public List<ApiRequestEntity> findAllApiRequest() {
        return jpaTemplate.findAll(ApiRequestEntity.class);
    }

    public List<ApiRequestEntity> findApiRequestList(List<String> idList) {
        return jpaTemplate.findList(ApiRequestEntity.class,idList);
    }

    public List<ApiRequestEntity> findApiRequestList(ApiRequestQuery apiRequestQuery) {
        QueryCondition queryCondition = QueryBuilders.createQuery(ApiRequestEntity.class)
                .eq("httpId", apiRequestQuery.getHttpId())
                .orders(apiRequestQuery.getOrderParams())
                .get();
        return jpaTemplate.findList(queryCondition,ApiRequestEntity.class);
    }

    public Pagination<ApiRequestEntity> findApiRequestPage(ApiRequestQuery apiRequestQuery) {
        QueryCondition queryCondition = QueryBuilders.createQuery(ApiRequestEntity.class)
                .eq("httpId", apiRequestQuery.getHttpId())
                .orders(apiRequestQuery.getOrderParams())
                .pagination(apiRequestQuery.getPageParam())
                .get();
        return jpaTemplate.findPage(queryCondition,ApiRequestEntity.class);
    }
}