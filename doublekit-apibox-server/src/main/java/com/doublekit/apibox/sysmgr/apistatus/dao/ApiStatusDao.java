package com.doublekit.apibox.sysmgr.apistatus.dao;

import com.doublekit.apibox.sysmgr.apistatus.entity.ApiStatusEntity;
import com.doublekit.apibox.sysmgr.apistatus.model.ApiStatusQuery;
import com.doublekit.core.page.Pagination;
import com.doublekit.dal.jpa.JpaTemplate;
import com.doublekit.dal.jpa.criterial.condition.DeleteCondition;
import com.doublekit.dal.jpa.criterial.condition.QueryCondition;
import com.doublekit.dal.jpa.criterial.conditionbuilder.QueryBuilders;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * ApiStatusDao
 */
@Repository
public class ApiStatusDao{

    private static Logger logger = LoggerFactory.getLogger(ApiStatusDao.class);

    @Autowired
    JpaTemplate jpaTemplate;

    /**
     * 创建
     * @param apiStatusEntity
     * @return
     */
    public String createApiStatus(ApiStatusEntity apiStatusEntity) {
        return jpaTemplate.save(apiStatusEntity,String.class);
    }

    /**
     * 更新
     * @param apiStatusEntity
     */
    public void updateApiStatus(ApiStatusEntity apiStatusEntity){
        jpaTemplate.update(apiStatusEntity);
    }

    /**
     * 删除
     * @param id
     */
    public void deleteApiStatus(String id){
        jpaTemplate.delete(ApiStatusEntity.class,id);
    }

    public void deleteApiStatus(DeleteCondition deleteCondition){
        jpaTemplate.delete(deleteCondition);
    }

    /**
     * 查找
     * @param id
     * @return
     */
    public ApiStatusEntity findApiStatus(String id){
        return jpaTemplate.findOne(ApiStatusEntity.class,id);
    }

    /**
    * findAllApiStatus
    * @return
    */
    public List<ApiStatusEntity> findAllApiStatus() {
        return jpaTemplate.findAll(ApiStatusEntity.class);
    }

    public List<ApiStatusEntity> findApiStatusList(List<String> idList) {
        return jpaTemplate.findList(ApiStatusEntity.class,idList);
    }

    public List<ApiStatusEntity> findApiStatusList(ApiStatusQuery apiStatusQuery) {
        QueryCondition queryCondition = QueryBuilders.createQuery(ApiStatusEntity.class)
                .orders(apiStatusQuery.getOrderParams())
                .get();
        return jpaTemplate.findList(queryCondition,ApiStatusEntity.class);
    }

    public Pagination<ApiStatusEntity> findApiStatusPage(ApiStatusQuery apiStatusQuery) {
        QueryCondition queryCondition = QueryBuilders.createQuery(ApiStatusEntity.class)
                .orders(apiStatusQuery.getOrderParams())
                .pagination(apiStatusQuery.getPageParam())
                .get();
        return jpaTemplate.findPage(queryCondition,ApiStatusEntity.class);
    }
}