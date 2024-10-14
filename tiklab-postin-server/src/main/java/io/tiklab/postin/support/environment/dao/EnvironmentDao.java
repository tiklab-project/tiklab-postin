package io.tiklab.postin.support.environment.dao;

import io.tiklab.postin.support.environment.entity.EnvironmentEntity;
import io.tiklab.core.page.Pagination;
import io.tiklab.postin.support.environment.model.EnvironmentQuery;
import io.tiklab.dal.jpa.JpaTemplate;
import io.tiklab.dal.jpa.criterial.condition.DeleteCondition;
import io.tiklab.dal.jpa.criterial.condition.QueryCondition;
import io.tiklab.dal.jpa.criterial.conditionbuilder.QueryBuilders;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 环境 数据访问
 */
@Repository
public class EnvironmentDao{

    private static Logger logger = LoggerFactory.getLogger(EnvironmentDao.class);

    @Autowired
    JpaTemplate jpaTemplate;

    /**
     * 创建环境
     * @param environmentEntity
     * @return
     */
    public String createEnvironment(EnvironmentEntity environmentEntity) {
        return jpaTemplate.save(environmentEntity,String.class);
    }

    /**
     * 更新环境
     * @param environmentEntity
     */
    public void updateEnvironment(EnvironmentEntity environmentEntity){
        jpaTemplate.update(environmentEntity);
    }

    /**
     * 删除环境
     * @param id
     */
    public void deleteEnvironment(String id){
        jpaTemplate.delete(EnvironmentEntity.class,id);
    }

    public void deleteEnvironment(DeleteCondition deleteCondition){
        jpaTemplate.delete(deleteCondition);
    }

    /**
     * 查找环境
     * @param id
     * @return
     */
    public EnvironmentEntity findEnvironment(String id){
        return jpaTemplate.findOne(EnvironmentEntity.class,id);
    }

    /**
    * 查找所有环境
    * @return
    */
    public List<EnvironmentEntity> findAllEnvironment() {
        return jpaTemplate.findAll(EnvironmentEntity.class);
    }

    public List<EnvironmentEntity> findEnvironmentList(List<String> idList) {
        return jpaTemplate.findList(EnvironmentEntity.class,idList);
    }

    /**
     * 根据查询参数查找环境
     * @param environmentQuery
     * @return
     */
    public List<EnvironmentEntity> findEnvironmentList(EnvironmentQuery environmentQuery) {
        QueryCondition queryCondition = QueryBuilders.createQuery(EnvironmentEntity.class)
                .eq("workspaceId",environmentQuery.getWorkspaceId())
                .like("name", environmentQuery.getName())
                .orders(environmentQuery.getOrderParams())
                .get();
        return jpaTemplate.findList(queryCondition, EnvironmentEntity.class);
    }

    /**
     * 根据查询参数按分页查找环境
     * @param environmentQuery
     * @return
     */
    public Pagination<EnvironmentEntity> findEnvironmentPage(EnvironmentQuery environmentQuery) {
        QueryCondition queryCondition = QueryBuilders.createQuery(EnvironmentEntity.class)
                .eq("workspaceId",environmentQuery.getWorkspaceId())
                .like("name", environmentQuery.getName())
                .pagination(environmentQuery.getPageParam())
                .orders(environmentQuery.getOrderParams())
                .get();
        return jpaTemplate.findPage(queryCondition, EnvironmentEntity.class);
    }
}