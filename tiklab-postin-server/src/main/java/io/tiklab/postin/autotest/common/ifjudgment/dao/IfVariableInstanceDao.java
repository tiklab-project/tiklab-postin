package io.tiklab.postin.autotest.common.ifjudgment.dao;

import io.tiklab.core.page.Pagination;
import io.tiklab.dal.jpa.JpaTemplate;
import io.tiklab.dal.jpa.criterial.condition.DeleteCondition;
import io.tiklab.dal.jpa.criterial.condition.QueryCondition;
import io.tiklab.dal.jpa.criterial.conditionbuilder.QueryBuilders;
import io.tiklab.postin.autotest.common.ifjudgment.entity.IfVariableInstanceEntity;
import io.tiklab.postin.autotest.common.ifjudgment.model.IfVariableInstanceQuery;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * if值判断历史 数据访问
 */
@Repository
public class IfVariableInstanceDao {

    private static Logger logger = LoggerFactory.getLogger(IfVariableInstanceDao.class);

    @Autowired
    JpaTemplate jpaTemplate;

    /**
     * 创建if值判断
     * @param ifVariableInstanceEntity
     * @return
     */
    public String createIfVariableInstance(IfVariableInstanceEntity ifVariableInstanceEntity) {
        return jpaTemplate.save(ifVariableInstanceEntity,String.class);
    }

    /**
     * 更新if值判断
     * @param ifVariableInstanceEntity
     */
    public void updateIfVariableInstance(IfVariableInstanceEntity ifVariableInstanceEntity){
        jpaTemplate.update(ifVariableInstanceEntity);
    }

    /**
     * 删除if值判断
     * @param id
     */
    public void deleteIfVariableInstance(String id){
        jpaTemplate.delete(IfVariableInstanceEntity.class,id);
    }

    public void deleteIfVariableInstance(DeleteCondition deleteCondition){
        jpaTemplate.delete(deleteCondition);
    }

    /**
     * 根据id查找if值判断
     * @param id
     * @return
     */
    public IfVariableInstanceEntity findIfVariableInstance(String id){
        return jpaTemplate.findOne(IfVariableInstanceEntity.class,id);
    }

    /**
    * 查找所有if值判断
    * @return
    */
    public List<IfVariableInstanceEntity> findAllIfVariableInstance() {
        return jpaTemplate.findAll(IfVariableInstanceEntity.class);
    }

    public List<IfVariableInstanceEntity> findIfVariableInstanceList(List<String> idList) {
        return jpaTemplate.findList(IfVariableInstanceEntity.class,idList);
    }

    /**
     * 根据查询参数查询if值判断列表
     * @param ifVariableInstanceQuery
     * @return
     */
    public List<IfVariableInstanceEntity> findIfVariableInstanceList(IfVariableInstanceQuery ifVariableInstanceQuery) {
        QueryCondition queryCondition = QueryBuilders.createQuery(IfVariableInstanceEntity.class)
                .eq("stepInstanceId",ifVariableInstanceQuery.getStepInstanceId())
                .orders(ifVariableInstanceQuery.getOrderParams())
                .get();
        return jpaTemplate.findList(queryCondition, IfVariableInstanceEntity.class);
    }

    /**
     * 根据查询参数按分页查询if值判断
     * @param ifVariableInstanceQuery
     * @return
     */
    public Pagination<IfVariableInstanceEntity> findIfVariableInstancePage(IfVariableInstanceQuery ifVariableInstanceQuery) {
        QueryCondition queryCondition = QueryBuilders.createQuery(IfVariableInstanceEntity.class)
                .eq("stepInstanceId",ifVariableInstanceQuery.getStepInstanceId())
                .orders(ifVariableInstanceQuery.getOrderParams())
                .pagination(ifVariableInstanceQuery.getPageParam())
                .get();
        return jpaTemplate.findPage(queryCondition, IfVariableInstanceEntity.class);
    }
}