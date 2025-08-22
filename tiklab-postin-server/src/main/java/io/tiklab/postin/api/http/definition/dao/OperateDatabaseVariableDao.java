package io.tiklab.postin.api.http.definition.dao;

import io.tiklab.core.page.Pagination;
import io.tiklab.dal.jpa.JpaTemplate;
import io.tiklab.dal.jpa.criterial.condition.DeleteCondition;
import io.tiklab.dal.jpa.criterial.condition.QueryCondition;
import io.tiklab.dal.jpa.criterial.conditionbuilder.QueryBuilders;
import io.tiklab.postin.api.http.definition.entity.OperateDatabaseVariableEntity;
import io.tiklab.postin.api.http.definition.model.OperateDatabaseVariableQuery;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 数据库操作中的变量 数据访问
 */
@Repository
public class OperateDatabaseVariableDao {

    private static Logger logger = LoggerFactory.getLogger(OperateDatabaseVariableDao.class);

    @Autowired
    JpaTemplate jpaTemplate;

    /**
     * 创建数据库操作中的变量
     * @param operateDatabaseVariableEntity
     * @return
     */
    public String createOperateDatabaseVariable(OperateDatabaseVariableEntity operateDatabaseVariableEntity) {
        return jpaTemplate.save(operateDatabaseVariableEntity,String.class);
    }

    /**
     * 更新数据库操作中的变量
     * @param operateDatabaseVariableEntity
     */
    public void updateOperateDatabaseVariable(OperateDatabaseVariableEntity operateDatabaseVariableEntity){
        jpaTemplate.update(operateDatabaseVariableEntity);
    }

    /**
     * 删除数据库操作中的变量
     * @param id
     */
    public void deleteOperateDatabaseVariable(String id){
        jpaTemplate.delete(OperateDatabaseVariableEntity.class,id);
    }

    /**
     * 通过条件删除数据库操作中的变量
     * @param deleteCondition
     */
    public void deleteOperateDatabaseVariableList(DeleteCondition deleteCondition){
        jpaTemplate.delete(deleteCondition);
    }

    /**
     * 查找数据库操作中的变量
     * @param id
     * @return
     */
    public OperateDatabaseVariableEntity findOperateDatabaseVariable(String id){
        return jpaTemplate.findOne(OperateDatabaseVariableEntity.class,id);
    }

    /**
    * 查找所有数据库操作中的变量
    * @return
    */
    public List<OperateDatabaseVariableEntity> findAllOperateDatabaseVariable() {
        return jpaTemplate.findAll(OperateDatabaseVariableEntity.class);
    }

    /**
     * 根据查询参数查找数据库操作中的变量列表
     * @param operateDatabaseVariableQuery
     * @return
     */
    public List<OperateDatabaseVariableEntity> findOperateDatabaseVariableList(OperateDatabaseVariableQuery operateDatabaseVariableQuery) {
        QueryCondition queryCondition = QueryBuilders.createQuery(OperateDatabaseVariableEntity.class)
                .eq("operationId", operateDatabaseVariableQuery.getOperationId())
                .orders(operateDatabaseVariableQuery.getOrderParams())
                .get();

        return jpaTemplate.findList(queryCondition, OperateDatabaseVariableEntity.class);
    }

    /**
     * 根据查询参数按分页查找数据库操作中的变量列表
     * @param operateDatabaseVariableQuery
     * @return
     */
    public Pagination<OperateDatabaseVariableEntity> findOperateDatabaseVariablePage(OperateDatabaseVariableQuery operateDatabaseVariableQuery) {
        QueryCondition queryCondition = QueryBuilders.createQuery(OperateDatabaseVariableEntity.class)
                .eq("operationId", operateDatabaseVariableQuery.getOperationId())
                .pagination(operateDatabaseVariableQuery.getPageParam())
                .orders(operateDatabaseVariableQuery.getOrderParams())
                .get();

        return jpaTemplate.findPage(queryCondition, OperateDatabaseVariableEntity.class);
    }
}