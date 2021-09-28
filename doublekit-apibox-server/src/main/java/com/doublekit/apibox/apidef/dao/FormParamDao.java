package com.doublekit.apibox.apidef.dao;

import com.doublekit.common.Pagination;
import com.doublekit.apibox.apidef.entity.FormParamPo;
import com.doublekit.apibox.apidef.model.FormParamQuery;
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
public class FormParamDao{

    private static Logger logger = LoggerFactory.getLogger(FormParamDao.class);

    @Autowired
    JpaTemplate jpaTemplate;

    /**
     * 创建用户
     * @param formParamPo
     * @return
     */
    public String createFormParam(FormParamPo formParamPo) {
        return jpaTemplate.save(formParamPo,String.class);
    }

    /**
     * 更新用户
     * @param formParamPo
     */
    public void updateFormParam(FormParamPo formParamPo){
        jpaTemplate.update(formParamPo);
    }

    /**
     * 删除用户
     * @param id
     */
    public void deleteFormParam(String id){
        jpaTemplate.delete(FormParamPo.class,id);
    }

    /**
     * 通过条件删除
     * @param deleteCondition
     */
    public void deleteFormParamLsit(DeleteCondition deleteCondition){
        jpaTemplate.delete(FormParamPo.class,deleteCondition);
    }

    /**
     * 查找用户
     * @param id
     * @return
     */
    public FormParamPo findFormParam(String id){
        return jpaTemplate.findOne(FormParamPo.class,id);
    }

    /**
    * findAllFormParam
    * @return
    */
    public List<FormParamPo> findAllFormParam() {
        return jpaTemplate.findAll(FormParamPo.class);
    }

    public List<FormParamPo> findFormParamList(FormParamQuery formParamQuery) {
        return jpaTemplate.findList(FormParamPo.class,formParamQuery);
    }

    public Pagination<FormParamPo> findFormParamPage(FormParamQuery formParamQuery) {
        return jpaTemplate.findPage(FormParamPo.class,formParamQuery);
    }
}