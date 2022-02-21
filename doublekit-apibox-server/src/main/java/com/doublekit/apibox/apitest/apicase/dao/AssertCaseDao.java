package com.doublekit.apibox.apitest.apicase.dao;

import com.doublekit.apibox.apitest.apicase.entity.AssertCaseEntity;
import com.doublekit.apibox.apitest.apicase.model.AssertCaseQuery;
import com.doublekit.common.page.Pagination;
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
public class AssertCaseDao{

    private static Logger logger = LoggerFactory.getLogger(AssertCaseDao.class);

    @Autowired
    JpaTemplate jpaTemplate;

    /**
     * 创建用户
     * @param assertCaseEntity
     * @return
     */
    public String createAssertCase(AssertCaseEntity assertCaseEntity) {
        return jpaTemplate.save(assertCaseEntity,String.class);
    }

    /**
     * 更新用户
     * @param assertCaseEntity
     */
    public void updateAssertCase(AssertCaseEntity assertCaseEntity){
        jpaTemplate.update(assertCaseEntity);
    }

    /**
     * 删除用户
     * @param id
     */
    public void deleteAssertCase(String id){
        jpaTemplate.delete(AssertCaseEntity.class,id);
    }

    /**
     * 查找用户
     * @param id
     * @return
     */
    public AssertCaseEntity findAssertCase(String id){
        return jpaTemplate.findOne(AssertCaseEntity.class,id);
    }

    /**
    * findAllAssertCase
    * @return
    */
    public List<AssertCaseEntity> findAllAssertCase() {
        return jpaTemplate.findAll(AssertCaseEntity.class);
    }

    public List<AssertCaseEntity> findAssertCaseList(List<String> idList) {
        return jpaTemplate.findList(AssertCaseEntity.class,idList);
    }

    public List<AssertCaseEntity> findAssertCaseList(AssertCaseQuery assertCaseQuery) {
        return jpaTemplate.findList(assertCaseQuery, AssertCaseEntity.class);
    }

    public Pagination<AssertCaseEntity> findAssertCasePage(AssertCaseQuery assertCaseQuery) {
        return jpaTemplate.findPage(assertCaseQuery, AssertCaseEntity.class);
    }
}