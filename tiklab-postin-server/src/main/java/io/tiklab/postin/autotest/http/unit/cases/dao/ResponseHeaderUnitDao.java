package io.tiklab.postin.autotest.http.unit.cases.dao;

import io.tiklab.core.page.Pagination;
import io.tiklab.dal.jpa.criterial.condition.DeleteCondition;
import io.tiklab.dal.jpa.criterial.condition.QueryCondition;
import io.tiklab.dal.jpa.criterial.conditionbuilder.QueryBuilders;
import io.tiklab.dal.jpa.JpaTemplate;
import io.tiklab.postin.autotest.http.unit.cases.entity.ResponseHeaderUnitEntity;
import io.tiklab.postin.autotest.http.unit.cases.model.ResponseHeaderUnitQuery;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 响应头 数据访问
 */
@Repository
public class ResponseHeaderUnitDao {

    private static Logger logger = LoggerFactory.getLogger(ResponseHeaderUnitDao.class);

    @Autowired
    JpaTemplate jpaTemplate;

    /**
     * 创建响应头
     * @param responseHeaderUnitEntity
     * @return
     */
    public String createResponseHeader(ResponseHeaderUnitEntity responseHeaderUnitEntity) {
        return jpaTemplate.save(responseHeaderUnitEntity,String.class);
    }

    /**
     * 更新响应头
     * @param responseHeaderUnitEntity
     */
    public void updateResponseHeader(ResponseHeaderUnitEntity responseHeaderUnitEntity){
        jpaTemplate.update(responseHeaderUnitEntity);
    }

    /**
     * 删除响应头
     * @param id
     */
    public void deleteResponseHeader(String id){
        jpaTemplate.delete(ResponseHeaderUnitEntity.class,id);
    }

    public void deleteResponseHeader(DeleteCondition deleteCondition){
        jpaTemplate.delete(deleteCondition);
    }

    /**
     * 查找响应头
     * @param id
     * @return
     */
    public ResponseHeaderUnitEntity findResponseHeader(String id){
        return jpaTemplate.findOne(ResponseHeaderUnitEntity.class,id);
    }

    /**
    * 查找所有响应头
    * @return
    */
    public List<ResponseHeaderUnitEntity> findAllResponseHeader() {
        return jpaTemplate.findAll(ResponseHeaderUnitEntity.class);
    }

    public List<ResponseHeaderUnitEntity> findResponseHeaderList(List<String> idList) {
        return jpaTemplate.findList(ResponseHeaderUnitEntity.class,idList);
    }

    /**
     * 根据查询参数查询响应头列表
     * @param responseHeaderUnitQuery
     * @return
     */
    public List<ResponseHeaderUnitEntity> findResponseHeaderList(ResponseHeaderUnitQuery responseHeaderUnitQuery) {
        QueryCondition queryCondition = QueryBuilders.createQuery(ResponseHeaderUnitEntity.class)
                .eq("apiUnitId", responseHeaderUnitQuery.getApiUnitId())
                .orders(responseHeaderUnitQuery.getOrderParams())
                .get();
        return jpaTemplate.findList(queryCondition, ResponseHeaderUnitEntity.class);
    }

    /**
     * 根据查询参数按分页查询响应头
     * @param responseHeaderUnitQuery
     * @return
     */
    public Pagination<ResponseHeaderUnitEntity> findResponseHeaderPage(ResponseHeaderUnitQuery responseHeaderUnitQuery) {
        QueryCondition queryCondition = QueryBuilders.createQuery(ResponseHeaderUnitEntity.class)
                .eq("apiUnitId", responseHeaderUnitQuery.getApiUnitId())
                .orders(responseHeaderUnitQuery.getOrderParams())
                .pagination(responseHeaderUnitQuery.getPageParam())
                .get();
        return jpaTemplate.findPage(queryCondition, ResponseHeaderUnitEntity.class);
    }
}