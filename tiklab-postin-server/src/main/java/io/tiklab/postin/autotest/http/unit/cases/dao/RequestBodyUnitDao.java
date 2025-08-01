package io.tiklab.postin.autotest.http.unit.cases.dao;

import io.tiklab.core.page.Pagination;
import io.tiklab.dal.jpa.criterial.condition.DeleteCondition;
import io.tiklab.dal.jpa.criterial.condition.QueryCondition;
import io.tiklab.dal.jpa.criterial.conditionbuilder.QueryBuilders;
import io.tiklab.dal.jpa.JpaTemplate;
import io.tiklab.postin.autotest.http.unit.cases.entity.RequestBodyUnitEntity;
import io.tiklab.postin.autotest.http.unit.cases.model.RequestBodyUnitQuery;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 请求体 数据访问
 */
@Repository
public class RequestBodyUnitDao {

    private static Logger logger = LoggerFactory.getLogger(RequestBodyUnitDao.class);

    @Autowired
    JpaTemplate jpaTemplate;

    /**
     * 创建请求体
     * @param requestBodyUnitEntity
     * @return
     */
    public String createRequestBody(RequestBodyUnitEntity requestBodyUnitEntity) {
        return jpaTemplate.save(requestBodyUnitEntity,String.class);
    }

    /**
     * 更新请求体
     * @param requestBodyUnitEntity
     */
    public void updateRequestBody(RequestBodyUnitEntity requestBodyUnitEntity){
        jpaTemplate.update(requestBodyUnitEntity);
    }

    /**
     * 删除请求体
     * @param id
     */
    public void deleteRequestBody(String id){
        jpaTemplate.delete(RequestBodyUnitEntity.class,id);
    }

    public void deleteRequestBody(DeleteCondition deleteCondition){
        jpaTemplate.delete(deleteCondition);
    }

    /**
     * 查找请求体
     * @param id
     * @return
     */
    public RequestBodyUnitEntity findRequestBody(String id){
        return jpaTemplate.findOne(RequestBodyUnitEntity.class,id);
    }

    /**
    * 查找所有请求体
    * @return
    */
    public List<RequestBodyUnitEntity> findAllRequestBody() {
        return jpaTemplate.findAll(RequestBodyUnitEntity.class);
    }

    public List<RequestBodyUnitEntity> findRequestBodyList(List<String> idList) {
        return jpaTemplate.findList(RequestBodyUnitEntity.class,idList);
    }

    /**
     * 根据查询参数查询请求体列表
     * @param requestBodyUnitQuery
     * @return
     */
    public List<RequestBodyUnitEntity> findRequestBodyList(RequestBodyUnitQuery requestBodyUnitQuery) {
        QueryCondition queryCondition = QueryBuilders.createQuery(RequestBodyUnitEntity.class)
                .eq("apiUnitId", requestBodyUnitQuery.getApiUnitId())
                .orders(requestBodyUnitQuery.getOrderParams())
                .get();
        return jpaTemplate.findList(queryCondition, RequestBodyUnitEntity.class);
    }

    /**
     * 根据查询参数按分页查询请求体
     * @param requestBodyUnitQuery
     * @return
     */
    public Pagination<RequestBodyUnitEntity> findRequestBodyPage(RequestBodyUnitQuery requestBodyUnitQuery) {
        QueryCondition queryCondition = QueryBuilders.createQuery(RequestBodyUnitEntity.class)
                .eq("apiUnitId", requestBodyUnitQuery.getApiUnitId())
                .orders(requestBodyUnitQuery.getOrderParams())
                .pagination(requestBodyUnitQuery.getPageParam())
                .get();
        return jpaTemplate.findPage(queryCondition, RequestBodyUnitEntity.class);
    }
}