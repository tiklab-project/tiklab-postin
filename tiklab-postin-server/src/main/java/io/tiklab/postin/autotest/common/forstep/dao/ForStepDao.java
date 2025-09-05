package io.tiklab.postin.autotest.common.forstep.dao;

import io.tiklab.core.page.Pagination;
import io.tiklab.dal.jpa.JpaTemplate;
import io.tiklab.dal.jpa.criterial.condition.DeleteCondition;
import io.tiklab.dal.jpa.criterial.condition.QueryCondition;
import io.tiklab.dal.jpa.criterial.conditionbuilder.QueryBuilders;
import io.tiklab.postin.autotest.common.forstep.entity.ForStepEntity;
import io.tiklab.postin.autotest.common.forstep.model.ForStepQuery;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * for循环 数据访问
 */
@Repository
public class ForStepDao {

    private static Logger logger = LoggerFactory.getLogger(ForStepDao.class);

    @Autowired
    JpaTemplate jpaTemplate;

    /**
     * 创建for循环
     * @param forStepEntity
     * @return
     */
    public String createForStep(ForStepEntity forStepEntity) {
        return jpaTemplate.save(forStepEntity,String.class);
    }

    /**
     * 更新for循环
     * @param forStepEntity
     */
    public void updateForStep(ForStepEntity forStepEntity){
        jpaTemplate.update(forStepEntity);
    }

    /**
     * 删除for循环
     * @param id
     */
    public void deleteForStep(String id){
        jpaTemplate.delete(ForStepEntity.class,id);
    }

    public void deleteForStep(DeleteCondition deleteCondition){
        jpaTemplate.delete(deleteCondition);
    }

    /**
     * 根据id查找for循环
     * @param id
     * @return
     */
    public ForStepEntity findForStep(String id){
        return jpaTemplate.findOne(ForStepEntity.class,id);
    }

    /**
    * 查找所有for循环
    * @return
    */
    public List<ForStepEntity> findAllForStep() {
        return jpaTemplate.findAll(ForStepEntity.class);
    }

    public List<ForStepEntity> findForStepList(List<String> idList) {
        return jpaTemplate.findList(ForStepEntity.class,idList);
    }

    /**
     * 根据查询参数查询for循环列表
     * @param forStepQuery
     * @return
     */
    public List<ForStepEntity> findForStepList(ForStepQuery forStepQuery) {
        QueryCondition queryCondition = QueryBuilders.createQuery(ForStepEntity.class)
                .eq("caseId",forStepQuery.getCaseId())
                .orders(forStepQuery.getOrderParams())
                .get();
        return jpaTemplate.findList(queryCondition, ForStepEntity.class);
    }

    /**
     * 根据查询参数按分页查询for循环
     * @param forStepQuery
     * @return
     */
    public Pagination<ForStepEntity> findForStepPage(ForStepQuery forStepQuery) {
        QueryCondition queryCondition = QueryBuilders.createQuery(ForStepEntity.class)
                .eq("caseId",forStepQuery.getCaseId())
                .orders(forStepQuery.getOrderParams())
                .pagination(forStepQuery.getPageParam())
                .get();
        return jpaTemplate.findPage(queryCondition, ForStepEntity.class);
    }
}