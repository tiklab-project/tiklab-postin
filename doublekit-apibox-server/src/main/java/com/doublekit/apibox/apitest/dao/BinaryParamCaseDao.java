package com.doublekit.apibox.apitest.dao;

import com.doublekit.apibox.apitest.entity.BinaryParamCaseEntity;
import com.doublekit.apibox.apitest.model.BinaryParamCaseQuery;
import com.doublekit.common.page.Pagination;
import com.doublekit.dal.jpa.JpaTemplate;
import com.doublekit.dal.jpa.criterial.model.DeleteCondition;
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
        return jpaTemplate.findList(binaryParamCaseQuery,BinaryParamCaseEntity.class);
    }

    public Pagination<BinaryParamCaseEntity> findBinaryParamCasePage(BinaryParamCaseQuery binaryParamCaseQuery) {
        return jpaTemplate.findPage(binaryParamCaseQuery,BinaryParamCaseEntity.class);
    }
}