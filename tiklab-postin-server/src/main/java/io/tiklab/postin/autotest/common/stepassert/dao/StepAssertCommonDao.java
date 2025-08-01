package io.tiklab.postin.autotest.common.stepassert.dao;

import io.tiklab.dal.jpa.JpaTemplate;
import io.tiklab.dal.jpa.criterial.condition.DeleteCondition;
import io.tiklab.dal.jpa.criterial.condition.QueryCondition;
import io.tiklab.dal.jpa.criterial.conditionbuilder.QueryBuilders;
import io.tiklab.postin.autotest.common.stepassert.entity.StepAssertCommonEntity;
import io.tiklab.postin.autotest.common.stepassert.model.StepAssertCommonQuery;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 步骤的断言 数据访问
 */
@Repository
public class StepAssertCommonDao {

    private static Logger logger = LoggerFactory.getLogger(StepAssertCommonDao.class);

    @Autowired
    JpaTemplate jpaTemplate;

    /**
     * 创建
     * @param stepAssertCommonEntity
     * @return
     */
    public String createStepAssertCommon(StepAssertCommonEntity stepAssertCommonEntity) {
        return jpaTemplate.save(stepAssertCommonEntity,String.class);
    }

    /**
     * 更新
     * @param stepAssertCommonEntity
     */
    public void updateStepAssertCommon(StepAssertCommonEntity stepAssertCommonEntity){
        jpaTemplate.update(stepAssertCommonEntity);
    }

    /**
     * 删除
     * @param id
     */
    public void deleteStepAssertCommon(String id){
        jpaTemplate.delete(StepAssertCommonEntity.class,id);
    }

    public void deleteStepAssertCommon(DeleteCondition deleteCondition){
        jpaTemplate.delete(deleteCondition);
    }

    /**
     * 根据id查找
     * @param id
     * @return
     */
    public StepAssertCommonEntity findStepAssertCommon(String id){
        return jpaTemplate.findOne(StepAssertCommonEntity.class,id);
    }

    public List<StepAssertCommonEntity> findStepAssertCommonList(StepAssertCommonQuery stepAssertCommonQuery){
        QueryCondition queryCondition = QueryBuilders.createQuery(StepAssertCommonEntity.class)
                .eq("stepId", stepAssertCommonQuery.getStepId())
                .orders(stepAssertCommonQuery.getOrderParams())
                .get();
        return jpaTemplate.findList(queryCondition,StepAssertCommonEntity.class);
    }

 
}