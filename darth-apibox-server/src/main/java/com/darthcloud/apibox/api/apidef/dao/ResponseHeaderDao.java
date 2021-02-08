package com.darthcloud.apibox.api.apidef.dao;

import com.darthcloud.common.Pagination;
import com.darthcloud.apibox.api.apidef.entity.ResponseHeaderPo;
import com.darthcloud.apibox.api.apidef.model.ResponseHeaderQuery;
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
public class ResponseHeaderDao{

    private static Logger logger = LoggerFactory.getLogger(ResponseHeaderDao.class);

    @Autowired
    JpaTemplate jpaTemplate;

    /**
     * 创建用户
     * @param responseHeaderPo
     * @return
     */
    public String createResponseHeader(ResponseHeaderPo responseHeaderPo) {
        return jpaTemplate.save(responseHeaderPo,String.class);
    }

    /**
     * 更新用户
     * @param responseHeaderPo
     */
    public void updateResponseHeader(ResponseHeaderPo responseHeaderPo){
        jpaTemplate.update(responseHeaderPo);
    }

    /**
     * 删除用户
     * @param id
     */
    public void deleteResponseHeader(String id){
        jpaTemplate.delete(ResponseHeaderPo.class,id);
    }

    /**
     * 查找用户
     * @param id
     * @return
     */
    public ResponseHeaderPo findResponseHeader(String id){
        return jpaTemplate.findOne(ResponseHeaderPo.class,id);
    }

    /**
    * findAllResponseHeader
    * @return
    */
    public List<ResponseHeaderPo> findAllResponseHeader() {
        return jpaTemplate.findAll(ResponseHeaderPo.class);
    }

    public List<ResponseHeaderPo> findResponseHeaderList(ResponseHeaderQuery responseHeaderQuery) {
        return jpaTemplate.createCriteria(ResponseHeaderPo.class).params(responseHeaderQuery).list();
    }

    public Pagination<List<ResponseHeaderPo>> findResponseHeaderPage(ResponseHeaderQuery responseHeaderQuery) { 
        return jpaTemplate.createCriteria(ResponseHeaderPo.class).params(responseHeaderQuery).page();
    }
}