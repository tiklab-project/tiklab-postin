package com.doublekit.apibox.apidef.dao;

import com.doublekit.apibox.apidef.entity.AfterScriptPo;
import com.doublekit.apibox.apidef.entity.ResponseResultPo;
import com.doublekit.apibox.apidef.model.AfterScriptQuery;
import com.doublekit.common.Pagination;
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
public class AfterScriptDao{

    private static Logger logger = LoggerFactory.getLogger(AfterScriptDao.class);

    @Autowired
    JpaTemplate jpaTemplate;

    /**
     * 创建用户
     * @param afterScriptPo
     * @return
     */
    public String createAfterScript(AfterScriptPo afterScriptPo) {
        return jpaTemplate.save(afterScriptPo,String.class);
    }

    /**
     * 更新用户
     * @param afterScriptPo
     */
    public void updateAfterScript(AfterScriptPo afterScriptPo){
        jpaTemplate.update(afterScriptPo);
    }

    /**
     * 删除用户
     * @param id
     */
    public void deleteAfterScript(String id){
        jpaTemplate.delete(AfterScriptPo.class,id);
    }

    /**
     * 通过条件删除
     * @param deleteCondition
     */
    public void deleteAfterScriptList(DeleteCondition deleteCondition){
        jpaTemplate.delete(AfterScriptPo.class,deleteCondition);
    }
    /**
     * 查找用户
     * @param id
     * @return
     */
    public AfterScriptPo findAfterScript(String id){
        return jpaTemplate.findOne(AfterScriptPo.class,id);
    }

    /**
    * findAllAfterScript
    * @return
    */
    public List<AfterScriptPo> findAllAfterScript() {
        return jpaTemplate.findAll(AfterScriptPo.class);
    }

    public List<AfterScriptPo> findAfterScriptList(AfterScriptQuery afterScriptQuery) {
        return jpaTemplate.findList(AfterScriptPo.class,afterScriptQuery);
    }

    public Pagination<AfterScriptPo> findAfterScriptPage(AfterScriptQuery afterScriptQuery) {
        return jpaTemplate.findPage(AfterScriptPo.class,afterScriptQuery);
    }
}