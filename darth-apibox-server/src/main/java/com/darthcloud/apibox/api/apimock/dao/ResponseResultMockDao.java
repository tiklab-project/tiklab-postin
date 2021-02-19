package com.darthcloud.apibox.api.apimock.dao;

import com.darthcloud.common.Pagination;
import com.darthcloud.apibox.api.apimock.entity.ResponseResultMockPo;
import com.darthcloud.apibox.api.apimock.model.ResponseResultMockQuery;
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
public class ResponseResultMockDao{

    private static Logger logger = LoggerFactory.getLogger(ResponseResultMockDao.class);

    @Autowired
    JpaTemplate jpaTemplate;

    /**
     * 创建用户
     * @param responseResultMockPo
     * @return
     */
    public String createResponseResultMock(ResponseResultMockPo responseResultMockPo) {
        return jpaTemplate.save(responseResultMockPo,String.class);
    }

    /**
     * 更新用户
     * @param responseResultMockPo
     */
    public void updateResponseResultMock(ResponseResultMockPo responseResultMockPo){
        jpaTemplate.update(responseResultMockPo);
    }

    /**
     * 删除用户
     * @param id
     */
    public void deleteResponseResultMock(String id){
        jpaTemplate.delete(ResponseResultMockPo.class,id);
    }

    /**
     * 查找用户
     * @param id
     * @return
     */
    public ResponseResultMockPo findResponseResultMock(String id){
        return jpaTemplate.findOne(ResponseResultMockPo.class,id);
    }

    /**
    * findAllResponseResultMock
    * @return
    */
    public List<ResponseResultMockPo> findAllResponseResultMock() {
        return jpaTemplate.findAll(ResponseResultMockPo.class);
    }

    public List<ResponseResultMockPo> findResponseResultMockList(ResponseResultMockQuery responseResultMockQuery) {
        return jpaTemplate.createCriteria(ResponseResultMockPo.class).params(responseResultMockQuery).list();
    }

    public Pagination<List<ResponseResultMockPo>> findResponseResultMockPage(ResponseResultMockQuery responseResultMockQuery) { 
        return jpaTemplate.createCriteria(ResponseResultMockPo.class).params(responseResultMockQuery).page();
    }
}