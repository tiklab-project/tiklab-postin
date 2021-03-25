package com.darthcloud.apibox.apitest.dao;

import com.darthcloud.apibox.apitest.entity.FormParamCasePo;
import com.darthcloud.apibox.apitest.model.FormParamCaseQuery;
import com.darthcloud.common.Pagination;
import com.darthcloud.dal.jpa.JpaTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 用户数据操作
 */
@Repository
public class FormParamCaseDao{

    private static Logger logger = LoggerFactory.getLogger(FormParamCaseDao.class);

    @Autowired
    JpaTemplate jpaTemplate;

    /**
     * 创建用户
     * @param formParamCasePo
     * @return
     */
    public String createFormParamCase(FormParamCasePo formParamCasePo) {
        return jpaTemplate.save(formParamCasePo,String.class);
    }

    /**
     * 更新用户
     * @param formParamCasePo
     */
    public void updateFormParamCase(FormParamCasePo formParamCasePo){
        jpaTemplate.update(formParamCasePo);
    }

    /**
     * 删除用户
     * @param id
     */
    public void deleteFormParamCase(String id){
        jpaTemplate.delete(FormParamCasePo.class,id);
    }

    /**
     * 查找用户
     * @param id
     * @return
     */
    public FormParamCasePo findFormParamCase(String id){
        return jpaTemplate.findOne(FormParamCasePo.class,id);
    }

    /**
    * findAllFormParamCase
    * @return
    */
    public List<FormParamCasePo> findAllFormParamCase() {
        return jpaTemplate.findAll(FormParamCasePo.class);
    }

    public List<FormParamCasePo> findFormParamCaseList(List<String> idList) {
        return jpaTemplate.findList(FormParamCasePo.class,idList);
    }

    public List<FormParamCasePo> findFormParamCaseList(FormParamCaseQuery formParamCaseQuery) {
        return jpaTemplate.findList(FormParamCasePo.class,formParamCaseQuery);
    }

    public Pagination<FormParamCasePo> findFormParamCasePage(FormParamCaseQuery formParamCaseQuery) {
        return jpaTemplate.findPage(FormParamCasePo.class,formParamCaseQuery);
    }
}