package com.darthcloud.apibox.sysmgr.dao;

import com.darthcloud.common.Pagination;
import com.darthcloud.apibox.sysmgr.entity.EnvironmentPo;
import com.darthcloud.apibox.sysmgr.model.EnvironmentQuery;
import com.darthcloud.dal.jpa.JpaTemplate;
import com.darthcloud.dal.jpa.builder.deletelist.condition.*;
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
     * @param environmentPo
     * @return
     */
    public String createEnvironment(EnvironmentPo environmentPo) {
        return jpaTemplate.save(environmentPo,String.class);
    }

    /**
     * 更新用户
     * @param environmentPo
     */
    public void updateEnvironment(EnvironmentPo environmentPo){
        jpaTemplate.update(environmentPo);
    }

    /**
     * 删除用户
     * @param id
     */
    public void deleteEnvironment(String id){
        jpaTemplate.delete(EnvironmentPo.class,id);
    }

    public void deleteEnvironment(DeleteCondition deleteCondition){
        jpaTemplate.delete(EnvironmentPo.class,deleteCondition);
    }

    /**
     * 查找用户
     * @param id
     * @return
     */
    public EnvironmentPo findEnvironment(String id){
        return jpaTemplate.findOne(EnvironmentPo.class,id);
    }

    /**
    * findAllEnvironment
    * @return
    */
    public List<EnvironmentPo> findAllEnvironment() {
        return jpaTemplate.findAll(EnvironmentPo.class);
    }

    public List<EnvironmentPo> findEnvironmentList(List<String> idList) {
        return jpaTemplate.findList(EnvironmentPo.class,idList);
    }

    public List<EnvironmentPo> findEnvironmentList(EnvironmentQuery environmentQuery) {
        return jpaTemplate.findList(EnvironmentPo.class,environmentQuery);
    }

    public Pagination<List<EnvironmentPo>> findEnvironmentPage(EnvironmentQuery environmentQuery) { 
        return jpaTemplate.findPage(EnvironmentPo.class,environmentQuery);
    }
}