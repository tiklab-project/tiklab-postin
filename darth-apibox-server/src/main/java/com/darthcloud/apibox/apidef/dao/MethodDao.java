package com.darthcloud.apibox.apidef.dao;

import com.darthcloud.apibox.apidef.entity.MethodPo;
import com.darthcloud.apibox.apidef.model.MethodExQuery;
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
public class MethodDao {

    private static Logger logger = LoggerFactory.getLogger(MethodDao.class);

    @Autowired
    JpaTemplate jpaTemplate;

    /**
     * 创建用户
     * @param methodExPo
     * @return
     */
    public String createMethod(MethodPo methodExPo) {
        return jpaTemplate.save(methodExPo,String.class);
    }

    /**
     * 更新用户
     * @param methodExPo
     */
    public void updateMethod(MethodPo methodExPo){
        jpaTemplate.update(methodExPo);
    }

    /**
     * 删除用户
     * @param id
     */
    public void deleteMethod(String id){
        jpaTemplate.delete(MethodPo.class,id);
    }

    /**
     * 查找用户
     * @param id
     * @return
     */
    public MethodPo findMethod(String id){
        return jpaTemplate.findOne(MethodPo.class,id);
    }

    /**
    * findAllApxMethod
    * @return
    */
    public List<MethodPo> findAllMethod() {
        return jpaTemplate.findAll(MethodPo.class);
    }

    public List<MethodPo> findMethodList(MethodExQuery methodExQuery) {
        return jpaTemplate.createCriteria(MethodPo.class).params(methodExQuery).list();
    }

    public Pagination<List<MethodPo>> findMethodPage(MethodExQuery methodExQuery) {
        return jpaTemplate.createCriteria(MethodPo.class).params(methodExQuery).page();
    }
}