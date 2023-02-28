package net.tiklab.postin.api.http.mock.dao;

import net.tiklab.postin.api.http.mock.entity.RequestMockEntity;
import net.tiklab.postin.api.http.mock.model.RequestMockQuery;
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
 * mock
 * 请求体 数据访问
 */
@Repository
public class RequestMockDao{

    private static Logger logger = LoggerFactory.getLogger(RequestMockDao.class);

    @Autowired
    JpaTemplate jpaTemplate;

    /**
     * 创建请求体
     * @param requestMockEntity
     * @return
     */
    public String createRequestMock(RequestMockEntity requestMockEntity) {
        return jpaTemplate.save(requestMockEntity,String.class);
    }

    /**
     * 更新请求体
     * @param requestMockEntity
     */
    public void updateRequestMock(RequestMockEntity requestMockEntity){
        jpaTemplate.update(requestMockEntity);
    }

    /**
     * 删除请求体
     * @param id
     */
    public void deleteRequestMock(String id){
        jpaTemplate.delete(RequestMockEntity.class,id);
    }

    /**
     * 查找请求体
     * @param id
     * @return
     */
    public RequestMockEntity findRequestMock(String id){
        return jpaTemplate.findOne(RequestMockEntity.class,id);
    }

    /**
    * 查询所有请求体
    * @return
    */
    public List<RequestMockEntity> findAllRequestMock() {
        return jpaTemplate.findAll(RequestMockEntity.class);
    }

    public List<RequestMockEntity> findRequestMockList(List<String> idList) {
        return jpaTemplate.findList(RequestMockEntity.class,idList);
    }

    /**
     * 根据查询参数查找请求体
     * @param requestMockQuery
     * @return
     */
    public List<RequestMockEntity> findRequestMockList(RequestMockQuery requestMockQuery) {
        QueryCondition queryCondition = QueryBuilders.createQuery(RequestMockEntity.class)
                .eq("mockId", requestMockQuery.getMockId())
                .orders(requestMockQuery.getOrderParams())
                .get();
        return jpaTemplate.findList(queryCondition, RequestMockEntity.class);
    }

    /**
     * 根据查询参数按分页查找请求体
     * @param requestMockQuery
     * @return
     */
    public Pagination<RequestMockEntity> findRequestMockPage(RequestMockQuery requestMockQuery) {
        QueryCondition queryCondition = QueryBuilders.createQuery(RequestMockEntity.class)
                .eq("mockId", requestMockQuery.getMockId())
                .pagination(requestMockQuery.getPageParam())
                .orders(requestMockQuery.getOrderParams())
                .get();
        return jpaTemplate.findPage(queryCondition, RequestMockEntity.class);
    }
}