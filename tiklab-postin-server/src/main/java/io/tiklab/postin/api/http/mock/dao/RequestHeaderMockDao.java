package io.tiklab.postin.api.http.mock.dao;

import io.tiklab.dal.jpa.criterial.condition.DeleteCondition;
import io.tiklab.postin.api.http.mock.entity.RequestHeaderMockEntity;
import io.tiklab.postin.api.http.mock.model.RequestHeaderMockQuery;
import io.tiklab.core.page.Pagination;
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
 * 请求头 数据访问
 */
@Repository
public class RequestHeaderMockDao{

    private static Logger logger = LoggerFactory.getLogger(RequestHeaderMockDao.class);

    @Autowired
    JpaTemplate jpaTemplate;

    /**
     * 创建请求头
     * @param requestHeaderMockEntity
     * @return
     */
    public String createRequestHeaderMock(RequestHeaderMockEntity requestHeaderMockEntity) {
        return jpaTemplate.save(requestHeaderMockEntity,String.class);
    }

    /**
     * 更新请求头
     * @param requestHeaderMockEntity
     */
    public void updateRequestHeaderMock(RequestHeaderMockEntity requestHeaderMockEntity){
        jpaTemplate.update(requestHeaderMockEntity);
    }

    /**
     * 删除请求头
     * @param id
     */
    public void deleteRequestHeaderMock(String id){
        jpaTemplate.delete(RequestHeaderMockEntity.class,id);
    }


    /**
     * 通过条件删除
     * @param deleteCondition
     */
    public void deleteRequestHeaderMockList(DeleteCondition deleteCondition){
        jpaTemplate.delete(deleteCondition);
    }


    /**
     * 查找请求头
     * @param id
     * @return
     */
    public RequestHeaderMockEntity findRequestHeaderMock(String id){
        return jpaTemplate.findOne(RequestHeaderMockEntity.class,id);
    }

    /**
    * 查找所有请求头
    * @return
    */
    public List<RequestHeaderMockEntity> findAllRequestHeaderMock() {
        return jpaTemplate.findAll(RequestHeaderMockEntity.class);
    }

    /**
     * 根据查询参数查找请求头
     * @param requestHeaderMockQuery
     * @return
     */
    public List<RequestHeaderMockEntity> findRequestHeaderMockList(RequestHeaderMockQuery requestHeaderMockQuery) {
        QueryCondition queryCondition = QueryBuilders.createQuery(RequestHeaderMockEntity.class)
                .eq("mockId", requestHeaderMockQuery.getMockId())
                .orders(requestHeaderMockQuery.getOrderParams())
                .get();
        return jpaTemplate.findList(queryCondition, RequestHeaderMockEntity.class);
    }

    /**
     * 根据查询参数按分页查找请求头
     * @param requestHeaderMockQuery
     * @return
     */
    public Pagination<RequestHeaderMockEntity> findRequestHeaderMockPage(RequestHeaderMockQuery requestHeaderMockQuery) {
        QueryCondition queryCondition = QueryBuilders.createQuery(RequestHeaderMockEntity.class)
                .eq("mockId", requestHeaderMockQuery.getMockId())
                .pagination(requestHeaderMockQuery.getPageParam())
                .orders(requestHeaderMockQuery.getOrderParams())
                .get();
        return jpaTemplate.findPage(queryCondition, RequestHeaderMockEntity.class);
    }
}