package com.doublekit.apibox.apidef.dao;

import com.doublekit.common.Pagination;
import com.doublekit.apibox.apidef.entity.RawParamEntity;
import com.doublekit.apibox.apidef.model.RawParamQuery;
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
public class RawParamDao{

    private static Logger logger = LoggerFactory.getLogger(RawParamDao.class);

    @Autowired
    JpaTemplate jpaTemplate;

    /**
     * 创建用户
     * @param rawParamEntity
     * @return
     */
    public String createRawParam(RawParamEntity rawParamEntity) {
        return jpaTemplate.save(rawParamEntity,String.class);
    }

    /**
     * 更新用户
     * @param rawParamEntity
     */
    public void updateRawParam(RawParamEntity rawParamEntity){
        jpaTemplate.update(rawParamEntity);
    }

    /**
     * 删除用户
     * @param id
     */
    public void deleteRawParam(String id){
        jpaTemplate.delete(RawParamEntity.class,id);
    }

    /**
     * 通过条件删除
     * @param deleteCondition
     */
    public void deleteRawParamlist(DeleteCondition deleteCondition){
        jpaTemplate.delete(RawParamEntity.class,deleteCondition);
    }

    /**
     * 查找用户
     * @param id
     * @return
     */
    public RawParamEntity findRawParam(String id){
        return jpaTemplate.findOne(RawParamEntity.class,id);
    }

    /**
    * findAllRawParam
    * @return
    */
    public List<RawParamEntity> findAllRawParam() {
        return jpaTemplate.findAll(RawParamEntity.class);
    }

    public List<RawParamEntity> findRawParamList(RawParamQuery rawParamQuery) {
        return jpaTemplate.findList(rawParamQuery, RawParamEntity.class);
    }

    public Pagination<RawParamEntity> findRawParamPage(RawParamQuery rawParamQuery) {
        return jpaTemplate.findPage(rawParamQuery, RawParamEntity.class);
    }
}