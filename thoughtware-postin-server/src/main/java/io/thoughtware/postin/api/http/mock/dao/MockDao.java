package io.thoughtware.postin.api.http.mock.dao;

import io.thoughtware.postin.api.http.mock.entity.MockEntity;
import io.thoughtware.dal.jpa.criterial.condition.DeleteCondition;
import io.thoughtware.postin.api.http.mock.model.MockQuery;
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
 * mock 数据访问
 */
@Repository
public class MockDao{

    private static Logger logger = LoggerFactory.getLogger(MockDao.class);

    @Autowired
    JpaTemplate jpaTemplate;

    /**
     * 创建mock
     * @param mockEntity
     * @return
     */
    public String createMock(MockEntity mockEntity) {
        return jpaTemplate.save(mockEntity,String.class);
    }

    /**
     * 更新mock
     * @param mockEntity
     */
    public void updateMock(MockEntity mockEntity){
        jpaTemplate.update(mockEntity);
    }

    /**
     * 删除mock
     * @param id
     */
    public void deleteMock(String id){
        jpaTemplate.delete(MockEntity.class,id);
    }

    /**
     * 通过条件删除
     * @param deleteCondition
     */
    public void deleteMockList(DeleteCondition deleteCondition){
        jpaTemplate.delete(deleteCondition);
    }


    /**
     * 查找mock
     * @param id
     * @return
     */
    public MockEntity findMock(String id){
        return jpaTemplate.findOne(MockEntity.class,id);
    }

    /**
    * 查找所有mock
    * @return
    */
    public List<MockEntity> findAllMock() {
        return jpaTemplate.findAll(MockEntity.class);
    }

    public List<MockEntity> findMockList(List<String> idList) {
        return jpaTemplate.findList(MockEntity.class,idList);
    }

    /**
     * 根据查询条件查找mock列表
     * @param mockQuery
     * @return
     */
    public List<MockEntity> findMockList(MockQuery mockQuery) {
        QueryCondition queryCondition = QueryBuilders.createQuery(MockEntity.class)
                .eq("httpId", mockQuery.getHttpId())
                .like("name", mockQuery.getName())
                .orders(mockQuery.getOrderParams())
                .get();
        return jpaTemplate.findList(queryCondition, MockEntity.class);
    }

    /**
     * 根据查询条件按分页查找mock列表
     * @param mockQuery
     * @return
     */
    public Pagination<MockEntity> findMockPage(MockQuery mockQuery) {
        QueryCondition queryCondition = QueryBuilders.createQuery(MockEntity.class)
                .eq("httpId", mockQuery.getHttpId())
                .like("name", mockQuery.getName())
                .pagination(mockQuery.getPageParam())
                .orders(mockQuery.getOrderParams())
                .get();
        return jpaTemplate.findPage(queryCondition, MockEntity.class);
    }
}