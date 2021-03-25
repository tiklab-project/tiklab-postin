package com.darthcloud.apibox.apitest.dao;

import com.darthcloud.common.Pagination;
import com.darthcloud.apibox.apitest.entity.PreScriptCasePo;
import com.darthcloud.apibox.apitest.model.PreScriptCaseQuery;
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
public class PreScriptCaseDao{

    private static Logger logger = LoggerFactory.getLogger(PreScriptCaseDao.class);

    @Autowired
    JpaTemplate jpaTemplate;

    /**
     * 创建用户
     * @param preScriptCasePo
     * @return
     */
    public String createPreScriptCase(PreScriptCasePo preScriptCasePo) {
        return jpaTemplate.save(preScriptCasePo,String.class);
    }

    /**
     * 更新用户
     * @param preScriptCasePo
     */
    public void updatePreScriptCase(PreScriptCasePo preScriptCasePo){
        jpaTemplate.update(preScriptCasePo);
    }

    /**
     * 删除用户
     * @param id
     */
    public void deletePreScriptCase(String id){
        jpaTemplate.delete(PreScriptCasePo.class,id);
    }

    /**
     * 查找用户
     * @param id
     * @return
     */
    public PreScriptCasePo findPreScriptCase(String id){
        return jpaTemplate.findOne(PreScriptCasePo.class,id);
    }

    /**
    * findAllPreScriptCase
    * @return
    */
    public List<PreScriptCasePo> findAllPreScriptCase() {
        return jpaTemplate.findAll(PreScriptCasePo.class);
    }

    public List<PreScriptCasePo> findPreScriptCaseList(List<String> idList) {
        return jpaTemplate.findList(PreScriptCasePo.class,idList);
    }

    public List<PreScriptCasePo> findPreScriptCaseList(PreScriptCaseQuery preScriptCaseQuery) {
        return jpaTemplate.findList(PreScriptCasePo.class,preScriptCaseQuery);
    }

    public Pagination<PreScriptCasePo> findPreScriptCasePage(PreScriptCaseQuery preScriptCaseQuery) {
        return jpaTemplate.findPage(PreScriptCasePo.class,preScriptCaseQuery);
    }
}