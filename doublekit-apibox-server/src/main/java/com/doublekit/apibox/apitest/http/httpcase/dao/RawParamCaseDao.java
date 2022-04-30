package com.doublekit.apibox.apitest.http.httpcase.dao;

import com.doublekit.apibox.apitest.http.httpcase.entity.RawParamCaseEntity;
import com.doublekit.apibox.apitest.http.httpcase.model.RawParamCaseQuery;
import com.doublekit.common.page.Pagination;
import com.doublekit.dal.jpa.JpaTemplate;
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
public class RawParamCaseDao{

    private static Logger logger = LoggerFactory.getLogger(RawParamCaseDao.class);

    @Autowired
    JpaTemplate jpaTemplate;

    /**
     * 创建用户
     * @param rawParamCaseEntity
     * @return
     */
    public String createRawParamCase(RawParamCaseEntity rawParamCaseEntity) {
        return jpaTemplate.save(rawParamCaseEntity,String.class);
    }

    /**
     * 更新用户
     * @param rawParamCaseEntity
     */
    public void updateRawParamCase(RawParamCaseEntity rawParamCaseEntity){
        jpaTemplate.update(rawParamCaseEntity);
    }

    /**
     * 删除用户
     * @param id
     */
    public void deleteRawParamCase(String id){
        jpaTemplate.delete(RawParamCaseEntity.class,id);
    }

    /**
     * 查找用户
     * @param id
     * @return
     */
    public RawParamCaseEntity findRawParamCase(String id){
        return jpaTemplate.findOne(RawParamCaseEntity.class,id);
    }

    /**
    * findAllRawParamCase
    * @return
    */
    public List<RawParamCaseEntity> findAllRawParamCase() {
        return jpaTemplate.findAll(RawParamCaseEntity.class);
    }

    public List<RawParamCaseEntity> findRawParamCaseList(List<String> idList) {
        return jpaTemplate.findList(RawParamCaseEntity.class,idList);
    }

    public List<RawParamCaseEntity> findRawParamCaseList(RawParamCaseQuery rawParamCaseQuery) {
        QueryCondition queryCondition = QueryBuilders.createQuery(RawParamCaseEntity.class)
                .eq("httpCaseId", rawParamCaseQuery.getHttpCaseId())
                .orders(rawParamCaseQuery.getOrderParams())
                .get();
        return jpaTemplate.findList(queryCondition, RawParamCaseEntity.class);
    }

    public Pagination<RawParamCaseEntity> findRawParamCasePage(RawParamCaseQuery rawParamCaseQuery) {
        QueryCondition queryCondition = QueryBuilders.createQuery(RawParamCaseEntity.class)
                .eq("httpCaseId", rawParamCaseQuery.getHttpCaseId())
                .pagination(rawParamCaseQuery.getPageParam())
                .orders(rawParamCaseQuery.getOrderParams())
                .get();
        return jpaTemplate.findPage(queryCondition, RawParamCaseEntity.class);
    }
}