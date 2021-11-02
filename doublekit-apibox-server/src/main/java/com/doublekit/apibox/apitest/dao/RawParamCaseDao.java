package com.doublekit.apibox.apitest.dao;

import com.doublekit.apibox.apitest.entity.RawParamCaseEntity;
import com.doublekit.apibox.apitest.model.RawParamCaseQuery;
import com.doublekit.common.Pagination;
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
        return jpaTemplate.findList(RawParamCaseEntity.class,rawParamCaseQuery);
    }

    public Pagination<RawParamCaseEntity> findRawParamCasePage(RawParamCaseQuery rawParamCaseQuery) {
        return jpaTemplate.findPage(RawParamCaseEntity.class,rawParamCaseQuery);
    }
}