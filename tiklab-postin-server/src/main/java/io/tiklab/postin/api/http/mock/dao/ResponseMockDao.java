package io.tiklab.postin.api.http.mock.dao;

import io.tiklab.postin.api.http.mock.entity.ResponseMockEntity;
import io.tiklab.core.page.Pagination;
import io.tiklab.postin.api.http.mock.model.ResponseMockQuery;
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
 * 响应 数据访问
 */
@Repository
public class ResponseMockDao{

    private static Logger logger = LoggerFactory.getLogger(ResponseMockDao.class);

    @Autowired
    JpaTemplate jpaTemplate;

    /**
     * 创建响应管理
     * @param responseMockEntity
     * @return
     */
    public String createResponseMock(ResponseMockEntity responseMockEntity) {
        return jpaTemplate.save(responseMockEntity,String.class);
    }

    /**
     * 更新响应管理
     * @param responseMockEntity
     */
    public void updateResponseMock(ResponseMockEntity responseMockEntity){
        jpaTemplate.update(responseMockEntity);
    }

    /**
     * 删除响应管理
     * @param id
     */
    public void deleteResponseMock(String id){
        jpaTemplate.delete(ResponseMockEntity.class,id);
    }

    /**
     * 查找响应管理
     * @param id
     * @return
     */
    public ResponseMockEntity findResponseMock(String id){
        return jpaTemplate.findOne(ResponseMockEntity.class,id);
    }

    /**
    * 查找所有响应管理
    * @return
    */
    public List<ResponseMockEntity> findAllResponseMock() {
        return jpaTemplate.findAll(ResponseMockEntity.class);
    }

    /**
     * 根据查询参数查找响应管理
     * @param responseMockQuery
     * @return
     */
    public List<ResponseMockEntity> findResponseMockList(ResponseMockQuery responseMockQuery) {
        QueryCondition queryCondition = QueryBuilders.createQuery(ResponseMockEntity.class)
                .eq("mockId", responseMockQuery.getMockId())
                .orders(responseMockQuery.getOrderParams())
                .get();
        return jpaTemplate.findList(queryCondition, ResponseMockEntity.class);
    }

    /**
     * 根据查询参数按分页查找响应管理
     * @param responseMockQuery
     * @return
     */
    public Pagination<ResponseMockEntity> findResponseMockPage(ResponseMockQuery responseMockQuery) {
        QueryCondition queryCondition = QueryBuilders.createQuery(ResponseMockEntity.class)
                .eq("mockId", responseMockQuery.getMockId())
                .pagination(responseMockQuery.getPageParam())
                .orders(responseMockQuery.getOrderParams())
                .get();
        return jpaTemplate.findPage(queryCondition, ResponseMockEntity.class);
    }
}