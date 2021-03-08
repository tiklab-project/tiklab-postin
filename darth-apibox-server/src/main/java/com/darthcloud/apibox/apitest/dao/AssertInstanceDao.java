package com.darthcloud.apibox.apitest.dao;

import com.darthcloud.common.Pagination;
import com.darthcloud.apibox.apitest.entity.AssertInstancePo;
import com.darthcloud.apibox.apitest.model.AssertInstanceQuery;
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
public class AssertInstanceDao{

    private static Logger logger = LoggerFactory.getLogger(AssertInstanceDao.class);

    @Autowired
    JpaTemplate jpaTemplate;

    /**
     * 创建用户
     * @param assertInstancePo
     * @return
     */
    public String createAssertInstance(AssertInstancePo assertInstancePo) {
        return jpaTemplate.save(assertInstancePo,String.class);
    }

    /**
     * 更新用户
     * @param assertInstancePo
     */
    public void updateAssertInstance(AssertInstancePo assertInstancePo){
        jpaTemplate.update(assertInstancePo);
    }

    /**
     * 删除用户
     * @param id
     */
    public void deleteAssertInstance(String id){
        jpaTemplate.delete(AssertInstancePo.class,id);
    }

    /**
     * 查找用户
     * @param id
     * @return
     */
    public AssertInstancePo findAssertInstance(String id){
        return jpaTemplate.findOne(AssertInstancePo.class,id);
    }

    /**
    * findAllAssertInstance
    * @return
    */
    public List<AssertInstancePo> findAllAssertInstance() {
        return jpaTemplate.findAll(AssertInstancePo.class);
    }

    public List<AssertInstancePo> findAssertInstanceList(List<String> idList) {
        return jpaTemplate.findList(AssertInstancePo.class,idList);
    }

    public List<AssertInstancePo> findAssertInstanceList(AssertInstanceQuery assertInstanceQuery) {
        return jpaTemplate.createCriteria(AssertInstancePo.class).params(assertInstanceQuery).list();
    }

    public Pagination<List<AssertInstancePo>> findAssertInstancePage(AssertInstanceQuery assertInstanceQuery) { 
        return jpaTemplate.createCriteria(AssertInstancePo.class).params(assertInstanceQuery).page();
    }
}