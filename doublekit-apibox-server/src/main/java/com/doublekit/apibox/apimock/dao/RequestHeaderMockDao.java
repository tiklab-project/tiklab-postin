package com.doublekit.apibox.apimock.dao;

import com.doublekit.apibox.apimock.entity.RequestHeaderMockPo;
import com.doublekit.apibox.apimock.model.RequestHeaderMockQuery;
import com.doublekit.common.Pagination;
import com.doublekit.dal.jpa.JpaTemplate;
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
     * @param requestHeaderMockPo
     * @return
     */
    public String createRequestHeaderMock(RequestHeaderMockPo requestHeaderMockPo) {
        return jpaTemplate.save(requestHeaderMockPo,String.class);
    }

    /**
     * 更新用户
     * @param requestHeaderMockPo
     */
    public void updateRequestHeaderMock(RequestHeaderMockPo requestHeaderMockPo){
        jpaTemplate.update(requestHeaderMockPo);
    }

    /**
     * 删除用户
     * @param id
     */
    public void deleteRequestHeaderMock(String id){
        jpaTemplate.delete(RequestHeaderMockPo.class,id);
    }

    /**
     * 查找用户
     * @param id
     * @return
     */
    public RequestHeaderMockPo findRequestHeaderMock(String id){
        return jpaTemplate.findOne(RequestHeaderMockPo.class,id);
    }

    /**
    * findAllRequestHeaderMock
    * @return
    */
    public List<RequestHeaderMockPo> findAllRequestHeaderMock() {
        return jpaTemplate.findAll(RequestHeaderMockPo.class);
    }

    public List<RequestHeaderMockPo> findRequestHeaderMockList(RequestHeaderMockQuery requestHeaderMockQuery) {
        return jpaTemplate.findList(RequestHeaderMockPo.class,requestHeaderMockQuery);
    }

    public Pagination<RequestHeaderMockPo> findRequestHeaderMockPage(RequestHeaderMockQuery requestHeaderMockQuery) {
        return jpaTemplate.findPage(RequestHeaderMockPo.class,requestHeaderMockQuery);
    }
}