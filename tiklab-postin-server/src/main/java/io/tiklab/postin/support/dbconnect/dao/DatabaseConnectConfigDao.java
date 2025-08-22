package io.tiklab.postin.support.dbconnect.dao;

import io.tiklab.core.page.Pagination;
import io.tiklab.dal.jpa.JpaTemplate;
import io.tiklab.dal.jpa.criterial.condition.DeleteCondition;
import io.tiklab.dal.jpa.criterial.condition.QueryCondition;
import io.tiklab.dal.jpa.criterial.conditionbuilder.QueryBuilders;
import io.tiklab.postin.support.dbconnect.entity.DatabaseConnectConfigEntity;
import io.tiklab.postin.support.dbconnect.model.DatabaseConnectConfigQuery;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 数据库连接配置 数据访问
 */
@Repository
public class DatabaseConnectConfigDao {

    private static Logger logger = LoggerFactory.getLogger(DatabaseConnectConfigDao.class);

    @Autowired
    JpaTemplate jpaTemplate;

    /**
     * 创建数据库连接
     * @param databaseConnectConfigEntity
     * @return
     */
    public String createDatabaseConnectConfig(DatabaseConnectConfigEntity databaseConnectConfigEntity) {
        return jpaTemplate.save(databaseConnectConfigEntity,String.class);
    }

    /**
     * 更新数据库连接
     * @param databaseConnectConfigEntity
     */
    public void updateDatabaseConnectConfig(DatabaseConnectConfigEntity databaseConnectConfigEntity){
        jpaTemplate.update(databaseConnectConfigEntity);
    }

    /**
     * 删除数据库连接
     * @param id
     */
    public void deleteDatabaseConnectConfig(String id){
        jpaTemplate.delete(DatabaseConnectConfigEntity.class,id);
    }

    /**
     * 通过条件删除数据库连接
     * @param deleteCondition
     */
    public void deleteDatabaseConnectConfigList(DeleteCondition deleteCondition){
        jpaTemplate.delete(deleteCondition);
    }

    /**
     * 查找数据库连接
     * @param id
     * @return
     */
    public DatabaseConnectConfigEntity findDatabaseConnectConfig(String id){
        return jpaTemplate.findOne(DatabaseConnectConfigEntity.class,id);
    }

    /**
    * 查找所有数据库连接
    * @return
    */
    public List<DatabaseConnectConfigEntity> findAllDatabaseConnectConfig() {
        return jpaTemplate.findAll(DatabaseConnectConfigEntity.class);
    }

    /**
     * 根据查询参数查找数据库连接列表
     * @param databaseConnectConfigQuery
     * @return
     */
    public List<DatabaseConnectConfigEntity> findDatabaseConnectConfigList(DatabaseConnectConfigQuery databaseConnectConfigQuery) {
        QueryCondition queryCondition = QueryBuilders.createQuery(DatabaseConnectConfigEntity.class)
                .eq("dbConnectId", databaseConnectConfigQuery.getDbConnectId())
                .orders(databaseConnectConfigQuery.getOrderParams())
                .get();

        return jpaTemplate.findList(queryCondition, DatabaseConnectConfigEntity.class);
    }

    /**
     * 根据查询参数按分页查找数据库连接列表
     * @param databaseConnectConfigQuery
     * @return
     */
    public Pagination<DatabaseConnectConfigEntity> findDatabaseConnectConfigPage(DatabaseConnectConfigQuery databaseConnectConfigQuery) {
        QueryCondition queryCondition = QueryBuilders.createQuery(DatabaseConnectConfigEntity.class)
                .eq("dbConnectId", databaseConnectConfigQuery.getDbConnectId())
                .pagination(databaseConnectConfigQuery.getPageParam())
                .orders(databaseConnectConfigQuery.getOrderParams())
                .get();

        return jpaTemplate.findPage(queryCondition, DatabaseConnectConfigEntity.class);
    }
}