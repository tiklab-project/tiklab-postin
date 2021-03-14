package com.darthcloud.apibox.apimock.dao;

import com.darthcloud.apibox.apimock.entity.RawResponseMockPo;
import com.darthcloud.apibox.apimock.model.RawResponseMockQuery;
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
public class RawResponseMockDao{

    private static Logger logger = LoggerFactory.getLogger(RawResponseMockDao.class);

    @Autowired
    JpaTemplate jpaTemplate;

    /**
     * 创建用户
     * @param rawResponseMockPo
     * @return
     */
    public String createRawResponseMock(RawResponseMockPo rawResponseMockPo) {
        return jpaTemplate.save(rawResponseMockPo,String.class);
    }

    /**
     * 更新用户
     * @param rawResponseMockPo
     */
    public void updateRawResponseMock(RawResponseMockPo rawResponseMockPo){
        jpaTemplate.update(rawResponseMockPo);
    }

    /**
     * 删除用户
     * @param id
     */
    public void deleteRawResponseMock(String id){
        jpaTemplate.delete(RawResponseMockPo.class,id);
    }

    /**
     * 查找用户
     * @param id
     * @return
     */
    public RawResponseMockPo findRawResponseMock(String id){
        return jpaTemplate.findOne(RawResponseMockPo.class,id);
    }

    /**
    * findAllRawResponseMock
    * @return
    */
    public List<RawResponseMockPo> findAllRawResponseMock() {
        return jpaTemplate.findAll(RawResponseMockPo.class);
    }

    public List<RawResponseMockPo> findRawResponseMockList(RawResponseMockQuery rawResponseMockQuery) {
        return jpaTemplate.findList(RawResponseMockPo.class,rawResponseMockQuery);
    }

    public Pagination<List<RawResponseMockPo>> findRawResponseMockPage(RawResponseMockQuery rawResponseMockQuery) { 
        return jpaTemplate.findPage(RawResponseMockPo.class,rawResponseMockQuery);
    }
}