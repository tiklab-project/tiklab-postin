package com.doublekit.apibox.apitest.dao;

import com.doublekit.apibox.apitest.entity.FormUrlencodedCaseEntity;
import com.doublekit.apibox.apitest.model.FormUrlencodedCaseQuery;
import com.doublekit.common.page.Pagination;
import com.doublekit.dal.jpa.JpaTemplate;
import com.doublekit.dal.jpa.criterial.model.DeleteCondition;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * FormUrlencodedCaseDao
 */
@Repository
public class FormUrlencodedCaseDao{

    private static Logger logger = LoggerFactory.getLogger(FormUrlencodedCaseDao.class);

    @Autowired
    JpaTemplate jpaTemplate;

    /**
     * 创建
     * @param formUrlencodedCaseEntity
     * @return
     */
    public String createFormUrlencodedCase(FormUrlencodedCaseEntity formUrlencodedCaseEntity) {
        return jpaTemplate.save(formUrlencodedCaseEntity,String.class);
    }

    /**
     * 更新
     * @param formUrlencodedCaseEntity
     */
    public void updateFormUrlencodedCase(FormUrlencodedCaseEntity formUrlencodedCaseEntity){
        jpaTemplate.update(formUrlencodedCaseEntity);
    }

    /**
     * 删除
     * @param id
     */
    public void deleteFormUrlencodedCase(String id){
        jpaTemplate.delete(FormUrlencodedCaseEntity.class,id);
    }

    public void deleteFormUrlencodedCase(DeleteCondition deleteCondition){
        jpaTemplate.delete(deleteCondition);
    }

    /**
     * 查找
     * @param id
     * @return
     */
    public FormUrlencodedCaseEntity findFormUrlencodedCase(String id){
        return jpaTemplate.findOne(FormUrlencodedCaseEntity.class,id);
    }

    /**
    * findAllFormUrlencodedCase
    * @return
    */
    public List<FormUrlencodedCaseEntity> findAllFormUrlencodedCase() {
        return jpaTemplate.findAll(FormUrlencodedCaseEntity.class);
    }

    public List<FormUrlencodedCaseEntity> findFormUrlencodedCaseList(List<String> idList) {
        return jpaTemplate.findList(FormUrlencodedCaseEntity.class,idList);
    }

    public List<FormUrlencodedCaseEntity> findFormUrlencodedCaseList(FormUrlencodedCaseQuery formUrlencodedCaseQuery) {
        return jpaTemplate.findList(formUrlencodedCaseQuery,FormUrlencodedCaseEntity.class);
    }

    public Pagination<FormUrlencodedCaseEntity> findFormUrlencodedCasePage(FormUrlencodedCaseQuery formUrlencodedCaseQuery) {
        return jpaTemplate.findPage(formUrlencodedCaseQuery,FormUrlencodedCaseEntity.class);
    }
}