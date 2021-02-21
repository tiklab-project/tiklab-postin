package com.darthcloud.apibox.apimock.dao;

import com.darthcloud.common.Pagination;
import com.darthcloud.apibox.apimock.entity.MockPo;
import com.darthcloud.apibox.apimock.model.MockQuery;
import com.darthcloud.dal.jpa.JpaTemplate;
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
     * @param mockPo
     * @return
     */
    public String createMock(MockPo mockPo) {
        return jpaTemplate.save(mockPo,String.class);
    }

    /**
     * 更新用户
     * @param mockPo
     */
    public void updateMock(MockPo mockPo){
        jpaTemplate.update(mockPo);
    }

    /**
     * 删除用户
     * @param id
     */
    public void deleteMock(String id){
        jpaTemplate.delete(MockPo.class,id);
    }

    /**
     * 查找用户
     * @param id
     * @return
     */
    public MockPo findMock(String id){
        return jpaTemplate.findOne(MockPo.class,id);
    }

    /**
    * findAllMock
    * @return
    */
    public List<MockPo> findAllMock() {
        return jpaTemplate.findAll(MockPo.class);
    }

    public List<MockPo> findMockList(MockQuery mockQuery) {
        return jpaTemplate.createCriteria(MockPo.class).params(mockQuery).list();
    }

    public Pagination<List<MockPo>> findMockPage(MockQuery mockQuery) { 
        return jpaTemplate.createCriteria(MockPo.class).params(mockQuery).page();
    }
}