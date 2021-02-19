package com.darthcloud.apibox.api.apimock.dao;

import com.darthcloud.common.Pagination;
import com.darthcloud.apibox.api.apimock.entity.ResponseMockPo;
import com.darthcloud.apibox.api.apimock.model.ResponseMockQuery;
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
public class ResponseMockDao{

    private static Logger logger = LoggerFactory.getLogger(ResponseMockDao.class);

    @Autowired
    JpaTemplate jpaTemplate;

    /**
     * 创建用户
     * @param responseMockPo
     * @return
     */
    public String createResponseMock(ResponseMockPo responseMockPo) {
        return jpaTemplate.save(responseMockPo,String.class);
    }

    /**
     * 更新用户
     * @param responseMockPo
     */
    public void updateResponseMock(ResponseMockPo responseMockPo){
        jpaTemplate.update(responseMockPo);
    }

    /**
     * 删除用户
     * @param id
     */
    public void deleteResponseMock(String id){
        jpaTemplate.delete(ResponseMockPo.class,id);
    }

    /**
     * 查找用户
     * @param id
     * @return
     */
    public ResponseMockPo findResponseMock(String id){
        return jpaTemplate.findOne(ResponseMockPo.class,id);
    }

    /**
    * findAllResponseMock
    * @return
    */
    public List<ResponseMockPo> findAllResponseMock() {
        return jpaTemplate.findAll(ResponseMockPo.class);
    }

    public List<ResponseMockPo> findResponseMockList(ResponseMockQuery responseMockQuery) {
        return jpaTemplate.createCriteria(ResponseMockPo.class).params(responseMockQuery).list();
    }

    public Pagination<List<ResponseMockPo>> findResponseMockPage(ResponseMockQuery responseMockQuery) { 
        return jpaTemplate.createCriteria(ResponseMockPo.class).params(responseMockQuery).page();
    }
}