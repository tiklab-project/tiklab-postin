package com.darthcloud.apibox.api.apimock.dao;

import com.darthcloud.common.Pagination;
import com.darthcloud.apibox.api.apimock.entity.FormParamMockPo;
import com.darthcloud.apibox.api.apimock.model.FormParamMockQuery;
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
public class FormParamMockDao{

    private static Logger logger = LoggerFactory.getLogger(FormParamMockDao.class);

    @Autowired
    JpaTemplate jpaTemplate;

    /**
     * 创建用户
     * @param formParamMockPo
     * @return
     */
    public String createFormParamMock(FormParamMockPo formParamMockPo) {
        return jpaTemplate.save(formParamMockPo,String.class);
    }

    /**
     * 更新用户
     * @param formParamMockPo
     */
    public void updateFormParamMock(FormParamMockPo formParamMockPo){
        jpaTemplate.update(formParamMockPo);
    }

    /**
     * 删除用户
     * @param id
     */
    public void deleteFormParamMock(String id){
        jpaTemplate.delete(FormParamMockPo.class,id);
    }

    /**
     * 查找用户
     * @param id
     * @return
     */
    public FormParamMockPo findFormParamMock(String id){
        return jpaTemplate.findOne(FormParamMockPo.class,id);
    }

    /**
    * findAllFormParamMock
    * @return
    */
    public List<FormParamMockPo> findAllFormParamMock() {
        return jpaTemplate.findAll(FormParamMockPo.class);
    }

    public List<FormParamMockPo> findFormParamMockList(FormParamMockQuery formParamMockQuery) {
        return jpaTemplate.createCriteria(FormParamMockPo.class).params(formParamMockQuery).list();
    }

    public Pagination<List<FormParamMockPo>> findFormParamMockPage(FormParamMockQuery formParamMockQuery) { 
        return jpaTemplate.createCriteria(FormParamMockPo.class).params(formParamMockQuery).page();
    }
}