package net.tiklab.postin.api.http.definition.dao;

import net.tiklab.postin.api.http.definition.entity.JsonParamEntity;
import net.tiklab.postin.api.http.definition.model.JsonParamQuery;
import net.tiklab.core.page.Pagination;
import net.tiklab.dal.jpa.JpaTemplate;
import net.tiklab.dal.jpa.criterial.condition.DeleteCondition;
import net.tiklab.dal.jpa.criterial.condition.QueryCondition;
import net.tiklab.dal.jpa.criterial.conditionbuilder.QueryBuilders;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 定义
 * http协议
 * 请求中json 数据访问
 */
@Repository
public class JsonParamDao{

    private static Logger logger = LoggerFactory.getLogger(JsonParamDao.class);

    @Autowired
    JpaTemplate jpaTemplate;

    /**
     * 创建请求中json
     * @param jsonParamPo
     * @return
     */
    public String createJsonParam(JsonParamEntity jsonParamPo) {
        return jpaTemplate.save(jsonParamPo,String.class);
    }

    /**
     * 更新请求中json
     * @param jsonParamPo
     */
    public void updateJsonParam(JsonParamEntity jsonParamPo){
        jpaTemplate.update(jsonParamPo);
    }

    /**
     * 删除请求中json
     * @param id
     */
    public void deleteJsonParam(String id){
        jpaTemplate.delete(JsonParamEntity.class,id);
    }

    /**
     * 通过条件删除请求中json
     * @param deleteCondition
     */
    public void deleteJsonParamList(DeleteCondition deleteCondition){
        jpaTemplate.delete(deleteCondition);
    }

    /**
     * 查找请求中json
     * @param id
     * @return
     */
    public JsonParamEntity findJsonParam(String id){
        return jpaTemplate.findOne(JsonParamEntity.class,id);
    }

    /**
    * 查找所有请求中json
    * @return
    */
    public List<JsonParamEntity> findAllJsonParam() {
        return jpaTemplate.findAll(JsonParamEntity.class);
    }

    public List<JsonParamEntity> findJsonParamList(List<String> idList) {
        return jpaTemplate.findList(JsonParamEntity.class,idList);
    }

    /**
     * 根据查询参数查找请求中json
     * @param jsonParamQuery
     * @return
     */
    public List<JsonParamEntity> findJsonParamList(JsonParamQuery jsonParamQuery) {
        QueryCondition queryCondition = QueryBuilders.createQuery(JsonParamEntity.class)
                .eq("httpId", jsonParamQuery.getHttpId())
                .orders(jsonParamQuery.getOrderParams())
                .get();
        return jpaTemplate.findList(queryCondition, JsonParamEntity.class);
    }

    /**
     * 根据查询参数按分页查找请求中json
     * @param jsonParamQuery
     * @return
     */
    public Pagination<JsonParamEntity> findJsonParamPage(JsonParamQuery jsonParamQuery) {
        QueryCondition queryCondition = QueryBuilders.createQuery(JsonParamEntity.class)
                .eq("httpId", jsonParamQuery.getHttpId())
                .pagination(jsonParamQuery.getPageParam())
                .orders(jsonParamQuery.getOrderParams())
                .get();
        return jpaTemplate.findPage(queryCondition, JsonParamEntity.class);
    }
}