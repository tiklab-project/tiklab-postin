package net.tiklab.postin.apidef.http.dao;

import net.tiklab.postin.apidef.http.entity.FormUrlencodedEntity;
import net.tiklab.postin.apidef.http.model.FormUrlencodedQuery;
import net.tiklab.core.page.Pagination;
import net.tiklab.dal.jpa.JpaTemplate;
import net.tiklab.dal.jpa.criterial.condition.DeleteCondition;
import net.tiklab.dal.jpa.criterial.condition.QueryCondition;
import net.tiklab.dal.jpa.criterial.conditionbuilder.QueryBuilders;
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
        QueryCondition queryCondition = QueryBuilders.createQuery(FormUrlencodedEntity.class)
                .eq("httpId", formUrlencodedQuery.getHttpId())
                .orders(formUrlencodedQuery.getOrderParams())
                .get();
        return jpaTemplate.findList(queryCondition,FormUrlencodedEntity.class);
    }

    public Pagination<FormUrlencodedEntity> findFormUrlencodedPage(FormUrlencodedQuery formUrlencodedQuery) {
        QueryCondition queryCondition = QueryBuilders.createQuery(FormUrlencodedEntity.class)
                .eq("httpId", formUrlencodedQuery.getHttpId())
                .pagination(formUrlencodedQuery.getPageParam())
                .orders(formUrlencodedQuery.getOrderParams())
                .get();
        return jpaTemplate.findPage(queryCondition,FormUrlencodedEntity.class);
    }
}