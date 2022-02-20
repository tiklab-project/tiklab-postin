package com.doublekit.apibox.apidef.dao;

import com.doublekit.apibox.apidef.entity.FormUrlencodedEntity;
import com.doublekit.apibox.apidef.model.FormUrlencodedQuery;
import com.doublekit.common.page.Pagination;
import com.doublekit.dal.jpa.JpaTemplate;
import com.doublekit.dal.jpa.criterial.condition.DeleteCondition;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * FormUrlencodedDao
 */
@Repository
public class FormUrlencodedDao{

    private static Logger logger = LoggerFactory.getLogger(FormUrlencodedDao.class);

    @Autowired
    JpaTemplate jpaTemplate;

    /**
     * 创建
     * @param formUrlencodedEntity
     * @return
     */
    public String createFormUrlencoded(FormUrlencodedEntity formUrlencodedEntity) {
        return jpaTemplate.save(formUrlencodedEntity,String.class);
    }

    /**
     * 更新
     * @param formUrlencodedEntity
     */
    public void updateFormUrlencoded(FormUrlencodedEntity formUrlencodedEntity){
        jpaTemplate.update(formUrlencodedEntity);
    }

    /**
     * 删除
     * @param id
     */
    public void deleteFormUrlencoded(String id){
        jpaTemplate.delete(FormUrlencodedEntity.class,id);
    }

    public void deleteFormUrlencoded(DeleteCondition deleteCondition){
        jpaTemplate.delete(deleteCondition);
    }

    /**
     * 查找
     * @param id
     * @return
     */
    public FormUrlencodedEntity findFormUrlencoded(String id){
        return jpaTemplate.findOne(FormUrlencodedEntity.class,id);
    }

    /**
    * findAllFormUrlencoded
    * @return
    */
    public List<FormUrlencodedEntity> findAllFormUrlencoded() {
        return jpaTemplate.findAll(FormUrlencodedEntity.class);
    }

    public List<FormUrlencodedEntity> findFormUrlencodedList(List<String> idList) {
        return jpaTemplate.findList(FormUrlencodedEntity.class,idList);
    }

    public List<FormUrlencodedEntity> findFormUrlencodedList(FormUrlencodedQuery formUrlencodedQuery) {
        return jpaTemplate.findList(formUrlencodedQuery,FormUrlencodedEntity.class);
    }

    public Pagination<FormUrlencodedEntity> findFormUrlencodedPage(FormUrlencodedQuery formUrlencodedQuery) {
        return jpaTemplate.findPage(formUrlencodedQuery,FormUrlencodedEntity.class);
    }
}