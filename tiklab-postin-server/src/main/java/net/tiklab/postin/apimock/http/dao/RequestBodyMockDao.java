package net.tiklab.postin.apimock.http.dao;

import net.tiklab.postin.apimock.http.entity.RequestBodyMockEntity;
import net.tiklab.postin.apimock.http.model.RequestBodyMockQuery;
import net.tiklab.core.page.Pagination;
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
public class RequestBodyMockDao{

    private static Logger logger = LoggerFactory.getLogger(RequestBodyMockDao.class);

    @Autowired
    JpaTemplate jpaTemplate;

    /**
     * 创建用户
     * @param requestBodyMockEntity
     * @return
     */
    public String createRequestBodyMock(RequestBodyMockEntity requestBodyMockEntity) {
        return jpaTemplate.save(requestBodyMockEntity,String.class);
    }

    /**
     * 更新用户
     * @param requestBodyMockEntity
     */
    public void updateRequestBodyMock(RequestBodyMockEntity requestBodyMockEntity){
        jpaTemplate.update(requestBodyMockEntity);
    }

    /**
     * 删除用户
     * @param id
     */
    public void deleteRequestBodyMock(String id){
        jpaTemplate.delete(RequestBodyMockEntity.class,id);
    }

    /**
     * 查找用户
     * @param id
     * @return
     */
    public RequestBodyMockEntity findRequestBodyMock(String id){
        return jpaTemplate.findOne(RequestBodyMockEntity.class,id);
    }

    /**
    * findAllRequestBodyMock
    * @return
    */
    public List<RequestBodyMockEntity> findAllRequestBodyMock() {
        return jpaTemplate.findAll(RequestBodyMockEntity.class);
    }

    public List<RequestBodyMockEntity> findRequestBodyMockList(List<String> idList) {
        return jpaTemplate.findList(RequestBodyMockEntity.class,idList);
    }

    public List<RequestBodyMockEntity> findRequestBodyMockList(RequestBodyMockQuery requestBodyMockQuery) {
        QueryCondition queryCondition = QueryBuilders.createQuery(RequestBodyMockEntity.class)
                .eq("mockId", requestBodyMockQuery.getMockId())
                .orders(requestBodyMockQuery.getOrderParams())
                .get();
        return jpaTemplate.findList(queryCondition, RequestBodyMockEntity.class);
    }

    public Pagination<RequestBodyMockEntity> findRequestBodyMockPage(RequestBodyMockQuery requestBodyMockQuery) {
        QueryCondition queryCondition = QueryBuilders.createQuery(RequestBodyMockEntity.class)
                .eq("mockId", requestBodyMockQuery.getMockId())
                .pagination(requestBodyMockQuery.getPageParam())
                .orders(requestBodyMockQuery.getOrderParams())
                .get();
        return jpaTemplate.findPage(queryCondition, RequestBodyMockEntity.class);
    }
}