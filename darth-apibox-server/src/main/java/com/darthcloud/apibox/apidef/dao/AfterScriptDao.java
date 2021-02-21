package com.darthcloud.apibox.apidef.dao;

import com.darthcloud.common.Pagination;
import com.darthcloud.apibox.apidef.entity.AfterScriptPo;
import com.darthcloud.apibox.apidef.model.AfterScriptQuery;
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
        return jpaTemplate.createCriteria(AfterScriptPo.class).params(afterScriptQuery).list();
    }

    public Pagination<List<AfterScriptPo>> findAfterScriptPage(AfterScriptQuery afterScriptQuery) { 
        return jpaTemplate.createCriteria(AfterScriptPo.class).params(afterScriptQuery).page();
    }
}