package com.darthcloud.apibox.apimock.dao;

import com.darthcloud.common.Pagination;
import com.darthcloud.apibox.apimock.entity.RequestBodyMockPo;
import com.darthcloud.apibox.apimock.model.RequestBodyMockQuery;
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
public class RequestBodyMockDao{

    private static Logger logger = LoggerFactory.getLogger(RequestBodyMockDao.class);

    @Autowired
    JpaTemplate jpaTemplate;

    /**
     * 创建用户
     * @param requestBodyMockPo
     * @return
     */
    public String createRequestBodyMock(RequestBodyMockPo requestBodyMockPo) {
        return jpaTemplate.save(requestBodyMockPo,String.class);
    }

    /**
     * 更新用户
     * @param requestBodyMockPo
     */
    public void updateRequestBodyMock(RequestBodyMockPo requestBodyMockPo){
        jpaTemplate.update(requestBodyMockPo);
    }

    /**
     * 删除用户
     * @param id
     */
    public void deleteRequestBodyMock(String id){
        jpaTemplate.delete(RequestBodyMockPo.class,id);
    }

    /**
     * 查找用户
     * @param id
     * @return
     */
    public RequestBodyMockPo findRequestBodyMock(String id){
        return jpaTemplate.findOne(RequestBodyMockPo.class,id);
    }

    /**
    * findAllRequestBodyMock
    * @return
    */
    public List<RequestBodyMockPo> findAllRequestBodyMock() {
        return jpaTemplate.findAll(RequestBodyMockPo.class);
    }

    public List<RequestBodyMockPo> findRequestBodyMockList(List<String> idList) {
        return jpaTemplate.findList(RequestBodyMockPo.class,idList);
    }

    public List<RequestBodyMockPo> findRequestBodyMockList(RequestBodyMockQuery requestBodyMockQuery) {
        return jpaTemplate.createCriteria(RequestBodyMockPo.class).params(requestBodyMockQuery).list();
    }

    public Pagination<List<RequestBodyMockPo>> findRequestBodyMockPage(RequestBodyMockQuery requestBodyMockQuery) { 
        return jpaTemplate.createCriteria(RequestBodyMockPo.class).params(requestBodyMockQuery).page();
    }
}