package io.tiklab.postin.autotest.common.ifjudgment.dao;

import io.tiklab.core.page.Pagination;
import io.tiklab.dal.jpa.JpaTemplate;
import io.tiklab.dal.jpa.criterial.condition.DeleteCondition;
import io.tiklab.dal.jpa.criterial.condition.QueryCondition;
import io.tiklab.dal.jpa.criterial.conditionbuilder.QueryBuilders;
import io.tiklab.postin.autotest.common.ifjudgment.entity.IfVariableEntity;
import io.tiklab.postin.autotest.common.ifjudgment.model.IfVariableQuery;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * if值判断 数据访问
 */
@Repository
public class IfVariableDao {

    private static Logger logger = LoggerFactory.getLogger(IfVariableDao.class);

    @Autowired
    JpaTemplate jpaTemplate;

    /**
     * 创建if值判断
     * @param ifVariableEntity
     * @return
     */
    public String createIfVariable(IfVariableEntity ifVariableEntity) {
        return jpaTemplate.save(ifVariableEntity,String.class);
    }

    /**
     * 更新if值判断
     * @param ifVariableEntity
     */
    public void updateIfVariable(IfVariableEntity ifVariableEntity){
        jpaTemplate.update(ifVariableEntity);
    }

    /**
     * 删除if值判断
     * @param id
     */
    public void deleteIfVariable(String id){
        jpaTemplate.delete(IfVariableEntity.class,id);
    }

    public void deleteIfVariable(DeleteCondition deleteCondition){
        jpaTemplate.delete(deleteCondition);
    }

    /**
     * 根据id查找if值判断
     * @param id
     * @return
     */
    public IfVariableEntity findIfVariable(String id){
        return jpaTemplate.findOne(IfVariableEntity.class,id);
    }

    /**
    * 查找所有if值判断
    * @return
    */
    public List<IfVariableEntity> findAllIfVariable() {
        return jpaTemplate.findAll(IfVariableEntity.class);
    }

    public List<IfVariableEntity> findIfVariableList(List<String> idList) {
        return jpaTemplate.findList(IfVariableEntity.class,idList);
    }

    /**
     * 根据查询参数查询if值判断列表
     * @param ifVariableQuery
     * @return
     */
    public List<IfVariableEntity> findIfVariableList(IfVariableQuery ifVariableQuery) {
        QueryCondition queryCondition = QueryBuilders.createQuery(IfVariableEntity.class)
                .eq("stepId",ifVariableQuery.getStepId())
                .orders(ifVariableQuery.getOrderParams())
                .get();
        return jpaTemplate.findList(queryCondition, IfVariableEntity.class);
    }

    /**
     * 根据查询参数按分页查询if值判断
     * @param ifVariableQuery
     * @return
     */
    public Pagination<IfVariableEntity> findIfVariablePage(IfVariableQuery ifVariableQuery) {
        QueryCondition queryCondition = QueryBuilders.createQuery(IfVariableEntity.class)
                .eq("stepId",ifVariableQuery.getStepId())
                .orders(ifVariableQuery.getOrderParams())
                .pagination(ifVariableQuery.getPageParam())
                .get();
        return jpaTemplate.findPage(queryCondition, IfVariableEntity.class);
    }
}