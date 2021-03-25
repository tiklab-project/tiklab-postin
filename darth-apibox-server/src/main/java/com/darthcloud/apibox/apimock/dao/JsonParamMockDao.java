package com.darthcloud.apibox.apimock.dao;

import com.darthcloud.common.Pagination;
import com.darthcloud.apibox.apimock.entity.JsonParamMockPo;
import com.darthcloud.apibox.apimock.model.JsonParamMockQuery;
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
public class JsonParamMockDao{

    private static Logger logger = LoggerFactory.getLogger(JsonParamMockDao.class);

    @Autowired
    JpaTemplate jpaTemplate;

    /**
     * 创建用户
     * @param jsonParamMockPo
     * @return
     */
    public String createJsonParamMock(JsonParamMockPo jsonParamMockPo) {
        return jpaTemplate.save(jsonParamMockPo,String.class);
    }

    /**
     * 更新用户
     * @param jsonParamMockPo
     */
    public void updateJsonParamMock(JsonParamMockPo jsonParamMockPo){
        jpaTemplate.update(jsonParamMockPo);
    }

    /**
     * 删除用户
     * @param id
     */
    public void deleteJsonParamMock(String id){
        jpaTemplate.delete(JsonParamMockPo.class,id);
    }

    /**
     * 查找用户
     * @param id
     * @return
     */
    public JsonParamMockPo findJsonParamMock(String id){
        return jpaTemplate.findOne(JsonParamMockPo.class,id);
    }

    /**
    * findAllJsonParamMock
    * @return
    */
    public List<JsonParamMockPo> findAllJsonParamMock() {
        return jpaTemplate.findAll(JsonParamMockPo.class);
    }

    public List<JsonParamMockPo> findJsonParamMockList(List<String> idList) {
        return jpaTemplate.findList(JsonParamMockPo.class,idList);
    }

    public List<JsonParamMockPo> findJsonParamMockList(JsonParamMockQuery jsonParamMockQuery) {
        return jpaTemplate.findList(JsonParamMockPo.class,jsonParamMockQuery);
    }

    public Pagination<JsonParamMockPo> findJsonParamMockPage(JsonParamMockQuery jsonParamMockQuery) {
        return jpaTemplate.findPage(JsonParamMockPo.class,jsonParamMockQuery);
    }
}