package com.darthcloud.apibox.jsonparam.dao;

import com.darthcloud.apibox.jsonparam.entity.JsonParamPo;
import com.darthcloud.apibox.jsonparam.model.JsonParamQuery;
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
public class JsonParamDao{

    private static Logger logger = LoggerFactory.getLogger(JsonParamDao.class);

    @Autowired
    JpaTemplate jpaTemplate;

    /**
     * 创建用户
     * @param jsonParamPo
     * @return
     */
    public String createJsonParam(JsonParamPo jsonParamPo) {
        return jpaTemplate.save(jsonParamPo,String.class);
    }

    /**
     * 更新用户
     * @param jsonParamPo
     */
    public void updateJsonParam(JsonParamPo jsonParamPo){
        jpaTemplate.update(jsonParamPo);
    }

    /**
     * 删除用户
     * @param id
     */
    public void deleteJsonParam(String id){
        jpaTemplate.delete(JsonParamPo.class,id);
    }

    /**
     * 查找用户
     * @param id
     * @return
     */
    public JsonParamPo findJsonParam(String id){
        return jpaTemplate.findOne(JsonParamPo.class,id);
    }

    /**
    * findAllJsonParam
    * @return
    */
    public List<JsonParamPo> findAllJsonParam() {
        return jpaTemplate.findAll(JsonParamPo.class);
    }

    public List<JsonParamPo> findJsonParamList(JsonParamQuery jsonParamQuery) {
        return jpaTemplate.createCriteria(JsonParamPo.class).params(jsonParamQuery).list();
    }

    public Pagination<List<JsonParamPo>> findJsonParamPage(JsonParamQuery jsonParamQuery) {
        return jpaTemplate.createCriteria(JsonParamPo.class).params(jsonParamQuery).page();
    }
}