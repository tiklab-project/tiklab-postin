package com.doublekit.apibox.apitest.dao;

import com.doublekit.common.Pagination;
import com.doublekit.apibox.apitest.entity.ResponseInstanceEntity;
import com.doublekit.apibox.apitest.model.ResponseInstanceQuery;
import com.doublekit.dal.jpa.JpaTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 用户数据操作
 */
@Repository
public class ResponseInstanceDao{

    private static Logger logger = LoggerFactory.getLogger(ResponseInstanceDao.class);

    @Autowired
    JpaTemplate jpaTemplate;

    /**
     * 创建用户
     * @param responseInstanceEntity
     * @return
     */
    public String createResponseInstance(ResponseInstanceEntity responseInstanceEntity) {
        return jpaTemplate.save(responseInstanceEntity,String.class);
    }

    /**
     * 更新用户
     * @param responseInstanceEntity
     */
    public void updateResponseInstance(ResponseInstanceEntity responseInstanceEntity){
        jpaTemplate.update(responseInstanceEntity);
    }

    /**
     * 删除用户
     * @param id
     */
    public void deleteResponseInstance(String id){
        jpaTemplate.delete(ResponseInstanceEntity.class,id);
    }

    /**
     * 查找用户
     * @param id
     * @return
     */
    public ResponseInstanceEntity findResponseInstance(String id){
        return jpaTemplate.findOne(ResponseInstanceEntity.class,id);
    }

    /**
    * findAllResponseInstance
    * @return
    */
    public List<ResponseInstanceEntity> findAllResponseInstance() {
        return jpaTemplate.findAll(ResponseInstanceEntity.class);
    }

    public List<ResponseInstanceEntity> findResponseInstanceList(List<String> idList) {
        return jpaTemplate.findList(ResponseInstanceEntity.class,idList);
    }

    public List<ResponseInstanceEntity> findResponseInstanceList(ResponseInstanceQuery responseInstanceQuery) {
        return jpaTemplate.findList(responseInstanceQuery, ResponseInstanceEntity.class);
    }

    public Pagination<ResponseInstanceEntity> findResponseInstancePage(ResponseInstanceQuery responseInstanceQuery) {
        return jpaTemplate.findPage(responseInstanceQuery, ResponseInstanceEntity.class);
    }
}