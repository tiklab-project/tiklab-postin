package com.tiklab.postin.apimock.http.dao;

import com.tiklab.postin.apimock.http.entity.ResponseHeaderMockEntity;
import com.tiklab.postin.apimock.http.model.ResponseHeaderMockQuery;
import com.tiklab.core.page.Pagination;
import com.tiklab.dal.jpa.JpaTemplate;
import com.tiklab.dal.jpa.criterial.condition.QueryCondition;
import com.tiklab.dal.jpa.criterial.conditionbuilder.QueryBuilders;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 用户数据操作
 */
@Repository
public class ResponseHeaderMockDao{

    private static Logger logger = LoggerFactory.getLogger(ResponseHeaderMockDao.class);

    @Autowired
    JpaTemplate jpaTemplate;

    /**
     * 创建用户
     * @param responseHeaderMockEntity
     * @return
     */
    public String createResponseHeaderMock(ResponseHeaderMockEntity responseHeaderMockEntity) {
        return jpaTemplate.save(responseHeaderMockEntity,String.class);
    }

    /**
     * 更新用户
     * @param responseHeaderMockEntity
     */
    public void updateResponseHeaderMock(ResponseHeaderMockEntity responseHeaderMockEntity){
        jpaTemplate.update(responseHeaderMockEntity);
    }

    /**
     * 删除用户
     * @param id
     */
    public void deleteResponseHeaderMock(String id){
        jpaTemplate.delete(ResponseHeaderMockEntity.class,id);
    }

    /**
     * 查找用户
     * @param id
     * @return
     */
    public ResponseHeaderMockEntity findResponseHeaderMock(String id){
        return jpaTemplate.findOne(ResponseHeaderMockEntity.class,id);
    }

    /**
    * findAllResponseHeaderMock
    * @return
    */
    public List<ResponseHeaderMockEntity> findAllResponseHeaderMock() {
        return jpaTemplate.findAll(ResponseHeaderMockEntity.class);
    }

    public List<ResponseHeaderMockEntity> findResponseHeaderMockList(ResponseHeaderMockQuery responseHeaderMockQuery) {
        QueryCondition queryCondition = QueryBuilders.createQuery(ResponseHeaderMockEntity.class)
                .eq("mockId", responseHeaderMockQuery.getMockId())
                .orders(responseHeaderMockQuery.getOrderParams())
                .get();
        return jpaTemplate.findList(queryCondition, ResponseHeaderMockEntity.class);
    }

    public Pagination<ResponseHeaderMockEntity> findResponseHeaderMockPage(ResponseHeaderMockQuery responseHeaderMockQuery) {
        QueryCondition queryCondition = QueryBuilders.createQuery(ResponseHeaderMockEntity.class)
                .eq("mockId", responseHeaderMockQuery.getMockId())
                .pagination(responseHeaderMockQuery.getPageParam())
                .orders(responseHeaderMockQuery.getOrderParams())
                .get();
        return jpaTemplate.findPage(queryCondition, ResponseHeaderMockEntity.class);
    }
}