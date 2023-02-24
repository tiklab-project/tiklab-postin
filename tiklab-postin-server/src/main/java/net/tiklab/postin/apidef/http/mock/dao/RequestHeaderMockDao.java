package net.tiklab.postin.apidef.http.mock.dao;

import net.tiklab.postin.apidef.http.mock.entity.RequestHeaderMockEntity;
import net.tiklab.postin.apidef.http.mock.model.RequestHeaderMockQuery;
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
public class RequestHeaderMockDao{

    private static Logger logger = LoggerFactory.getLogger(RequestHeaderMockDao.class);

    @Autowired
    JpaTemplate jpaTemplate;

    /**
     * 创建用户
     * @param requestHeaderMockEntity
     * @return
     */
    public String createRequestHeaderMock(RequestHeaderMockEntity requestHeaderMockEntity) {
        return jpaTemplate.save(requestHeaderMockEntity,String.class);
    }

    /**
     * 更新用户
     * @param requestHeaderMockEntity
     */
    public void updateRequestHeaderMock(RequestHeaderMockEntity requestHeaderMockEntity){
        jpaTemplate.update(requestHeaderMockEntity);
    }

    /**
     * 删除用户
     * @param id
     */
    public void deleteRequestHeaderMock(String id){
        jpaTemplate.delete(RequestHeaderMockEntity.class,id);
    }

    /**
     * 查找用户
     * @param id
     * @return
     */
    public RequestHeaderMockEntity findRequestHeaderMock(String id){
        return jpaTemplate.findOne(RequestHeaderMockEntity.class,id);
    }

    /**
    * findAllRequestHeaderMock
    * @return
    */
    public List<RequestHeaderMockEntity> findAllRequestHeaderMock() {
        return jpaTemplate.findAll(RequestHeaderMockEntity.class);
    }

    public List<RequestHeaderMockEntity> findRequestHeaderMockList(RequestHeaderMockQuery requestHeaderMockQuery) {
        QueryCondition queryCondition = QueryBuilders.createQuery(RequestHeaderMockEntity.class)
                .eq("mockId", requestHeaderMockQuery.getMockId())
                .orders(requestHeaderMockQuery.getOrderParams())
                .get();
        return jpaTemplate.findList(queryCondition, RequestHeaderMockEntity.class);
    }

    public Pagination<RequestHeaderMockEntity> findRequestHeaderMockPage(RequestHeaderMockQuery requestHeaderMockQuery) {
        QueryCondition queryCondition = QueryBuilders.createQuery(RequestHeaderMockEntity.class)
                .eq("mockId", requestHeaderMockQuery.getMockId())
                .pagination(requestHeaderMockQuery.getPageParam())
                .orders(requestHeaderMockQuery.getOrderParams())
                .get();
        return jpaTemplate.findPage(queryCondition, RequestHeaderMockEntity.class);
    }
}