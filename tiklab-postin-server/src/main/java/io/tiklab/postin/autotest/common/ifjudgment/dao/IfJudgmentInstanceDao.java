package io.tiklab.postin.autotest.common.ifjudgment.dao;

import io.tiklab.core.page.Pagination;
import io.tiklab.dal.jpa.JpaTemplate;
import io.tiklab.dal.jpa.criterial.condition.DeleteCondition;
import io.tiklab.dal.jpa.criterial.condition.QueryCondition;
import io.tiklab.dal.jpa.criterial.conditionbuilder.QueryBuilders;
import io.tiklab.postin.autotest.common.ifjudgment.entity.IfJudgmentInstanceEntity;
import io.tiklab.postin.autotest.common.ifjudgment.model.IfJudgmentInstanceQuery;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * if条件判断 数据访问
 */
@Repository
public class IfJudgmentInstanceDao {

    private static Logger logger = LoggerFactory.getLogger(IfJudgmentInstanceDao.class);

    @Autowired
    JpaTemplate jpaTemplate;

    /**
     * 创建if条件判断
     * @param ifJudgmentInstanceEntity
     * @return
     */
    public String createIfJudgmentInstance(IfJudgmentInstanceEntity ifJudgmentInstanceEntity) {
        return jpaTemplate.save(ifJudgmentInstanceEntity,String.class);
    }

    /**
     * 更新if条件判断
     * @param ifJudgmentInstanceEntity
     */
    public void updateIfJudgmentInstance(IfJudgmentInstanceEntity ifJudgmentInstanceEntity){
        jpaTemplate.update(ifJudgmentInstanceEntity);
    }

    /**
     * 删除if条件判断
     * @param id
     */
    public void deleteIfJudgmentInstance(String id){
        jpaTemplate.delete(IfJudgmentInstanceEntity.class,id);
    }

    public void deleteIfJudgmentInstance(DeleteCondition deleteCondition){
        jpaTemplate.delete(deleteCondition);
    }

    /**
     * 根据id查找if条件判断
     * @param id
     * @return
     */
    public IfJudgmentInstanceEntity findIfJudgmentInstance(String id){
        return jpaTemplate.findOne(IfJudgmentInstanceEntity.class,id);
    }

    /**
    * 查找所有if条件判断
    * @return
    */
    public List<IfJudgmentInstanceEntity> findAllIfJudgmentInstance() {
        return jpaTemplate.findAll(IfJudgmentInstanceEntity.class);
    }

    public List<IfJudgmentInstanceEntity> findIfJudgmentInstanceList(List<String> idList) {
        return jpaTemplate.findList(IfJudgmentInstanceEntity.class,idList);
    }

    /**
     * 根据查询参数查询if条件判断列表
     * @param ifJudgmentInstanceQuery
     * @return
     */
    public List<IfJudgmentInstanceEntity> findIfJudgmentInstanceList(IfJudgmentInstanceQuery ifJudgmentInstanceQuery) {
        QueryCondition queryCondition = QueryBuilders.createQuery(IfJudgmentInstanceEntity.class)
                .eq("stepInstanceId",ifJudgmentInstanceQuery.getStepInstanceId())
                .orders(ifJudgmentInstanceQuery.getOrderParams())
                .get();
        return jpaTemplate.findList(queryCondition, IfJudgmentInstanceEntity.class);
    }

    /**
     * 根据查询参数按分页查询if条件判断
     * @param ifJudgmentInstanceQuery
     * @return
     */
    public Pagination<IfJudgmentInstanceEntity> findIfJudgmentInstancePage(IfJudgmentInstanceQuery ifJudgmentInstanceQuery) {
        QueryCondition queryCondition = QueryBuilders.createQuery(IfJudgmentInstanceEntity.class)
                .eq("stepInstanceId",ifJudgmentInstanceQuery.getStepInstanceId())
                .orders(ifJudgmentInstanceQuery.getOrderParams())
                .pagination(ifJudgmentInstanceQuery.getPageParam())
                .get();
        return jpaTemplate.findPage(queryCondition, IfJudgmentInstanceEntity.class);
    }
}