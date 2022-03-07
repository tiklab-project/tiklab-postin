package com.doublekit.apibox.apimock.dao;

import com.doublekit.common.page.Pagination;
import com.doublekit.apibox.apimock.entity.JsonParamMockEntity;
import com.doublekit.apibox.apimock.model.JsonParamMockQuery;
import com.doublekit.dal.jpa.JpaTemplate;
import com.doublekit.dal.jpa.criterial.condition.QueryCondition;
import com.doublekit.dal.jpa.criterial.conditionbuilder.QueryBuilders;
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
     * @param jsonParamMockEntity
     * @return
     */
    public String createJsonParamMock(JsonParamMockEntity jsonParamMockEntity) {
        return jpaTemplate.save(jsonParamMockEntity,String.class);
    }

    /**
     * 更新用户
     * @param jsonParamMockEntity
     */
    public void updateJsonParamMock(JsonParamMockEntity jsonParamMockEntity){
        jpaTemplate.update(jsonParamMockEntity);
    }

    /**
     * 删除用户
     * @param id
     */
    public void deleteJsonParamMock(String id){
        jpaTemplate.delete(JsonParamMockEntity.class,id);
    }

    /**
     * 查找用户
     * @param id
     * @return
     */
    public JsonParamMockEntity findJsonParamMock(String id){
        return jpaTemplate.findOne(JsonParamMockEntity.class,id);
    }

    /**
    * findAllJsonParamMock
    * @return
    */
    public List<JsonParamMockEntity> findAllJsonParamMock() {
        return jpaTemplate.findAll(JsonParamMockEntity.class);
    }

    public List<JsonParamMockEntity> findJsonParamMockList(List<String> idList) {
        return jpaTemplate.findList(JsonParamMockEntity.class,idList);
    }

    public List<JsonParamMockEntity> findJsonParamMockList(JsonParamMockQuery jsonParamMockQuery) {
        QueryCondition queryCondition = QueryBuilders.createQuery(JsonParamMockEntity.class)
                .eq("mockId", jsonParamMockQuery.getMockId())
                .orders(jsonParamMockQuery.getOrderParams())
                .get();
        return jpaTemplate.findList(queryCondition, JsonParamMockEntity.class);
    }

    public Pagination<JsonParamMockEntity> findJsonParamMockPage(JsonParamMockQuery jsonParamMockQuery) {
        QueryCondition queryCondition = QueryBuilders.createQuery(JsonParamMockEntity.class)
                .eq("mockId", jsonParamMockQuery.getMockId())
                .pagination(jsonParamMockQuery.getPageParam())
                .orders(jsonParamMockQuery.getOrderParams())
                .get();
        return jpaTemplate.findPage(queryCondition, JsonParamMockEntity.class);
    }
}