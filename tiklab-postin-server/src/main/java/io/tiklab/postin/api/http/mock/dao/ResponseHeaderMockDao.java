package io.tiklab.postin.api.http.mock.dao;

import io.tiklab.postin.api.http.mock.entity.ResponseHeaderMockEntity;
import io.tiklab.dal.jpa.criterial.condition.DeleteCondition;
import io.tiklab.postin.api.http.mock.model.ResponseHeaderMockQuery;
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
 * 响应头 数据访问
 */
@Repository
public class ResponseHeaderMockDao{

    private static Logger logger = LoggerFactory.getLogger(ResponseHeaderMockDao.class);

    @Autowired
    JpaTemplate jpaTemplate;

    /**
     * 创建响应头
     * @param responseHeaderMockEntity
     * @return
     */
    public String createResponseHeaderMock(ResponseHeaderMockEntity responseHeaderMockEntity) {
        return jpaTemplate.save(responseHeaderMockEntity,String.class);
    }

    /**
     * 更新响应头
     * @param responseHeaderMockEntity
     */
    public void updateResponseHeaderMock(ResponseHeaderMockEntity responseHeaderMockEntity){
        jpaTemplate.update(responseHeaderMockEntity);
    }

    /**
     * 删除响应头
     * @param id
     */
    public void deleteResponseHeaderMock(String id){
        jpaTemplate.delete(ResponseHeaderMockEntity.class,id);
    }

    /**
     * 通过条件删除
     * @param deleteCondition
     */
    public void deleteResponseHeaderMockList(DeleteCondition deleteCondition){
        jpaTemplate.delete(deleteCondition);
    }

    /**
     * 根据id查询响应头
     * @param id
     * @return
     */
    public ResponseHeaderMockEntity findResponseHeaderMock(String id){
        return jpaTemplate.findOne(ResponseHeaderMockEntity.class,id);
    }

    /**
    * 查询所有响应头
    * @return
    */
    public List<ResponseHeaderMockEntity> findAllResponseHeaderMock() {
        return jpaTemplate.findAll(ResponseHeaderMockEntity.class);
    }

    /**
     * 根据查询参数查找响应头
     * @param responseHeaderMockQuery
     * @return
     */
    public List<ResponseHeaderMockEntity> findResponseHeaderMockList(ResponseHeaderMockQuery responseHeaderMockQuery) {
        QueryCondition queryCondition = QueryBuilders.createQuery(ResponseHeaderMockEntity.class)
                .eq("mockId", responseHeaderMockQuery.getMockId())
                .orders(responseHeaderMockQuery.getOrderParams())
                .get();
        return jpaTemplate.findList(queryCondition, ResponseHeaderMockEntity.class);
    }

    /**
     * 根据查询参数按分页查找响应头
     * @param responseHeaderMockQuery
     * @return
     */
    public Pagination<ResponseHeaderMockEntity> findResponseHeaderMockPage(ResponseHeaderMockQuery responseHeaderMockQuery) {
        QueryCondition queryCondition = QueryBuilders.createQuery(ResponseHeaderMockEntity.class)
                .eq("mockId", responseHeaderMockQuery.getMockId())
                .pagination(responseHeaderMockQuery.getPageParam())
                .orders(responseHeaderMockQuery.getOrderParams())
                .get();
        return jpaTemplate.findPage(queryCondition, ResponseHeaderMockEntity.class);
    }
}