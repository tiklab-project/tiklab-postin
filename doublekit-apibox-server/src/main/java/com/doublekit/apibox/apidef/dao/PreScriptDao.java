package com.doublekit.apibox.apidef.dao;

import com.doublekit.apibox.apidef.entity.JsonResponsePo;
import com.doublekit.common.Pagination;
import com.doublekit.apibox.apidef.entity.PreScriptPo;
import com.doublekit.apibox.apidef.model.PreScriptQuery;
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
public class PreScriptDao{

    private static Logger logger = LoggerFactory.getLogger(PreScriptDao.class);

    @Autowired
    JpaTemplate jpaTemplate;

    /**
     * 创建用户
     * @param preScriptPo
     * @return
     */
    public String createPreScript(PreScriptPo preScriptPo) {
        return jpaTemplate.save(preScriptPo,String.class);
    }

    /**
     * 更新用户
     * @param preScriptPo
     */
    public void updatePreScript(PreScriptPo preScriptPo){
        jpaTemplate.update(preScriptPo);
    }

    /**
     * 删除用户
     * @param id
     */
    public void deletePreScript(String id){
        jpaTemplate.delete(PreScriptPo.class,id);
    }

    /**
     * 通过条件删除
     * @param deleteCondition
     */
    public void deletePreScriptList(DeleteCondition deleteCondition){
        jpaTemplate.delete(PreScriptPo.class,deleteCondition);
    }


    /**
     * 查找用户
     * @param id
     * @return
     */
    public PreScriptPo findPreScript(String id){
        return jpaTemplate.findOne(PreScriptPo.class,id);
    }

    /**
    * findAllPreScript
    * @return
    */
    public List<PreScriptPo> findAllPreScript() {
        return jpaTemplate.findAll(PreScriptPo.class);
    }

    public List<PreScriptPo> findPreScriptList(PreScriptQuery preScriptQuery) {
        return jpaTemplate.findList(PreScriptPo.class,preScriptQuery);
    }

    public Pagination<PreScriptPo> findPreScriptPage(PreScriptQuery preScriptQuery) {
        return jpaTemplate.findPage(PreScriptPo.class,preScriptQuery);
    }
}