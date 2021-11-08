package com.doublekit.apibox.apitest.dao;

import com.doublekit.common.Pagination;
import com.doublekit.apibox.apitest.entity.PreScriptCaseEntity;
import com.doublekit.apibox.apitest.model.PreScriptCaseQuery;
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
public class PreScriptCaseDao{

    private static Logger logger = LoggerFactory.getLogger(PreScriptCaseDao.class);

    @Autowired
    JpaTemplate jpaTemplate;

    /**
     * 创建用户
     * @param preScriptCaseEntity
     * @return
     */
    public String createPreScriptCase(PreScriptCaseEntity preScriptCaseEntity) {
        return jpaTemplate.save(preScriptCaseEntity,String.class);
    }

    /**
     * 更新用户
     * @param preScriptCaseEntity
     */
    public void updatePreScriptCase(PreScriptCaseEntity preScriptCaseEntity){
        jpaTemplate.update(preScriptCaseEntity);
    }

    /**
     * 删除用户
     * @param id
     */
    public void deletePreScriptCase(String id){
        jpaTemplate.delete(PreScriptCaseEntity.class,id);
    }

    /**
     * 查找用户
     * @param id
     * @return
     */
    public PreScriptCaseEntity findPreScriptCase(String id){
        return jpaTemplate.findOne(PreScriptCaseEntity.class,id);
    }

    /**
    * findAllPreScriptCase
    * @return
    */
    public List<PreScriptCaseEntity> findAllPreScriptCase() {
        return jpaTemplate.findAll(PreScriptCaseEntity.class);
    }

    public List<PreScriptCaseEntity> findPreScriptCaseList(List<String> idList) {
        return jpaTemplate.findList(PreScriptCaseEntity.class,idList);
    }

    public List<PreScriptCaseEntity> findPreScriptCaseList(PreScriptCaseQuery preScriptCaseQuery) {
        return jpaTemplate.findList(preScriptCaseQuery, PreScriptCaseEntity.class);
    }

    public Pagination<PreScriptCaseEntity> findPreScriptCasePage(PreScriptCaseQuery preScriptCaseQuery) {
        return jpaTemplate.findPage(preScriptCaseQuery, PreScriptCaseEntity.class);
    }
}