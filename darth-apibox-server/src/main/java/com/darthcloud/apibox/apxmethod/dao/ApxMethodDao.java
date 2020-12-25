package com.darthcloud.apibox.apxmethod.dao;

import com.darthcloud.common.Pagination;
import com.darthcloud.apibox.apxmethod.entity.ApxMethodPo;
import com.darthcloud.apibox.apxmethod.model.ApxMethodQuery;
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
public class ApxMethodDao{

    private static Logger logger = LoggerFactory.getLogger(ApxMethodDao.class);

    @Autowired
    JpaTemplate jpaTemplate;

    /**
     * 创建用户
     * @param apxMethodPo
     * @return
     */
    public String createApxMethod(ApxMethodPo apxMethodPo) {
        return jpaTemplate.save(apxMethodPo,String.class);
    }

    /**
     * 更新用户
     * @param apxMethodPo
     */
    public void updateApxMethod(ApxMethodPo apxMethodPo){
        jpaTemplate.update(apxMethodPo);
    }

    /**
     * 删除用户
     * @param id
     */
    public void deleteApxMethod(String id){
        jpaTemplate.delete(ApxMethodPo.class,id);
    }

    /**
     * 查找用户
     * @param id
     * @return
     */
    public ApxMethodPo findApxMethod(String id){
        return jpaTemplate.findOne(ApxMethodPo.class,id);
    }

    /**
    * findAllApxMethod
    * @return
    */
    public List<ApxMethodPo> findAllApxMethod() {
        return jpaTemplate.findAll(ApxMethodPo.class);
    }

    public List<ApxMethodPo> findApxMethodList(ApxMethodQuery apxMethodQuery) {
        return jpaTemplate.criteriaQuery(ApxMethodPo.class,apxMethodQuery);
    }

    public Pagination<List<ApxMethodPo>> findApxMethodPage(ApxMethodQuery apxMethodQuery) { 
        return jpaTemplate.criteriaPageQuery(ApxMethodPo.class,apxMethodQuery);
    }
}