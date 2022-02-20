package com.doublekit.apibox.sysmgr.apistatus.dao;

import com.doublekit.apibox.sysmgr.apistatus.entity.ApiStatusEntity;
import com.doublekit.apibox.sysmgr.apistatus.model.ApiStatusQuery;
import com.doublekit.common.page.Pagination;
import com.doublekit.dal.jpa.JpaTemplate;
import com.doublekit.dal.jpa.criterial.condition.DeleteCondition;
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
        return jpaTemplate.findList(apiStatusQuery,ApiStatusEntity.class);
    }

    public Pagination<ApiStatusEntity> findApiStatusPage(ApiStatusQuery apiStatusQuery) {
        return jpaTemplate.findPage(apiStatusQuery,ApiStatusEntity.class);
    }
}