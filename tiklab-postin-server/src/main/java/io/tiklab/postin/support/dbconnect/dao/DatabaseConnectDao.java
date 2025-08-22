package io.tiklab.postin.support.dbconnect.dao;

import io.tiklab.core.page.Pagination;
import io.tiklab.dal.jpa.JpaTemplate;
import io.tiklab.dal.jpa.criterial.condition.DeleteCondition;
import io.tiklab.dal.jpa.criterial.condition.QueryCondition;
import io.tiklab.dal.jpa.criterial.conditionbuilder.QueryBuilders;
import io.tiklab.postin.support.dbconnect.entity.DatabaseConnectEntity;
import io.tiklab.postin.support.dbconnect.model.DatabaseConnectQuery;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 数据库连接 数据访问
 */
@Repository
public class DatabaseConnectDao {

    private static Logger logger = LoggerFactory.getLogger(DatabaseConnectDao.class);

    @Autowired
    JpaTemplate jpaTemplate;

    /**
     * 创建数据库连接
     * @param databaseConnectEntity
     * @return
     */
    public String createDatabaseConnect(DatabaseConnectEntity databaseConnectEntity) {
        return jpaTemplate.save(databaseConnectEntity,String.class);
    }

    /**
     * 更新数据库连接
     * @param databaseConnectEntity
     */
    public void updateDatabaseConnect(DatabaseConnectEntity databaseConnectEntity){
        jpaTemplate.update(databaseConnectEntity);
    }

    /**
     * 删除数据库连接
     * @param id
     */
    public void deleteDatabaseConnect(String id){
        jpaTemplate.delete(DatabaseConnectEntity.class,id);
    }

    /**
     * 通过条件删除数据库连接
     * @param deleteCondition
     */
    public void deleteDatabaseConnectList(DeleteCondition deleteCondition){
        jpaTemplate.delete(deleteCondition);
    }

    /**
     * 查找数据库连接
     * @param id
     * @return
     */
    public DatabaseConnectEntity findDatabaseConnect(String id){
        return jpaTemplate.findOne(DatabaseConnectEntity.class,id);
    }

    /**
    * 查找所有数据库连接
    * @return
    */
    public List<DatabaseConnectEntity> findAllDatabaseConnect() {
        return jpaTemplate.findAll(DatabaseConnectEntity.class);
    }

    /**
     * 根据查询参数查找数据库连接列表
     * @param databaseConnectQuery
     * @return
     */
    public List<DatabaseConnectEntity> findDatabaseConnectList(DatabaseConnectQuery databaseConnectQuery) {
        QueryCondition queryCondition = QueryBuilders.createQuery(DatabaseConnectEntity.class)
                .eq("workspaceId", databaseConnectQuery.getWorkspaceId())
                .orders(databaseConnectQuery.getOrderParams())
                .get();

        return jpaTemplate.findList(queryCondition, DatabaseConnectEntity.class);
    }

    /**
     * 根据查询参数按分页查找数据库连接列表
     * @param databaseConnectQuery
     * @return
     */
    public Pagination<DatabaseConnectEntity> findDatabaseConnectPage(DatabaseConnectQuery databaseConnectQuery) {
        QueryCondition queryCondition = QueryBuilders.createQuery(DatabaseConnectEntity.class)
                .eq("workspaceId", databaseConnectQuery.getWorkspaceId())
                .pagination(databaseConnectQuery.getPageParam())
                .orders(databaseConnectQuery.getOrderParams())
                .get();

        return jpaTemplate.findPage(queryCondition, DatabaseConnectEntity.class);
    }
}