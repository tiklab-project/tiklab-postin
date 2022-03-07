package com.doublekit.apibox.apimock.dao;

import com.doublekit.apibox.apimock.entity.FormParamMockEntity;
import com.doublekit.apibox.apimock.model.FormParamMockQuery;
import com.doublekit.common.page.Pagination;
import com.doublekit.dal.jpa.JpaTemplate;
import com.doublekit.dal.jpa.criterial.condition.QueryCondition;
import com.doublekit.dal.jpa.criterial.conditionbuilder.QueryBuilders;
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
     * @param formParamMockEntity
     * @return
     */
    public String createFormParamMock(FormParamMockEntity formParamMockEntity) {
        return jpaTemplate.save(formParamMockEntity,String.class);
    }

    /**
     * 更新用户
     * @param formParamMockEntity
     */
    public void updateFormParamMock(FormParamMockEntity formParamMockEntity){
        jpaTemplate.update(formParamMockEntity);
    }

    /**
     * 删除用户
     * @param id
     */
    public void deleteFormParamMock(String id){
        jpaTemplate.delete(FormParamMockEntity.class,id);
    }

    /**
     * 查找用户
     * @param id
     * @return
     */
    public FormParamMockEntity findFormParamMock(String id){
        return jpaTemplate.findOne(FormParamMockEntity.class,id);
    }

    /**
    * findAllFormParamMock
    * @return
    */
    public List<FormParamMockEntity> findAllFormParamMock() {
        return jpaTemplate.findAll(FormParamMockEntity.class);
    }

    public List<FormParamMockEntity> findFormParamMockList(FormParamMockQuery formParamMockQuery) {
        QueryCondition queryCondition = QueryBuilders.createQuery(FormParamMockEntity.class)
                .eq("mockId", formParamMockQuery.getMockId())
                .orders(formParamMockQuery.getOrderParams())
                .get();
        return jpaTemplate.findList(queryCondition, FormParamMockEntity.class);
    }

    public Pagination<FormParamMockEntity> findFormParamMockPage(FormParamMockQuery formParamMockQuery) {
        QueryCondition queryCondition = QueryBuilders.createQuery(FormParamMockEntity.class)
                .eq("mockId", formParamMockQuery.getMockId())
                .pagination(formParamMockQuery.getPageParam())
                .orders(formParamMockQuery.getOrderParams())
                .get();
        return jpaTemplate.findPage(queryCondition, FormParamMockEntity.class);
    }
}