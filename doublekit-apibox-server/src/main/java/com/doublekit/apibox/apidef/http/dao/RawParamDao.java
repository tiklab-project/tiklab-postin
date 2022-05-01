package com.doublekit.apibox.apidef.http.dao;

import com.doublekit.core.page.Pagination;
import com.doublekit.apibox.apidef.http.entity.RawParamEntity;
import com.doublekit.apibox.apidef.http.model.RawParamQuery;
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
        jpaTemplate.delete(deleteCondition);
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
        QueryCondition queryCondition = QueryBuilders.createQuery(RawParamEntity.class)
                .eq("httpId", rawParamQuery.getHttpId())
                .orders(rawParamQuery.getOrderParams())
                .get();
        return jpaTemplate.findList(queryCondition, RawParamEntity.class);
    }

    public Pagination<RawParamEntity> findRawParamPage(RawParamQuery rawParamQuery) {
        QueryCondition queryCondition = QueryBuilders.createQuery(RawParamEntity.class)
                .eq("httpId", rawParamQuery.getHttpId())
                .pagination(rawParamQuery.getPageParam())
                .orders(rawParamQuery.getOrderParams())
                .get();
        return jpaTemplate.findPage(queryCondition, RawParamEntity.class);
    }


}