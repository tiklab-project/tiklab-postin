package io.tiklab.postin.autotest.testplan.quartz.dao;

import io.tiklab.core.page.Pagination;
import io.tiklab.dal.jpa.JpaTemplate;
import io.tiklab.dal.jpa.criterial.condition.DeleteCondition;
import io.tiklab.dal.jpa.criterial.condition.QueryCondition;
import io.tiklab.dal.jpa.criterial.conditionbuilder.QueryBuilders;
import io.tiklab.postin.autotest.testplan.quartz.entity.QuartzPlanEntity;
import io.tiklab.postin.autotest.testplan.quartz.model.QuartzPlanQuery;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 定时任务 数据访问
 */
@Repository
public class QuartzPlanDao {

    private static Logger logger = LoggerFactory.getLogger(QuartzPlanDao.class);

    @Autowired
    JpaTemplate jpaTemplate;

    /**
     * 创建定时任务
     * @param quartzPlanEntity
     * @return
     */
    public String createQuartzPlan(QuartzPlanEntity quartzPlanEntity) {
        return jpaTemplate.save(quartzPlanEntity,String.class);
    }

    /**
     * 更新定时任务
     * @param quartzPlanEntity
     */
    public void updateQuartzPlan(QuartzPlanEntity quartzPlanEntity){
        jpaTemplate.update(quartzPlanEntity);
    }

    /**
     * 删除定时任务
     * @param id
     */
    public void deleteQuartzPlan(String id){
        jpaTemplate.delete(QuartzPlanEntity.class,id);
    }

    public void deleteQuartzPlan(DeleteCondition deleteCondition){
        jpaTemplate.delete(deleteCondition);
    }

    /**
     * 根据id查找定时任务
     * @param id
     * @return
     */
    public QuartzPlanEntity findQuartzPlan(String id){
        return jpaTemplate.findOne(QuartzPlanEntity.class,id);
    }


    /**
    * 查找所有定时任务
    * @return
    */
    public List<QuartzPlanEntity> findAllQuartzPlan() {
        return jpaTemplate.findAll(QuartzPlanEntity.class);
    }

    public List<QuartzPlanEntity> findQuartzPlanList(List<String> idList) {
        return jpaTemplate.findList(QuartzPlanEntity.class,idList);
    }

    /**
     * 根据查询参数查询定时任务列表
     * @param quartzPlanQuery
     * @return
     */
    public List<QuartzPlanEntity> findQuartzPlanList(QuartzPlanQuery quartzPlanQuery) {
        QueryCondition queryCondition = QueryBuilders.createQuery(QuartzPlanEntity.class)
                .eq("testPlanId", quartzPlanQuery.getTestPlanId())
                .orders(quartzPlanQuery.getOrderParams())
                .get();
        return jpaTemplate.findList(queryCondition, QuartzPlanEntity.class);
    }

    /**
     * 根据查询参数按分页查询定时任务
     * @param quartzPlanQuery
     * @return
     */
    public Pagination<QuartzPlanEntity> findQuartzPlanPage(QuartzPlanQuery quartzPlanQuery) {
        QueryCondition queryCondition = QueryBuilders.createQuery(QuartzPlanEntity.class)
                .eq("testPlanId", quartzPlanQuery.getTestPlanId())
                .orders(quartzPlanQuery.getOrderParams())
                .pagination(quartzPlanQuery.getPageParam())
                .get();
        return jpaTemplate.findPage(queryCondition, QuartzPlanEntity.class);
    }
}