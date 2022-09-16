package net.tiklab.postin.apimock.http.dao;

import net.tiklab.core.page.Pagination;
import net.tiklab.postin.apimock.http.entity.ResponseMockEntity;
import net.tiklab.postin.apimock.http.model.ResponseMockQuery;
import net.tiklab.dal.jpa.JpaTemplate;
import net.tiklab.dal.jpa.criterial.condition.QueryCondition;
import net.tiklab.dal.jpa.criterial.conditionbuilder.QueryBuilders;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 用户数据操作
 */
@Repository
public class ResponseMockDao{

    private static Logger logger = LoggerFactory.getLogger(ResponseMockDao.class);

    @Autowired
    JpaTemplate jpaTemplate;

    /**
     * 创建用户
     * @param responseMockEntity
     * @return
     */
    public String createResponseMock(ResponseMockEntity responseMockEntity) {
        return jpaTemplate.save(responseMockEntity,String.class);
    }

    /**
     * 更新用户
     * @param responseMockEntity
     */
    public void updateResponseMock(ResponseMockEntity responseMockEntity){
        jpaTemplate.update(responseMockEntity);
    }

    /**
     * 删除用户
     * @param id
     */
    public void deleteResponseMock(String id){
        jpaTemplate.delete(ResponseMockEntity.class,id);
    }

    /**
     * 查找用户
     * @param id
     * @return
     */
    public ResponseMockEntity findResponseMock(String id){
        return jpaTemplate.findOne(ResponseMockEntity.class,id);
    }

    /**
    * findAllResponseMock
    * @return
    */
    public List<ResponseMockEntity> findAllResponseMock() {
        return jpaTemplate.findAll(ResponseMockEntity.class);
    }

    public List<ResponseMockEntity> findResponseMockList(ResponseMockQuery responseMockQuery) {
        QueryCondition queryCondition = QueryBuilders.createQuery(ResponseMockEntity.class)
                .eq("mockId", responseMockQuery.getMockId())
                .orders(responseMockQuery.getOrderParams())
                .get();
        return jpaTemplate.findList(queryCondition, ResponseMockEntity.class);
    }

    public Pagination<ResponseMockEntity> findResponseMockPage(ResponseMockQuery responseMockQuery) {
        QueryCondition queryCondition = QueryBuilders.createQuery(ResponseMockEntity.class)
                .eq("mockId", responseMockQuery.getMockId())
                .pagination(responseMockQuery.getPageParam())
                .orders(responseMockQuery.getOrderParams())
                .get();
        return jpaTemplate.findPage(queryCondition, ResponseMockEntity.class);
    }
}