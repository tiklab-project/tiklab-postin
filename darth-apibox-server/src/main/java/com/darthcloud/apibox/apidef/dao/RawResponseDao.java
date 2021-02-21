package com.darthcloud.apibox.apidef.dao;

import com.darthcloud.common.Pagination;
import com.darthcloud.apibox.apidef.entity.RawResponsePo;
import com.darthcloud.apibox.apidef.model.RawResponseQuery;
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
public class RawResponseDao{

    private static Logger logger = LoggerFactory.getLogger(RawResponseDao.class);

    @Autowired
    JpaTemplate jpaTemplate;

    /**
     * 创建用户
     * @param rawResponsePo
     * @return
     */
    public String createRawResponse(RawResponsePo rawResponsePo) {
        return jpaTemplate.save(rawResponsePo,String.class);
    }

    /**
     * 更新用户
     * @param rawResponsePo
     */
    public void updateRawResponse(RawResponsePo rawResponsePo){
        jpaTemplate.update(rawResponsePo);
    }

    /**
     * 删除用户
     * @param id
     */
    public void deleteRawResponse(String id){
        jpaTemplate.delete(RawResponsePo.class,id);
    }

    /**
     * 查找用户
     * @param id
     * @return
     */
    public RawResponsePo findRawResponse(String id){
        return jpaTemplate.findOne(RawResponsePo.class,id);
    }

    /**
    * findAllRawResponse
    * @return
    */
    public List<RawResponsePo> findAllRawResponse() {
        return jpaTemplate.findAll(RawResponsePo.class);
    }

    public List<RawResponsePo> findRawResponseList(RawResponseQuery rawResponseQuery) {
        return jpaTemplate.createCriteria(RawResponsePo.class).params(rawResponseQuery).list();
    }

    public Pagination<List<RawResponsePo>> findRawResponsePage(RawResponseQuery rawResponseQuery) { 
        return jpaTemplate.createCriteria(RawResponsePo.class).params(rawResponseQuery).page();
    }
}