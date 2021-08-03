package com.doublekit.apibox.datastructure.dao;

import com.doublekit.common.Pagination;
import com.doublekit.apibox.datastructure.entity.JsonParamDSPo;
import com.doublekit.apibox.datastructure.model.JsonParamDSQuery;
import com.doublekit.dal.jpa.JpaTemplate;
import com.doublekit.dal.jpa.builder.deletelist.condition.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * JsonParamDSDao
 */
@Repository
public class JsonParamDSDao{

    private static Logger logger = LoggerFactory.getLogger(JsonParamDSDao.class);

    @Autowired
    JpaTemplate jpaTemplate;

    /**
     * 创建
     * @param jsonParamDSPo
     * @return
     */
    public String createJsonParamDS(JsonParamDSPo jsonParamDSPo) {
        return jpaTemplate.save(jsonParamDSPo,String.class);
    }

    /**
     * 更新
     * @param jsonParamDSPo
     */
    public void updateJsonParamDS(JsonParamDSPo jsonParamDSPo){
        jpaTemplate.update(jsonParamDSPo);
    }

    /**
     * 删除
     * @param id
     */
    public void deleteJsonParamDS(String id){
        jpaTemplate.delete(JsonParamDSPo.class,id);
    }

    public void deleteJsonParamDS(DeleteCondition deleteCondition){
        jpaTemplate.delete(JsonParamDSPo.class,deleteCondition);
    }

    /**
     * 查找
     * @param id
     * @return
     */
    public JsonParamDSPo findJsonParamDS(String id){
        return jpaTemplate.findOne(JsonParamDSPo.class,id);
    }

    /**
    * findAllJsonParamDS
    * @return
    */
    public List<JsonParamDSPo> findAllJsonParamDS() {
        return jpaTemplate.findAll(JsonParamDSPo.class);
    }

    public List<JsonParamDSPo> findJsonParamDSList(List<String> idList) {
        return jpaTemplate.findList(JsonParamDSPo.class,idList);
    }

    public List<JsonParamDSPo> findJsonParamDSList(JsonParamDSQuery jsonParamDSQuery) {
        return jpaTemplate.findList(JsonParamDSPo.class,jsonParamDSQuery);
    }

    public Pagination<JsonParamDSPo> findJsonParamDSPage(JsonParamDSQuery jsonParamDSQuery) {
        return jpaTemplate.findPage(JsonParamDSPo.class,jsonParamDSQuery);
    }
}