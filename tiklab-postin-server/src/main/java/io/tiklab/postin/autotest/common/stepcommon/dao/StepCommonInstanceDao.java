package io.tiklab.postin.autotest.common.stepcommon.dao;

import io.tiklab.core.page.Pagination;
import io.tiklab.dal.jpa.JpaTemplate;
import io.tiklab.dal.jpa.criterial.condition.DeleteCondition;
import io.tiklab.dal.jpa.criterial.condition.QueryCondition;
import io.tiklab.dal.jpa.criterial.conditionbuilder.QueryBuilders;
import io.tiklab.postin.autotest.common.stepcommon.entity.StepCommonInstanceEntity;
import io.tiklab.postin.autotest.common.stepcommon.model.StepCommonInstanceQuery;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 公共步骤历史 数据访问
 */
@Repository
public class StepCommonInstanceDao {

    private static Logger logger = LoggerFactory.getLogger(StepCommonInstanceDao.class);

    @Autowired
    JpaTemplate jpaTemplate;

    /**
     * 创建公共步骤历史
     * @param stepCommonInstanceEntity
     * @return
     */
    public String createStepCommonInstance(StepCommonInstanceEntity stepCommonInstanceEntity) {
        return jpaTemplate.save(stepCommonInstanceEntity,String.class);
    }

    /**
     * 更新公共步骤历史
     * @param stepCommonInstanceEntity
     */
    public void updateStepCommonInstance(StepCommonInstanceEntity stepCommonInstanceEntity){
        jpaTemplate.update(stepCommonInstanceEntity);
    }

    /**
     * 删除公共步骤历史
     * @param id
     */
    public void deleteStepCommonInstance(String id){
        jpaTemplate.delete(StepCommonInstanceEntity.class,id);
    }

    public void deleteStepCommonInstance(DeleteCondition deleteCondition){
        jpaTemplate.delete(deleteCondition);
    }

    /**
     * 根据id查找公共步骤历史
     * @param id
     * @return
     */
    public StepCommonInstanceEntity findStepCommonInstance(String id){
        return jpaTemplate.findOne(StepCommonInstanceEntity.class,id);
    }

    /**
    * 查找所有公共步骤历史
    * @return
    */
    public List<StepCommonInstanceEntity> findAllStepCommonInstance() {
        return jpaTemplate.findAll(StepCommonInstanceEntity.class);
    }

    public List<StepCommonInstanceEntity> findStepCommonInstanceList(List<String> idList) {
        return jpaTemplate.findList(StepCommonInstanceEntity.class,idList);
    }

    /**
     * 根据查询参数查询公共步骤历史列表
     * @param stepCommonInstanceQuery
     * @return
     */
    public List<StepCommonInstanceEntity> findStepCommonInstanceList(StepCommonInstanceQuery stepCommonInstanceQuery) {
        QueryCondition queryCondition = QueryBuilders.createQuery(StepCommonInstanceEntity.class)
                .eq("instanceId",stepCommonInstanceQuery.getInstanceId())
                .orders(stepCommonInstanceQuery.getOrderParams())
                .get();
        return jpaTemplate.findList(queryCondition, StepCommonInstanceEntity.class);
    }

    /**
     * 根据查询参数按分页查询公共步骤历史
     * @param stepCommonInstanceQuery
     * @return
     */
    public Pagination<StepCommonInstanceEntity> findStepCommonInstancePage(StepCommonInstanceQuery stepCommonInstanceQuery) {
        QueryCondition queryCondition = QueryBuilders.createQuery(StepCommonInstanceEntity.class)
                .eq("instanceId",stepCommonInstanceQuery.getInstanceId())
                .orders(stepCommonInstanceQuery.getOrderParams())
                .pagination(stepCommonInstanceQuery.getPageParam())
                .get();
        return jpaTemplate.findPage(queryCondition, StepCommonInstanceEntity.class);
    }
}