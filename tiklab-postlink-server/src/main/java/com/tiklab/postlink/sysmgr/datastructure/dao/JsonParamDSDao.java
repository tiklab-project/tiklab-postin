package com.tiklab.postlink.sysmgr.datastructure.dao;

import com.tiklab.core.page.Pagination;
import com.tiklab.postlink.sysmgr.datastructure.entity.JsonParamDSEntity;
import com.tiklab.postlink.sysmgr.datastructure.model.JsonParamDSQuery;
import com.tiklab.dal.jpa.JpaTemplate;
import com.tiklab.dal.jpa.criterial.condition.DeleteCondition;
import com.tiklab.dal.jpa.criterial.condition.QueryCondition;
import com.tiklab.dal.jpa.criterial.conditionbuilder.QueryBuilders;
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
        jpaTemplate.delete(deleteCondition);
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
        QueryCondition queryCondition = QueryBuilders.createQuery(JsonParamDSEntity.class)
                .eq("dataStructureId", jsonParamDSQuery.getDataStructureId())
                .orders(jsonParamDSQuery.getOrderParams())
                .get();
        return jpaTemplate.findList(queryCondition, JsonParamDSEntity.class);
    }

    public Pagination<JsonParamDSEntity> findJsonParamDSPage(JsonParamDSQuery jsonParamDSQuery) {
        QueryCondition queryCondition = QueryBuilders.createQuery(JsonParamDSEntity.class)
                .eq("dataStructureId", jsonParamDSQuery.getDataStructureId())
                .pagination(jsonParamDSQuery.getPageParam())
                .orders(jsonParamDSQuery.getOrderParams())
                .get();
        return jpaTemplate.findPage(queryCondition, JsonParamDSEntity.class);
    }
}