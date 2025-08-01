package io.tiklab.postin.autotest.http.unit.cases.dao;

import io.tiklab.core.page.Pagination;
import io.tiklab.dal.jpa.criterial.condition.DeleteCondition;
import io.tiklab.dal.jpa.criterial.condition.QueryCondition;
import io.tiklab.dal.jpa.criterial.conditionbuilder.QueryBuilders;
import io.tiklab.dal.jpa.JpaTemplate;
import io.tiklab.postin.autotest.http.unit.cases.entity.JsonParamUnitEntity;
import io.tiklab.postin.autotest.http.unit.cases.model.JsonParamUnitQuery;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 请求中json 数据访问
 */
@Repository
public class JsonParamUnitDao {

    private static Logger logger = LoggerFactory.getLogger(JsonParamUnitDao.class);

    @Autowired
    JpaTemplate jpaTemplate;

    /**
     * 创建请求中json
     * @param jsonParamUnitEntity
     * @return
     */
    public String createJsonParam(JsonParamUnitEntity jsonParamUnitEntity) {
        return jpaTemplate.save(jsonParamUnitEntity,String.class);
    }

    /**
     * 更新请求中json
     * @param jsonParamUnitEntity
     */
    public void updateJsonParam(JsonParamUnitEntity jsonParamUnitEntity){
        jpaTemplate.update(jsonParamUnitEntity);
    }

    /**
     * 删除请求中json
     * @param id
     */
    public void deleteJsonParam(String id){
        jpaTemplate.delete(JsonParamUnitEntity.class,id);
    }

    public void deleteJsonParam(DeleteCondition deleteCondition){
        jpaTemplate.delete(deleteCondition);
    }

    /**
     * 查找请求中json
     * @param id
     * @return
     */
    public JsonParamUnitEntity findJsonParam(String id){
        return jpaTemplate.findOne(JsonParamUnitEntity.class,id);
    }

    /**
    * 查找所有json
    * @return
    */
    public List<JsonParamUnitEntity> findAllJsonParam() {
        return jpaTemplate.findAll(JsonParamUnitEntity.class);
    }

    public List<JsonParamUnitEntity> findJsonParamList(List<String> idList) {
        return jpaTemplate.findList(JsonParamUnitEntity.class,idList);
    }

    /**
     * 根据查询参数查询json列表
     * @param jsonParamUnitQuery
     * @return
     */
    public List<JsonParamUnitEntity> findJsonParamList(JsonParamUnitQuery jsonParamUnitQuery) {
        QueryCondition queryCondition = QueryBuilders.createQuery(JsonParamUnitEntity.class)
                .eq("apiUnitId", jsonParamUnitQuery.getApiUnitId())
                .orders(jsonParamUnitQuery.getOrderParams())
                .get();
        return jpaTemplate.findList(queryCondition, JsonParamUnitEntity.class);
    }

    /**
     * 根据查询参数按分页查询json
     * @param jsonParamUnitQuery
     * @return
     */
    public Pagination<JsonParamUnitEntity> findJsonParamPage(JsonParamUnitQuery jsonParamUnitQuery) {
        QueryCondition queryCondition = QueryBuilders.createQuery(JsonParamUnitEntity.class)
                .eq("apiUnitId", jsonParamUnitQuery.getApiUnitId())
                .orders(jsonParamUnitQuery.getOrderParams())
                .pagination(jsonParamUnitQuery.getPageParam())
                .get();
        return jpaTemplate.findPage(queryCondition, JsonParamUnitEntity.class);
    }
}