package com.doublekit.apibox.apitest.dao;

import com.doublekit.apibox.apitest.entity.JsonParamCaseEntity;
import com.doublekit.apibox.apitest.model.JsonParamCaseQuery;
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
public class JsonParamCaseDao{

    private static Logger logger = LoggerFactory.getLogger(JsonParamCaseDao.class);

    @Autowired
    JpaTemplate jpaTemplate;

    /**
     * 创建用户
     * @param jsonParamCaseEntity
     * @return
     */
    public String createJsonParamCase(JsonParamCaseEntity jsonParamCaseEntity) {
        return jpaTemplate.save(jsonParamCaseEntity,String.class);
    }

    /**
     * 更新用户
     * @param jsonParamCaseEntity
     */
    public void updateJsonParamCase(JsonParamCaseEntity jsonParamCaseEntity){
        jpaTemplate.update(jsonParamCaseEntity);
    }

    /**
     * 删除用户
     * @param id
     */
    public void deleteJsonParamCase(String id){
        jpaTemplate.delete(JsonParamCaseEntity.class,id);
    }

    /**
     * 查找用户
     * @param id
     * @return
     */
    public JsonParamCaseEntity findJsonParamCase(String id){
        return jpaTemplate.findOne(JsonParamCaseEntity.class,id);
    }

    /**
    * findAllJsonParamCase
    * @return
    */
    public List<JsonParamCaseEntity> findAllJsonParamCase() {
        return jpaTemplate.findAll(JsonParamCaseEntity.class);
    }

    public List<JsonParamCaseEntity> findJsonParamCaseList(List<String> idList) {
        return jpaTemplate.findList(JsonParamCaseEntity.class,idList);
    }

    public List<JsonParamCaseEntity> findJsonParamCaseList(JsonParamCaseQuery jsonParamCaseQuery) {
        return jpaTemplate.findList(jsonParamCaseQuery, JsonParamCaseEntity.class);
    }

    public Pagination<JsonParamCaseEntity> findJsonParamCasePage(JsonParamCaseQuery jsonParamCaseQuery) {
        return jpaTemplate.findPage(jsonParamCaseQuery, JsonParamCaseEntity.class);
    }
}