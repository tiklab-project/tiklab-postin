package com.doublekit.apibox.apidef.dao;

import com.doublekit.apibox.apidef.entity.BinaryParamEntity;
import com.doublekit.apibox.apidef.model.BinaryParamQuery;
import com.doublekit.common.page.Pagination;
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
 * BinaryParamDao
 */
@Repository
public class BinaryParamDao{

    private static Logger logger = LoggerFactory.getLogger(BinaryParamDao.class);

    @Autowired
    JpaTemplate jpaTemplate;

    /**
     * 创建
     * @param binaryParamEntity
     * @return
     */
    public String createBinaryParam(BinaryParamEntity binaryParamEntity) {
        return jpaTemplate.save(binaryParamEntity,String.class);
    }

    /**
     * 更新
     * @param binaryParamEntity
     */
    public void updateBinaryParam(BinaryParamEntity binaryParamEntity){
        jpaTemplate.update(binaryParamEntity);
    }

    /**
     * 删除
     * @param id
     */
    public void deleteBinaryParam(String id){
        jpaTemplate.delete(BinaryParamEntity.class,id);
    }

    public void deleteBinaryParam(DeleteCondition deleteCondition){
        jpaTemplate.delete(deleteCondition);
    }

    /**
     * 查找
     * @param id
     * @return
     */
    public BinaryParamEntity findBinaryParam(String id){
        return jpaTemplate.findOne(BinaryParamEntity.class,id);
    }

    /**
    * findAllBinaryParam
    * @return
    */
    public List<BinaryParamEntity> findAllBinaryParam() {
        return jpaTemplate.findAll(BinaryParamEntity.class);
    }

    public List<BinaryParamEntity> findBinaryParamList(List<String> idList) {
        return jpaTemplate.findList(BinaryParamEntity.class,idList);
    }

    public List<BinaryParamEntity> findBinaryParamList(BinaryParamQuery binaryParamQuery) {
        QueryCondition queryCondition = QueryBuilders.createQuery(BinaryParamEntity.class)
                .eq("methodId",binaryParamQuery.getMethodId())
                .orders(binaryParamQuery.getOrderParams())
                .get();
        return jpaTemplate.findList(queryCondition,BinaryParamEntity.class);
    }

    public Pagination<BinaryParamEntity> findBinaryParamPage(BinaryParamQuery binaryParamQuery) {
        QueryCondition queryCondition = QueryBuilders.createQuery(BinaryParamEntity.class)
                .eq("methodId",binaryParamQuery.getMethodId())
                .pagination(binaryParamQuery.getPageParam())
                .orders(binaryParamQuery.getOrderParams())
                .get();
        return jpaTemplate.findPage(queryCondition,BinaryParamEntity.class);
    }
}