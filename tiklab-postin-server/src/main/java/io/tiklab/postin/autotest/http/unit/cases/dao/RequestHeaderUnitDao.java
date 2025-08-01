package io.tiklab.postin.autotest.http.unit.cases.dao;

import io.tiklab.core.page.Pagination;
import io.tiklab.dal.jpa.criterial.condition.DeleteCondition;
import io.tiklab.dal.jpa.criterial.condition.QueryCondition;
import io.tiklab.dal.jpa.criterial.conditionbuilder.QueryBuilders;
import io.tiklab.dal.jpa.JpaTemplate;
import io.tiklab.postin.autotest.http.unit.cases.entity.RequestHeaderUnitEntity;
import io.tiklab.postin.autotest.http.unit.cases.model.RequestHeaderUnitQuery;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 请求头 数据访问
 */
@Repository
public class RequestHeaderUnitDao {

    private static Logger logger = LoggerFactory.getLogger(RequestHeaderUnitDao.class);

    @Autowired
    JpaTemplate jpaTemplate;

    /**
     * 创建请求头
     * @param requestHeaderUnitEntity
     * @return
     */
    public String createRequestHeader(RequestHeaderUnitEntity requestHeaderUnitEntity) {
        return jpaTemplate.save(requestHeaderUnitEntity,String.class);
    }

    /**
     * 更新请求头
     * @param requestHeaderUnitEntity
     */
    public void updateRequestHeader(RequestHeaderUnitEntity requestHeaderUnitEntity){
        jpaTemplate.update(requestHeaderUnitEntity);
    }

    /**
     * 删除请求头
     * @param id
     */
    public void deleteRequestHeader(String id){
        jpaTemplate.delete(RequestHeaderUnitEntity.class,id);
    }

    public void deleteRequestHeader(DeleteCondition deleteCondition){
        jpaTemplate.delete(deleteCondition);
    }

    /**
     * 查找请求头
     * @param id
     * @return
     */
    public RequestHeaderUnitEntity findRequestHeader(String id){
        return jpaTemplate.findOne(RequestHeaderUnitEntity.class,id);
    }

    /**
    * 查找所有请求头
    * @return
    */
    public List<RequestHeaderUnitEntity> findAllRequestHeader() {
        return jpaTemplate.findAll(RequestHeaderUnitEntity.class);
    }

    public List<RequestHeaderUnitEntity> findRequestHeaderList(List<String> idList) {
        return jpaTemplate.findList(RequestHeaderUnitEntity.class,idList);
    }

    /**
     * 根据参数查询请求头列表
     * @param requestHeaderUnitQuery
     * @return
     */
    public List<RequestHeaderUnitEntity> findRequestHeaderList(RequestHeaderUnitQuery requestHeaderUnitQuery) {
        QueryCondition queryCondition = QueryBuilders.createQuery(RequestHeaderUnitEntity.class)
                .eq("apiUnitId", requestHeaderUnitQuery.getApiUnitId())
                .orders(requestHeaderUnitQuery.getOrderParams())
                .get();
        return jpaTemplate.findList(queryCondition, RequestHeaderUnitEntity.class);
    }

    /**
     * 根据参数按分页查询请求头
     * @param requestHeaderUnitQuery
     * @return
     */
    public Pagination<RequestHeaderUnitEntity> findRequestHeaderPage(RequestHeaderUnitQuery requestHeaderUnitQuery) {
        QueryCondition queryCondition = QueryBuilders.createQuery(RequestHeaderUnitEntity.class)
                .eq("apiUnitId", requestHeaderUnitQuery.getApiUnitId())
                .orders(requestHeaderUnitQuery.getOrderParams())
                .pagination(requestHeaderUnitQuery.getPageParam())
                .get();
        return jpaTemplate.findPage(requestHeaderUnitQuery, RequestHeaderUnitEntity.class);
    }
}