package com.darthcloud.apibox.apimock.dao;

import com.darthcloud.apibox.apimock.entity.ResponseHeaderMockPo;
import com.darthcloud.apibox.apimock.model.ResponseHeaderMockQuery;
import com.darthcloud.common.Pagination;
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
public class ResponseHeaderMockDao{

    private static Logger logger = LoggerFactory.getLogger(ResponseHeaderMockDao.class);

    @Autowired
    JpaTemplate jpaTemplate;

    /**
     * 创建用户
     * @param responseHeaderMockPo
     * @return
     */
    public String createResponseHeaderMock(ResponseHeaderMockPo responseHeaderMockPo) {
        return jpaTemplate.save(responseHeaderMockPo,String.class);
    }

    /**
     * 更新用户
     * @param responseHeaderMockPo
     */
    public void updateResponseHeaderMock(ResponseHeaderMockPo responseHeaderMockPo){
        jpaTemplate.update(responseHeaderMockPo);
    }

    /**
     * 删除用户
     * @param id
     */
    public void deleteResponseHeaderMock(String id){
        jpaTemplate.delete(ResponseHeaderMockPo.class,id);
    }

    /**
     * 查找用户
     * @param id
     * @return
     */
    public ResponseHeaderMockPo findResponseHeaderMock(String id){
        return jpaTemplate.findOne(ResponseHeaderMockPo.class,id);
    }

    /**
    * findAllResponseHeaderMock
    * @return
    */
    public List<ResponseHeaderMockPo> findAllResponseHeaderMock() {
        return jpaTemplate.findAll(ResponseHeaderMockPo.class);
    }

    public List<ResponseHeaderMockPo> findResponseHeaderMockList(ResponseHeaderMockQuery responseHeaderMockQuery) {
        return jpaTemplate.findList(ResponseHeaderMockPo.class,responseHeaderMockQuery);
    }

    public Pagination<List<ResponseHeaderMockPo>> findResponseHeaderMockPage(ResponseHeaderMockQuery responseHeaderMockQuery) { 
        return jpaTemplate.findPage(ResponseHeaderMockPo.class,responseHeaderMockQuery);
    }
}