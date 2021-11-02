package com.doublekit.apibox.datastructure.dao;

import com.doublekit.common.Pagination;
import com.doublekit.apibox.datastructure.entity.EnumParamEntity;
import com.doublekit.apibox.datastructure.model.EnumParamQuery;
import com.doublekit.dal.jpa.JpaTemplate;
import com.doublekit.dal.jpa.builder.deletelist.condition.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * EnumParamDao
 */
@Repository
public class EnumParamDao{

    private static Logger logger = LoggerFactory.getLogger(EnumParamDao.class);

    @Autowired
    JpaTemplate jpaTemplate;

    /**
     * 创建
     * @param enumParamEntity
     * @return
     */
    public String createEnumParam(EnumParamEntity enumParamEntity) {
        return jpaTemplate.save(enumParamEntity,String.class);
    }

    /**
     * 更新
     * @param enumParamEntity
     */
    public void updateEnumParam(EnumParamEntity enumParamEntity){
        jpaTemplate.update(enumParamEntity);
    }

    /**
     * 删除
     * @param id
     */
    public void deleteEnumParam(String id){
        jpaTemplate.delete(EnumParamEntity.class,id);
    }

    public void deleteEnumParam(DeleteCondition deleteCondition){
        jpaTemplate.delete(EnumParamEntity.class,deleteCondition);
    }

    /**
     * 查找
     * @param id
     * @return
     */
    public EnumParamEntity findEnumParam(String id){
        return jpaTemplate.findOne(EnumParamEntity.class,id);
    }

    /**
    * findAllEnumParam
    * @return
    */
    public List<EnumParamEntity> findAllEnumParam() {
        return jpaTemplate.findAll(EnumParamEntity.class);
    }

    public List<EnumParamEntity> findEnumParamList(List<String> idList) {
        return jpaTemplate.findList(EnumParamEntity.class,idList);
    }

    public List<EnumParamEntity> findEnumParamList(EnumParamQuery enumParamQuery) {
        return jpaTemplate.findList(EnumParamEntity.class,enumParamQuery);
    }

    public Pagination<EnumParamEntity> findEnumParamPage(EnumParamQuery enumParamQuery) {
        return jpaTemplate.findPage(EnumParamEntity.class,enumParamQuery);
    }
}