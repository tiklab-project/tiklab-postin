package com.tiklab.postlink.apitest.http.httpcase.dao;

import com.tiklab.postlink.apitest.http.httpcase.entity.BinaryParamCaseEntity;
import com.tiklab.postlink.apitest.http.httpcase.model.BinaryParamCaseQuery;
import com.tiklab.core.page.Pagination;
import com.tiklab.dal.jpa.JpaTemplate;
import com.tiklab.dal.jpa.criterial.condition.DeleteCondition;
import com.tiklab.dal.jpa.criterial.condition.QueryCondition;
import com.tiklab.dal.jpa.criterial.conditionbuilder.QueryBuilders;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * BinaryParamCaseDao
 */
@Repository
public class BinaryParamCaseDao{

    private static Logger logger = LoggerFactory.getLogger(BinaryParamCaseDao.class);

    @Autowired
    JpaTemplate jpaTemplate;

    /**
     * 创建
     * @param binaryParamCaseEntity
     * @return
     */
    public String createBinaryParamCase(BinaryParamCaseEntity binaryParamCaseEntity) {
        return jpaTemplate.save(binaryParamCaseEntity,String.class);
    }

    /**
     * 更新
     * @param binaryParamCaseEntity
     */
    public void updateBinaryParamCase(BinaryParamCaseEntity binaryParamCaseEntity){
        jpaTemplate.update(binaryParamCaseEntity);
    }

    /**
     * 删除
     * @param id
     */
    public void deleteBinaryParamCase(String id){
        jpaTemplate.delete(BinaryParamCaseEntity.class,id);
    }

    public void deleteBinaryParamCase(DeleteCondition deleteCondition){
        jpaTemplate.delete(deleteCondition);
    }

    /**
     * 查找
     * @param id
     * @return
     */
    public BinaryParamCaseEntity findBinaryParamCase(String id){
        return jpaTemplate.findOne(BinaryParamCaseEntity.class,id);
    }

    /**
    * findAllBinaryParamCase
    * @return
    */
    public List<BinaryParamCaseEntity> findAllBinaryParamCase() {
        return jpaTemplate.findAll(BinaryParamCaseEntity.class);
    }

    public List<BinaryParamCaseEntity> findBinaryParamCaseList(List<String> idList) {
        return jpaTemplate.findList(BinaryParamCaseEntity.class,idList);
    }

    public List<BinaryParamCaseEntity> findBinaryParamCaseList(BinaryParamCaseQuery binaryParamCaseQuery) {
        QueryCondition queryCondition = QueryBuilders.createQuery(BinaryParamCaseEntity.class)
                .eq("httpCaseId", binaryParamCaseQuery.getHttpCaseId())
                .orders(binaryParamCaseQuery.getOrderParams())
                .get();
        return jpaTemplate.findList(queryCondition,BinaryParamCaseEntity.class);
    }

    public Pagination<BinaryParamCaseEntity> findBinaryParamCasePage(BinaryParamCaseQuery binaryParamCaseQuery) {
        QueryCondition queryCondition = QueryBuilders.createQuery(BinaryParamCaseEntity.class)
                .eq("httpCaseId", binaryParamCaseQuery.getHttpCaseId())
                .pagination(binaryParamCaseQuery.getPageParam())
                .orders(binaryParamCaseQuery.getOrderParams())
                .get();
        return jpaTemplate.findPage(queryCondition,BinaryParamCaseEntity.class);
    }
}