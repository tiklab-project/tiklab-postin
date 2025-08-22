package io.tiklab.postin.api.http.definition.dao;

import io.tiklab.core.page.Pagination;
import io.tiklab.dal.jpa.JpaTemplate;
import io.tiklab.dal.jpa.criterial.condition.DeleteCondition;
import io.tiklab.dal.jpa.criterial.condition.QueryCondition;
import io.tiklab.dal.jpa.criterial.conditionbuilder.QueryBuilders;
import io.tiklab.postin.api.http.definition.entity.OperateDatabaseEntity;
import io.tiklab.postin.api.http.definition.model.OperateDatabaseQuery;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 数据库操作 数据访问
 */
@Repository
public class OperateDatabaseDao {

    private static Logger logger = LoggerFactory.getLogger(OperateDatabaseDao.class);

    @Autowired
    JpaTemplate jpaTemplate;

    /**
     * 创建数据库操作
     * @param operateDatabaseEntity
     * @return
     */
    public String createOperateDatabase(OperateDatabaseEntity operateDatabaseEntity) {
        return jpaTemplate.save(operateDatabaseEntity,String.class);
    }

    /**
     * 更新数据库操作
     * @param operateDatabaseEntity
     */
    public void updateOperateDatabase(OperateDatabaseEntity operateDatabaseEntity){
        jpaTemplate.update(operateDatabaseEntity);
    }

    /**
     * 删除数据库操作
     * @param id
     */
    public void deleteOperateDatabase(String id){
        jpaTemplate.delete(OperateDatabaseEntity.class,id);
    }

    /**
     * 通过条件删除数据库操作
     * @param deleteCondition
     */
    public void deleteOperateDatabaseList(DeleteCondition deleteCondition){
        jpaTemplate.delete(deleteCondition);
    }

    /**
     * 查找数据库操作
     * @param id
     * @return
     */
    public OperateDatabaseEntity findOperateDatabase(String id){
        return jpaTemplate.findOne(OperateDatabaseEntity.class,id);
    }

    /**
    * 查找所有数据库操作
    * @return
    */
    public List<OperateDatabaseEntity> findAllOperateDatabase() {
        return jpaTemplate.findAll(OperateDatabaseEntity.class);
    }

    /**
     * 根据查询参数查找数据库操作列表
     * @param operateDatabaseQuery
     * @return
     */
    public List<OperateDatabaseEntity> findOperateDatabaseList(OperateDatabaseQuery operateDatabaseQuery) {
        QueryCondition queryCondition = QueryBuilders.createQuery(OperateDatabaseEntity.class)
                .eq("operationId", operateDatabaseQuery.getOperationId())
                .orders(operateDatabaseQuery.getOrderParams())
                .get();

        return jpaTemplate.findList(queryCondition, OperateDatabaseEntity.class);
    }

    /**
     * 根据查询参数按分页查找数据库操作列表
     * @param operateDatabaseQuery
     * @return
     */
    public Pagination<OperateDatabaseEntity> findOperateDatabasePage(OperateDatabaseQuery operateDatabaseQuery) {
        QueryCondition queryCondition = QueryBuilders.createQuery(OperateDatabaseEntity.class)
                .eq("operationId", operateDatabaseQuery.getOperationId())
                .pagination(operateDatabaseQuery.getPageParam())
                .orders(operateDatabaseQuery.getOrderParams())
                .get();

        return jpaTemplate.findPage(queryCondition, OperateDatabaseEntity.class);
    }
}