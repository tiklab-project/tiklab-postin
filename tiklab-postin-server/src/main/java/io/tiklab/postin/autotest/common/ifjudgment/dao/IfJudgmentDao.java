package io.tiklab.postin.autotest.common.ifjudgment.dao;

import io.tiklab.core.page.Pagination;
import io.tiklab.dal.jpa.JpaTemplate;
import io.tiklab.dal.jpa.criterial.condition.DeleteCondition;
import io.tiklab.dal.jpa.criterial.condition.QueryCondition;
import io.tiklab.dal.jpa.criterial.conditionbuilder.QueryBuilders;
import io.tiklab.postin.autotest.common.ifjudgment.entity.IfJudgmentEntity;
import io.tiklab.postin.autotest.common.ifjudgment.model.IfJudgmentQuery;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * if条件判断 数据访问
 */
@Repository
public class IfJudgmentDao {

    private static Logger logger = LoggerFactory.getLogger(IfJudgmentDao.class);

    @Autowired
    JpaTemplate jpaTemplate;

    /**
     * 创建if条件判断
     * @param ifJudgmentEntity
     * @return
     */
    public String createIfJudgment(IfJudgmentEntity ifJudgmentEntity) {
        return jpaTemplate.save(ifJudgmentEntity,String.class);
    }

    /**
     * 更新if条件判断
     * @param ifJudgmentEntity
     */
    public void updateIfJudgment(IfJudgmentEntity ifJudgmentEntity){
        jpaTemplate.update(ifJudgmentEntity);
    }

    /**
     * 删除if条件判断
     * @param id
     */
    public void deleteIfJudgment(String id){
        jpaTemplate.delete(IfJudgmentEntity.class,id);
    }

    public void deleteIfJudgment(DeleteCondition deleteCondition){
        jpaTemplate.delete(deleteCondition);
    }

    /**
     * 根据id查找if条件判断
     * @param id
     * @return
     */
    public IfJudgmentEntity findIfJudgment(String id){
        return jpaTemplate.findOne(IfJudgmentEntity.class,id);
    }

    /**
    * 查找所有if条件判断
    * @return
    */
    public List<IfJudgmentEntity> findAllIfJudgment() {
        return jpaTemplate.findAll(IfJudgmentEntity.class);
    }

    public List<IfJudgmentEntity> findIfJudgmentList(List<String> idList) {
        return jpaTemplate.findList(IfJudgmentEntity.class,idList);
    }

    /**
     * 根据查询参数查询if条件判断列表
     * @param ifJudgmentQuery
     * @return
     */
    public List<IfJudgmentEntity> findIfJudgmentList(IfJudgmentQuery ifJudgmentQuery) {
        QueryCondition queryCondition = QueryBuilders.createQuery(IfJudgmentEntity.class)
                .eq("caseId",ifJudgmentQuery.getCaseId())
                .orders(ifJudgmentQuery.getOrderParams())
                .get();
        return jpaTemplate.findList(queryCondition, IfJudgmentEntity.class);
    }

    /**
     * 根据查询参数按分页查询if条件判断
     * @param ifJudgmentQuery
     * @return
     */
    public Pagination<IfJudgmentEntity> findIfJudgmentPage(IfJudgmentQuery ifJudgmentQuery) {
        QueryCondition queryCondition = QueryBuilders.createQuery(IfJudgmentEntity.class)
                .eq("caseId",ifJudgmentQuery.getCaseId())
                .orders(ifJudgmentQuery.getOrderParams())
                .pagination(ifJudgmentQuery.getPageParam())
                .get();
        return jpaTemplate.findPage(queryCondition, IfJudgmentEntity.class);
    }
}