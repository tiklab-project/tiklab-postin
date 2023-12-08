package io.thoughtware.postin.api.http.test.cases.dao;

import io.thoughtware.postin.api.http.test.cases.entity.AssertCasesEntity;
import io.thoughtware.postin.api.http.test.cases.model.AssertCaseQuery;
import io.thoughtware.core.page.Pagination;
import io.thoughtware.dal.jpa.JpaTemplate;
import io.thoughtware.dal.jpa.criterial.condition.QueryCondition;
import io.thoughtware.dal.jpa.criterial.conditionbuilder.QueryBuilders;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 用户数据操作
 */
@Repository
public class AssertCaseDao{

    private static Logger logger = LoggerFactory.getLogger(AssertCaseDao.class);

    @Autowired
    JpaTemplate jpaTemplate;

    /**
     * 创建用户
     * @param assertCasesEntity
     * @return
     */
    public String createAssertCase(AssertCasesEntity assertCasesEntity) {
        return jpaTemplate.save(assertCasesEntity,String.class);
    }

    /**
     * 更新用户
     * @param assertCasesEntity
     */
    public void updateAssertCase(AssertCasesEntity assertCasesEntity){
        jpaTemplate.update(assertCasesEntity);
    }

    /**
     * 删除用户
     * @param id
     */
    public void deleteAssertCase(String id){
        jpaTemplate.delete(AssertCasesEntity.class,id);
    }

    /**
     * 查找用户
     * @param id
     * @return
     */
    public AssertCasesEntity findAssertCase(String id){
        return jpaTemplate.findOne(AssertCasesEntity.class,id);
    }

    /**
    * findAllAssertCase
    * @return
    */
    public List<AssertCasesEntity> findAllAssertCase() {
        return jpaTemplate.findAll(AssertCasesEntity.class);
    }

    public List<AssertCasesEntity> findAssertCaseList(List<String> idList) {
        return jpaTemplate.findList(AssertCasesEntity.class,idList);
    }

    public List<AssertCasesEntity> findAssertCaseList(AssertCaseQuery assertCaseQuery) {
        QueryCondition queryCondition = QueryBuilders.createQuery(AssertCasesEntity.class)
                .eq("httpCaseId", assertCaseQuery.getHttpCaseId())
                .orders(assertCaseQuery.getOrderParams())
                .get();
        return jpaTemplate.findList(queryCondition, AssertCasesEntity.class);
    }

    public Pagination<AssertCasesEntity> findAssertCasePage(AssertCaseQuery assertCaseQuery) {
        QueryCondition queryCondition = QueryBuilders.createQuery(AssertCasesEntity.class)
                .eq("httpCaseId", assertCaseQuery.getHttpCaseId())
                .pagination(assertCaseQuery.getPageParam())
                .orders(assertCaseQuery.getOrderParams())
                .get();
        return jpaTemplate.findPage(queryCondition, AssertCasesEntity.class);
    }
}