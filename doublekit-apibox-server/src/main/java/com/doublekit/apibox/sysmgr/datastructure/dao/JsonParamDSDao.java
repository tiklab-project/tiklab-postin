package com.doublekit.apibox.sysmgr.datastructure.dao;

import com.doublekit.common.Pagination;
import com.doublekit.apibox.sysmgr.datastructure.entity.JsonParamDSEntity;
import com.doublekit.apibox.sysmgr.datastructure.model.JsonParamDSQuery;
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
     * @param jsonParamDSEntity
     * @return
     */
    public String createJsonParamDS(JsonParamDSEntity jsonParamDSEntity) {
        return jpaTemplate.save(jsonParamDSEntity,String.class);
    }

    /**
     * 更新
     * @param jsonParamDSEntity
     */
    public void updateJsonParamDS(JsonParamDSEntity jsonParamDSEntity){
        jpaTemplate.update(jsonParamDSEntity);
    }

    /**
     * 删除
     * @param id
     */
    public void deleteJsonParamDS(String id){
        jpaTemplate.delete(JsonParamDSEntity.class,id);
    }

    public void deleteJsonParamDS(DeleteCondition deleteCondition){
        jpaTemplate.delete(JsonParamDSEntity.class,deleteCondition);
    }

    /**
     * 查找
     * @param id
     * @return
     */
    public JsonParamDSEntity findJsonParamDS(String id){
        return jpaTemplate.findOne(JsonParamDSEntity.class,id);
    }

    /**
    * findAllJsonParamDS
    * @return
    */
    public List<JsonParamDSEntity> findAllJsonParamDS() {
        return jpaTemplate.findAll(JsonParamDSEntity.class);
    }

    public List<JsonParamDSEntity> findJsonParamDSList(List<String> idList) {
        return jpaTemplate.findList(JsonParamDSEntity.class,idList);
    }

    public List<JsonParamDSEntity> findJsonParamDSList(JsonParamDSQuery jsonParamDSQuery) {
        return jpaTemplate.findList(jsonParamDSQuery, JsonParamDSEntity.class);
    }

    public Pagination<JsonParamDSEntity> findJsonParamDSPage(JsonParamDSQuery jsonParamDSQuery) {
        return jpaTemplate.findPage(jsonParamDSQuery, JsonParamDSEntity.class);
    }
}