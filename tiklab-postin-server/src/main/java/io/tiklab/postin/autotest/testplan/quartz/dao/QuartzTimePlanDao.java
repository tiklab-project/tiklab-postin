package io.tiklab.postin.autotest.testplan.quartz.dao;

import io.tiklab.core.page.Pagination;
import io.tiklab.dal.jpa.JpaTemplate;
import io.tiklab.dal.jpa.criterial.condition.DeleteCondition;
import io.tiklab.dal.jpa.criterial.condition.QueryCondition;
import io.tiklab.dal.jpa.criterial.conditionbuilder.QueryBuilders;
import io.tiklab.postin.autotest.testplan.quartz.entity.QuartzTimePlanEntity;
import io.tiklab.postin.autotest.testplan.quartz.model.QuartzTimePlanQuery;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 定时任务时间 数据访问
 */
@Repository
public class QuartzTimePlanDao {

    private static Logger logger = LoggerFactory.getLogger(QuartzTimePlanDao.class);

    @Autowired
    JpaTemplate jpaTemplate;

    /**
     * 创建定时任务时间
     * @param quartzTimePlanEntity
     * @return
     */
    public String createQuartzTimePlan(QuartzTimePlanEntity quartzTimePlanEntity) {
        return jpaTemplate.save(quartzTimePlanEntity,String.class);
    }

    /**
     * 更新定时任务时间
     * @param quartzTimePlanEntity
     */
    public void updateQuartzTimePlan(QuartzTimePlanEntity quartzTimePlanEntity){
        jpaTemplate.update(quartzTimePlanEntity);
    }

    /**
     * 删除定时任务时间
     * @param id
     */
    public void deleteQuartzTimePlan(String id){
        jpaTemplate.delete(QuartzTimePlanEntity.class,id);
    }

    public void deleteQuartzTimePlan(DeleteCondition deleteCondition){
        jpaTemplate.delete(deleteCondition);
    }

    /**
     * 根据id查找定时任务时间
     * @param id
     * @return
     */
    public QuartzTimePlanEntity findQuartzTimePlan(String id){
        return jpaTemplate.findOne(QuartzTimePlanEntity.class,id);
    }


    /**
    * 查找所有定时任务时间
    * @return
    */
    public List<QuartzTimePlanEntity> findAllQuartzTimePlan() {
        return jpaTemplate.findAll(QuartzTimePlanEntity.class);
    }

    public List<QuartzTimePlanEntity> findQuartzTimePlanList(List<String> idList) {
        return jpaTemplate.findList(QuartzTimePlanEntity.class,idList);
    }

    /**
     * 根据查询参数查询定时任务时间列表
     * @param quartzTimePlanQuery
     * @return
     */
    public List<QuartzTimePlanEntity> findQuartzTimePlanList(QuartzTimePlanQuery quartzTimePlanQuery) {
        QueryCondition queryCondition = QueryBuilders.createQuery(QuartzTimePlanEntity.class)
                .eq("quartzPlanId", quartzTimePlanQuery.getQuartzPlanId())
                .orders(quartzTimePlanQuery.getOrderParams())
                .get();
        return jpaTemplate.findList(queryCondition, QuartzTimePlanEntity.class);
    }

    /**
     * 根据查询参数按分页查询定时任务时间
     * @param quartzTimePlanQuery
     * @return
     */
    public Pagination<QuartzTimePlanEntity> findQuartzTimePlanPage(QuartzTimePlanQuery quartzTimePlanQuery) {
        QueryCondition queryCondition = QueryBuilders.createQuery(QuartzTimePlanEntity.class)
                .eq("quartzPlanId", quartzTimePlanQuery.getQuartzPlanId())
                .orders(quartzTimePlanQuery.getOrderParams())
                .pagination(quartzTimePlanQuery.getPageParam())
                .get();
        return jpaTemplate.findPage(queryCondition, QuartzTimePlanEntity.class);
    }
}