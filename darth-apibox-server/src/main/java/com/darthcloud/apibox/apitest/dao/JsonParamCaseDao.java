package com.darthcloud.apibox.apitest.dao;

import com.darthcloud.apibox.apitest.entity.JsonParamCasePo;
import com.darthcloud.apibox.apitest.model.JsonParamCaseQuery;
import com.darthcloud.common.Pagination;
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
public class JsonParamCaseDao{

    private static Logger logger = LoggerFactory.getLogger(JsonParamCaseDao.class);

    @Autowired
    JpaTemplate jpaTemplate;

    /**
     * 创建用户
     * @param jsonParamCasePo
     * @return
     */
    public String createJsonParamCase(JsonParamCasePo jsonParamCasePo) {
        return jpaTemplate.save(jsonParamCasePo,String.class);
    }

    /**
     * 更新用户
     * @param jsonParamCasePo
     */
    public void updateJsonParamCase(JsonParamCasePo jsonParamCasePo){
        jpaTemplate.update(jsonParamCasePo);
    }

    /**
     * 删除用户
     * @param id
     */
    public void deleteJsonParamCase(String id){
        jpaTemplate.delete(JsonParamCasePo.class,id);
    }

    /**
     * 查找用户
     * @param id
     * @return
     */
    public JsonParamCasePo findJsonParamCase(String id){
        return jpaTemplate.findOne(JsonParamCasePo.class,id);
    }

    /**
    * findAllJsonParamCase
    * @return
    */
    public List<JsonParamCasePo> findAllJsonParamCase() {
        return jpaTemplate.findAll(JsonParamCasePo.class);
    }

    public List<JsonParamCasePo> findJsonParamCaseList(List<String> idList) {
        return jpaTemplate.findList(JsonParamCasePo.class,idList);
    }

    public List<JsonParamCasePo> findJsonParamCaseList(JsonParamCaseQuery jsonParamCaseQuery) {
        return jpaTemplate.findList(JsonParamCasePo.class,jsonParamCaseQuery);
    }

    public Pagination<List<JsonParamCasePo>> findJsonParamCasePage(JsonParamCaseQuery jsonParamCaseQuery) { 
        return jpaTemplate.findPage(JsonParamCasePo.class,jsonParamCaseQuery);
    }
}