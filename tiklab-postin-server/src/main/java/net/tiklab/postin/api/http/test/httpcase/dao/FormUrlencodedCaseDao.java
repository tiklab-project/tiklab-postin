package net.tiklab.postin.api.http.test.httpcase.dao;

import net.tiklab.postin.api.http.test.httpcase.entity.FormUrlencodedCaseEntity;
import net.tiklab.postin.api.http.test.httpcase.model.FormUrlencodedCaseQuery;
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
 * FormUrlencodedCaseDao
 */
@Repository
public class FormUrlencodedCaseDao{

    private static Logger logger = LoggerFactory.getLogger(FormUrlencodedCaseDao.class);

    @Autowired
    JpaTemplate jpaTemplate;

    /**
     * 创建
     * @param formUrlencodedCaseEntity
     * @return
     */
    public String createFormUrlencodedCase(FormUrlencodedCaseEntity formUrlencodedCaseEntity) {
        return jpaTemplate.save(formUrlencodedCaseEntity,String.class);
    }

    /**
     * 更新
     * @param formUrlencodedCaseEntity
     */
    public void updateFormUrlencodedCase(FormUrlencodedCaseEntity formUrlencodedCaseEntity){
        jpaTemplate.update(formUrlencodedCaseEntity);
    }

    /**
     * 删除
     * @param id
     */
    public void deleteFormUrlencodedCase(String id){
        jpaTemplate.delete(FormUrlencodedCaseEntity.class,id);
    }

    public void deleteFormUrlencodedCase(DeleteCondition deleteCondition){
        jpaTemplate.delete(deleteCondition);
    }

    /**
     * 查找
     * @param id
     * @return
     */
    public FormUrlencodedCaseEntity findFormUrlencodedCase(String id){
        return jpaTemplate.findOne(FormUrlencodedCaseEntity.class,id);
    }

    /**
    * findAllFormUrlencodedCase
    * @return
    */
    public List<FormUrlencodedCaseEntity> findAllFormUrlencodedCase() {
        return jpaTemplate.findAll(FormUrlencodedCaseEntity.class);
    }

    public List<FormUrlencodedCaseEntity> findFormUrlencodedCaseList(List<String> idList) {
        return jpaTemplate.findList(FormUrlencodedCaseEntity.class,idList);
    }

    public List<FormUrlencodedCaseEntity> findFormUrlencodedCaseList(FormUrlencodedCaseQuery formUrlencodedCaseQuery) {
        QueryCondition queryCondition = QueryBuilders.createQuery(FormUrlencodedCaseEntity.class)
                .eq("httpCaseId", formUrlencodedCaseQuery.getHttpCaseId())
                .orders(formUrlencodedCaseQuery.getOrderParams())
                .get();
        return jpaTemplate.findList(queryCondition,FormUrlencodedCaseEntity.class);
    }

    public Pagination<FormUrlencodedCaseEntity> findFormUrlencodedCasePage(FormUrlencodedCaseQuery formUrlencodedCaseQuery) {
        QueryCondition queryCondition = QueryBuilders.createQuery(FormUrlencodedCaseEntity.class)
                .eq("httpCaseId", formUrlencodedCaseQuery.getHttpCaseId())
                .pagination(formUrlencodedCaseQuery.getPageParam())
                .orders(formUrlencodedCaseQuery.getOrderParams())
                .get();
        return jpaTemplate.findPage(queryCondition,FormUrlencodedCaseEntity.class);
    }
}