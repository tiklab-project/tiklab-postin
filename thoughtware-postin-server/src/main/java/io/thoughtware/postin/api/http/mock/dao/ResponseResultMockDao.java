package io.thoughtware.postin.api.http.mock.dao;

import io.thoughtware.postin.api.http.mock.entity.ResponseResultMockEntity;
import io.thoughtware.postin.api.http.mock.model.ResponseResultMockQuery;
import io.thoughtware.core.page.Pagination;
import io.thoughtware.dal.jpa.JpaTemplate;
import io.thoughtware.dal.jpa.criterial.condition.QueryCondition;
import io.thoughtware.dal.jpa.criterial.conditionbuilder.QueryBuilders;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * mock
 * 响应结果 数据访问
 */
@Repository
public class ResponseResultMockDao {

    private static Logger logger = LoggerFactory.getLogger(ResponseResultMockDao.class);

    @Autowired
    JpaTemplate jpaTemplate;

    /**
     * 创建响应结果
     * @param responseResultMockEntity
     * @return
     */
    public String createResponseResultMock(ResponseResultMockEntity responseResultMockEntity) {
        return jpaTemplate.save(responseResultMockEntity,String.class);
    }

    /**
     * 更新响应结果
     * @param responseResultMockEntity
     */
    public void updateResponseResultMock(ResponseResultMockEntity responseResultMockEntity){
        jpaTemplate.update(responseResultMockEntity);
    }

    /**
     * 删除响应结果
     * @param id
     */
    public void deleteResponseResultMock(String id){
        jpaTemplate.delete(ResponseResultMockEntity.class,id);
    }

    /**
     * 查找响应结果
     * @param id
     * @return
     */
    public ResponseResultMockEntity findResponseResultMock(String id){
        return jpaTemplate.findOne(ResponseResultMockEntity.class,id);
    }

    /**
    * 查找所有响应结果
    * @return
    */
    public List<ResponseResultMockEntity> findAllResponseResultMock() {
        return jpaTemplate.findAll(ResponseResultMockEntity.class);
    }

    /**
     * 根据查询参数查找响应结果
     * @param responseResultMockQuery
     * @return
     */
    public List<ResponseResultMockEntity> findResponseResultMockList(ResponseResultMockQuery responseResultMockQuery) {
        QueryCondition queryCondition = QueryBuilders.createQuery(ResponseResultMockEntity.class)
                .eq("mockId", responseResultMockQuery.getMockId())
                .orders(responseResultMockQuery.getOrderParams())
                .get();
        return jpaTemplate.findList(queryCondition, ResponseResultMockEntity.class);
    }

    /**
     * 根据查询参数按分页查找响应结果
     * @param responseResultMockQuery
     * @return
     */
    public Pagination<ResponseResultMockEntity> findResponseResultMockPage(ResponseResultMockQuery responseResultMockQuery) {
        QueryCondition queryCondition = QueryBuilders.createQuery(ResponseResultMockEntity.class)
                .eq("mockId", responseResultMockQuery.getMockId())
                .pagination(responseResultMockQuery.getPageParam())
                .orders(responseResultMockQuery.getOrderParams())
                .get();
        return jpaTemplate.findPage(queryCondition, ResponseResultMockEntity.class);
    }
}