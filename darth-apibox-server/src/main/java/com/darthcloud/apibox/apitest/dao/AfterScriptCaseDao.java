package com.darthcloud.apibox.apitest.dao;

import com.darthcloud.common.Pagination;
import com.darthcloud.apibox.apitest.entity.AfterScriptCasePo;
import com.darthcloud.apibox.apitest.model.AfterScriptCaseQuery;
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
public class AfterScriptCaseDao{

    private static Logger logger = LoggerFactory.getLogger(AfterScriptCaseDao.class);

    @Autowired
    JpaTemplate jpaTemplate;

    /**
     * 创建用户
     * @param afterScriptCasePo
     * @return
     */
    public String createAfterScriptCase(AfterScriptCasePo afterScriptCasePo) {
        return jpaTemplate.save(afterScriptCasePo,String.class);
    }

    /**
     * 更新用户
     * @param afterScriptCasePo
     */
    public void updateAfterScriptCase(AfterScriptCasePo afterScriptCasePo){
        jpaTemplate.update(afterScriptCasePo);
    }

    /**
     * 删除用户
     * @param id
     */
    public void deleteAfterScriptCase(String id){
        jpaTemplate.delete(AfterScriptCasePo.class,id);
    }

    /**
     * 查找用户
     * @param id
     * @return
     */
    public AfterScriptCasePo findAfterScriptCase(String id){
        return jpaTemplate.findOne(AfterScriptCasePo.class,id);
    }

    /**
    * findAllAfterScriptCase
    * @return
    */
    public List<AfterScriptCasePo> findAllAfterScriptCase() {
        return jpaTemplate.findAll(AfterScriptCasePo.class);
    }

    public List<AfterScriptCasePo> findAfterScriptCaseList(List<String> idList) {
        return jpaTemplate.findList(AfterScriptCasePo.class,idList);
    }

    public List<AfterScriptCasePo> findAfterScriptCaseList(AfterScriptCaseQuery afterScriptCaseQuery) {
        return jpaTemplate.createCriteria(AfterScriptCasePo.class).params(afterScriptCaseQuery).list();
    }

    public Pagination<List<AfterScriptCasePo>> findAfterScriptCasePage(AfterScriptCaseQuery afterScriptCaseQuery) { 
        return jpaTemplate.createCriteria(AfterScriptCasePo.class).params(afterScriptCaseQuery).page();
    }
}