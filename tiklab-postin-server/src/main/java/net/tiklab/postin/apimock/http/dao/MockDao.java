package net.tiklab.postin.apimock.http.dao;

import net.tiklab.postin.apimock.http.entity.MockEntity;
import net.tiklab.postin.apimock.http.model.MockQuery;
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
public class MockDao{

    private static Logger logger = LoggerFactory.getLogger(MockDao.class);

    @Autowired
    JpaTemplate jpaTemplate;

    /**
     * 创建用户
     * @param mockEntity
     * @return
     */
    public String createMock(MockEntity mockEntity) {
        return jpaTemplate.save(mockEntity,String.class);
    }

    /**
     * 更新用户
     * @param mockEntity
     */
    public void updateMock(MockEntity mockEntity){
        jpaTemplate.update(mockEntity);
    }

    /**
     * 删除用户
     * @param id
     */
    public void deleteMock(String id){
        jpaTemplate.delete(MockEntity.class,id);
    }

    /**
     * 查找用户
     * @param id
     * @return
     */
    public MockEntity findMock(String id){
        return jpaTemplate.findOne(MockEntity.class,id);
    }

    /**
    * findAllMock
    * @return
    */
    public List<MockEntity> findAllMock() {
        return jpaTemplate.findAll(MockEntity.class);
    }

    public List<MockEntity> findMockList(List<String> idList) {
        return jpaTemplate.findList(MockEntity.class,idList);
    }

    public List<MockEntity> findMockList(MockQuery mockQuery) {
        QueryCondition queryCondition = QueryBuilders.createQuery(MockEntity.class)
                .eq("httpId", mockQuery.getHttpId())
                .like("name", mockQuery.getName())
                .orders(mockQuery.getOrderParams())
                .get();
        return jpaTemplate.findList(queryCondition, MockEntity.class);
    }

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