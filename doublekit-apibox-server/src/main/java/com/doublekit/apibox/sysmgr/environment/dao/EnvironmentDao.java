package com.doublekit.apibox.sysmgr.environment.dao;

import com.doublekit.common.Pagination;
import com.doublekit.apibox.sysmgr.environment.entity.EnvironmentEntity;
import com.doublekit.apibox.sysmgr.environment.model.EnvironmentQuery;
import com.doublekit.dal.jpa.JpaTemplate;
import com.doublekit.dal.jpa.builder.deletelist.condition.DeleteCondition;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 用户数据操作
 */
@Repository
public class EnvironmentDao{

    private static Logger logger = LoggerFactory.getLogger(EnvironmentDao.class);

    @Autowired
    JpaTemplate jpaTemplate;

    /**
     * 创建用户
     * @param environmentEntity
     * @return
     */
    public String createEnvironment(EnvironmentEntity environmentEntity) {
        return jpaTemplate.save(environmentEntity,String.class);
    }

    /**
     * 更新用户
     * @param environmentEntity
     */
    public void updateEnvironment(EnvironmentEntity environmentEntity){
        jpaTemplate.update(environmentEntity);
    }

    /**
     * 删除用户
     * @param id
     */
    public void deleteEnvironment(String id){
        jpaTemplate.delete(EnvironmentEntity.class,id);
    }

    public void deleteEnvironment(DeleteCondition deleteCondition){
        jpaTemplate.delete(EnvironmentEntity.class,deleteCondition);
    }

    /**
     * 查找用户
     * @param id
     * @return
     */
    public EnvironmentEntity findEnvironment(String id){
        return jpaTemplate.findOne(EnvironmentEntity.class,id);
    }

    /**
    * findAllEnvironment
    * @return
    */
    public List<EnvironmentEntity> findAllEnvironment() {
        return jpaTemplate.findAll(EnvironmentEntity.class);
    }

    public List<EnvironmentEntity> findEnvironmentList(List<String> idList) {
        return jpaTemplate.findList(EnvironmentEntity.class,idList);
    }

    public List<EnvironmentEntity> findEnvironmentList(EnvironmentQuery environmentQuery) {
        return jpaTemplate.findList(EnvironmentEntity.class,environmentQuery);
    }

    public Pagination<EnvironmentEntity> findEnvironmentPage(EnvironmentQuery environmentQuery) {
        return jpaTemplate.findPage(EnvironmentEntity.class,environmentQuery);
    }
}