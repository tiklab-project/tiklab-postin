package com.doublekit.apibox.datastructure.dao;

import com.doublekit.common.Pagination;
import com.doublekit.apibox.datastructure.entity.EnumParamPo;
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
     * @param enumParamPo
     * @return
     */
    public String createEnumParam(EnumParamPo enumParamPo) {
        return jpaTemplate.save(enumParamPo,String.class);
    }

    /**
     * 更新
     * @param enumParamPo
     */
    public void updateEnumParam(EnumParamPo enumParamPo){
        jpaTemplate.update(enumParamPo);
    }

    /**
     * 删除
     * @param id
     */
    public void deleteEnumParam(String id){
        jpaTemplate.delete(EnumParamPo.class,id);
    }

    public void deleteEnumParam(DeleteCondition deleteCondition){
        jpaTemplate.delete(EnumParamPo.class,deleteCondition);
    }

    /**
     * 查找
     * @param id
     * @return
     */
    public EnumParamPo findEnumParam(String id){
        return jpaTemplate.findOne(EnumParamPo.class,id);
    }

    /**
    * findAllEnumParam
    * @return
    */
    public List<EnumParamPo> findAllEnumParam() {
        return jpaTemplate.findAll(EnumParamPo.class);
    }

    public List<EnumParamPo> findEnumParamList(List<String> idList) {
        return jpaTemplate.findList(EnumParamPo.class,idList);
    }

    public List<EnumParamPo> findEnumParamList(EnumParamQuery enumParamQuery) {
        return jpaTemplate.findList(EnumParamPo.class,enumParamQuery);
    }

    public Pagination<EnumParamPo> findEnumParamPage(EnumParamQuery enumParamQuery) {
        return jpaTemplate.findPage(EnumParamPo.class,enumParamQuery);
    }
}