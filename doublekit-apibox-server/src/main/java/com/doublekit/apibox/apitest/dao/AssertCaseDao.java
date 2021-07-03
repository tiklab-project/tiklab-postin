package com.doublekit.apibox.apitest.dao;

import com.doublekit.apibox.apitest.entity.AssertCasePo;
import com.doublekit.apibox.apitest.model.AssertCaseQuery;
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
public class AssertCaseDao{

    private static Logger logger = LoggerFactory.getLogger(AssertCaseDao.class);

    @Autowired
    JpaTemplate jpaTemplate;

    /**
     * 创建用户
     * @param assertCasePo
     * @return
     */
    public String createAssertCase(AssertCasePo assertCasePo) {
        return jpaTemplate.save(assertCasePo,String.class);
    }

    /**
     * 更新用户
     * @param assertCasePo
     */
    public void updateAssertCase(AssertCasePo assertCasePo){
        jpaTemplate.update(assertCasePo);
    }

    /**
     * 删除用户
     * @param id
     */
    public void deleteAssertCase(String id){
        jpaTemplate.delete(AssertCasePo.class,id);
    }

    /**
     * 查找用户
     * @param id
     * @return
     */
    public AssertCasePo findAssertCase(String id){
        return jpaTemplate.findOne(AssertCasePo.class,id);
    }

    /**
    * findAllAssertCase
    * @return
    */
    public List<AssertCasePo> findAllAssertCase() {
        return jpaTemplate.findAll(AssertCasePo.class);
    }

    public List<AssertCasePo> findAssertCaseList(List<String> idList) {
        return jpaTemplate.findList(AssertCasePo.class,idList);
    }

    public List<AssertCasePo> findAssertCaseList(AssertCaseQuery assertCaseQuery) {
        return jpaTemplate.findList(AssertCasePo.class,assertCaseQuery);
    }

    public Pagination<AssertCasePo> findAssertCasePage(AssertCaseQuery assertCaseQuery) {
        return jpaTemplate.findPage(AssertCasePo.class,assertCaseQuery);
    }
}