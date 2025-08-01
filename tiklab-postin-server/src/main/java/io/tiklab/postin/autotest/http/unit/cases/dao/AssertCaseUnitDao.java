package io.tiklab.postin.autotest.http.unit.cases.dao;

import io.tiklab.core.page.Pagination;
import io.tiklab.dal.jpa.criterial.condition.DeleteCondition;
import io.tiklab.dal.jpa.criterial.condition.QueryCondition;
import io.tiklab.dal.jpa.criterial.conditionbuilder.QueryBuilders;
import io.tiklab.dal.jpa.JpaTemplate;
import io.tiklab.postin.autotest.http.unit.cases.entity.AssertCaseUnitEntity;
import io.tiklab.postin.autotest.http.unit.cases.model.AssertUnitQuery;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 断言 数据访问
 */
@Repository
public class AssertCaseUnitDao {

    private static Logger logger = LoggerFactory.getLogger(AssertCaseUnitDao.class);

    @Autowired
    JpaTemplate jpaTemplate;

    /**
     * 创建断言
     * @param assertCaseUnitEntity
     * @return
     */
    public String createAssertCase(AssertCaseUnitEntity assertCaseUnitEntity) {
        return jpaTemplate.save(assertCaseUnitEntity,String.class);
    }

    /**
     * 更新断言
     * @param assertCaseUnitEntity
     */
    public void updateAssertCase(AssertCaseUnitEntity assertCaseUnitEntity){
        jpaTemplate.update(assertCaseUnitEntity);
    }

    /**
     * 删除断言
     * @param id
     */
    public void deleteAssertCase(String id){
        jpaTemplate.delete(AssertCaseUnitEntity.class,id);
    }

    public void deleteAssertCase(DeleteCondition deleteCondition){
        jpaTemplate.delete(deleteCondition);
    }

    /**
     * 通过id查找断言
     * @param id
     * @return
     */
    public AssertCaseUnitEntity findAssertCase(String id){
        return jpaTemplate.findOne(AssertCaseUnitEntity.class,id);
    }

    /**
    * 查找所有断言
    * @return
    */
    public List<AssertCaseUnitEntity> findAllAssertCase() {
        return jpaTemplate.findAll(AssertCaseUnitEntity.class);
    }

    public List<AssertCaseUnitEntity> findAssertCaseList(List<String> idList) {
        return jpaTemplate.findList(AssertCaseUnitEntity.class,idList);
    }

    /**
     * 查询断言列表
     * @param assertUnitQuery
     * @return
     */
    public List<AssertCaseUnitEntity> findAssertCaseList(AssertUnitQuery assertUnitQuery) {
        QueryCondition queryCondition = QueryBuilders.createQuery(AssertCaseUnitEntity.class)
                .eq("apiUnitId", assertUnitQuery.getApiUnitId())
                .orders(assertUnitQuery.getOrderParams())
                .get();
        return jpaTemplate.findList(queryCondition, AssertCaseUnitEntity.class);
    }

    /**
     * 按分页查询断言
     * @param assertUnitQuery
     * @return
     */
    public Pagination<AssertCaseUnitEntity> findAssertCasePage(AssertUnitQuery assertUnitQuery) {
        QueryCondition queryCondition = QueryBuilders.createQuery(AssertCaseUnitEntity.class)
                .eq("apiUnitId", assertUnitQuery.getApiUnitId())
                .pagination(assertUnitQuery.getPageParam())
                .orders(assertUnitQuery.getOrderParams())
                .get();
        return jpaTemplate.findPage(queryCondition, AssertCaseUnitEntity.class);
    }
}