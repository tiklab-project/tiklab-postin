package com.tiklab.postin.apimock.http.dao;

import com.tiklab.postin.apimock.http.entity.RawResponseMockEntity;
import com.tiklab.postin.apimock.http.model.RawResponseMockQuery;
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
public class RawResponseMockDao{

    private static Logger logger = LoggerFactory.getLogger(RawResponseMockDao.class);

    @Autowired
    JpaTemplate jpaTemplate;

    /**
     * 创建用户
     * @param rawResponseMockEntity
     * @return
     */
    public String createRawResponseMock(RawResponseMockEntity rawResponseMockEntity) {
        return jpaTemplate.save(rawResponseMockEntity,String.class);
    }

    /**
     * 更新用户
     * @param rawResponseMockEntity
     */
    public void updateRawResponseMock(RawResponseMockEntity rawResponseMockEntity){
        jpaTemplate.update(rawResponseMockEntity);
    }

    /**
     * 删除用户
     * @param id
     */
    public void deleteRawResponseMock(String id){
        jpaTemplate.delete(RawResponseMockEntity.class,id);
    }

    /**
     * 查找用户
     * @param id
     * @return
     */
    public RawResponseMockEntity findRawResponseMock(String id){
        return jpaTemplate.findOne(RawResponseMockEntity.class,id);
    }

    /**
    * findAllRawResponseMock
    * @return
    */
    public List<RawResponseMockEntity> findAllRawResponseMock() {
        return jpaTemplate.findAll(RawResponseMockEntity.class);
    }

    public List<RawResponseMockEntity> findRawResponseMockList(RawResponseMockQuery rawResponseMockQuery) {
        QueryCondition queryCondition = QueryBuilders.createQuery(RawResponseMockEntity.class)
                .eq("mockId", rawResponseMockQuery.getMockId())
                .orders(rawResponseMockQuery.getOrderParams())
                .get();
        return jpaTemplate.findList(queryCondition, RawResponseMockEntity.class);
    }

    public Pagination<RawResponseMockEntity> findRawResponseMockPage(RawResponseMockQuery rawResponseMockQuery) {
        QueryCondition queryCondition = QueryBuilders.createQuery(RawResponseMockEntity.class)
                .eq("mockId", rawResponseMockQuery.getMockId())
                .pagination(rawResponseMockQuery.getPageParam())
                .orders(rawResponseMockQuery.getOrderParams())
                .get();
        return jpaTemplate.findPage(queryCondition, RawResponseMockEntity.class);
    }
}