package io.tiklab.postin.api.http.mock.dao;

import io.tiklab.core.page.Pagination;
import io.tiklab.dal.jpa.criterial.condition.DeleteCondition;
import io.tiklab.postin.api.http.mock.entity.JsonParamMockEntity;
import io.tiklab.postin.api.http.mock.model.JsonParamMockQuery;
import io.tiklab.dal.jpa.JpaTemplate;
import io.tiklab.dal.jpa.criterial.condition.QueryCondition;
import io.tiklab.dal.jpa.criterial.conditionbuilder.QueryBuilders;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * mock
 * 请求中json 数据访问
 */
@Repository
public class JsonParamMockDao{

    private static Logger logger = LoggerFactory.getLogger(JsonParamMockDao.class);

    @Autowired
    JpaTemplate jpaTemplate;

    /**
     * 创建json
     * @param jsonParamMockEntity
     * @return
     */
    public String createJsonParamMock(JsonParamMockEntity jsonParamMockEntity) {
        return jpaTemplate.save(jsonParamMockEntity,String.class);
    }

    /**
     * 更新json
     * @param jsonParamMockEntity
     */
    public void updateJsonParamMock(JsonParamMockEntity jsonParamMockEntity){
        jpaTemplate.update(jsonParamMockEntity);
    }

    /**
     * 删除json
     * @param id
     */
    public void deleteJsonParamMock(String id){
        jpaTemplate.delete(JsonParamMockEntity.class,id);
    }

    /**
     * 通过条件删除
     * @param deleteCondition
     */
    public void deleteJsonParamMockList(DeleteCondition deleteCondition){
        jpaTemplate.delete(deleteCondition);
    }


    /**
     * 查找json
     * @param id
     * @return
     */
    public JsonParamMockEntity findJsonParamMock(String id){
        return jpaTemplate.findOne(JsonParamMockEntity.class,id);
    }

    /**
    * 查找所有json
    * @return
    */
    public List<JsonParamMockEntity> findAllJsonParamMock() {
        return jpaTemplate.findAll(JsonParamMockEntity.class);
    }

    public List<JsonParamMockEntity> findJsonParamMockList(List<String> idList) {
        return jpaTemplate.findList(JsonParamMockEntity.class,idList);
    }

    /**
     * 根据查询参数查找json列表
     * @param jsonParamMockQuery
     * @return
     */
    public List<JsonParamMockEntity> findJsonParamMockList(JsonParamMockQuery jsonParamMockQuery) {
        QueryCondition queryCondition = QueryBuilders.createQuery(JsonParamMockEntity.class)
                .eq("mockId", jsonParamMockQuery.getMockId())
                .orders(jsonParamMockQuery.getOrderParams())
                .get();
        return jpaTemplate.findList(queryCondition, JsonParamMockEntity.class);
    }

    /**
     * 根据查询参数按分页查找json列表
     * @param jsonParamMockQuery
     * @return
     */
    public Pagination<JsonParamMockEntity> findJsonParamMockPage(JsonParamMockQuery jsonParamMockQuery) {
        QueryCondition queryCondition = QueryBuilders.createQuery(JsonParamMockEntity.class)
                .eq("mockId", jsonParamMockQuery.getMockId())
                .pagination(jsonParamMockQuery.getPageParam())
                .orders(jsonParamMockQuery.getOrderParams())
                .get();
        return jpaTemplate.findPage(queryCondition, JsonParamMockEntity.class);
    }
}