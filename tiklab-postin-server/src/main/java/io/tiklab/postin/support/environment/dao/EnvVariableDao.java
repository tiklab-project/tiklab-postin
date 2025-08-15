package io.tiklab.postin.support.environment.dao;

import io.tiklab.core.page.Pagination;
import io.tiklab.dal.jpa.JpaTemplate;
import io.tiklab.dal.jpa.criterial.condition.DeleteCondition;
import io.tiklab.dal.jpa.criterial.condition.QueryCondition;
import io.tiklab.dal.jpa.criterial.conditionbuilder.QueryBuilders;
import io.tiklab.postin.support.environment.entity.EnvVariableEntity;
import io.tiklab.postin.support.environment.model.EnvVariableQuery;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 环境中变量 数据访问
 */
@Repository
public class EnvVariableDao {

    private static Logger logger = LoggerFactory.getLogger(EnvVariableDao.class);

    @Autowired
    JpaTemplate jpaTemplate;

    /**
     * 创建环境中变量
     * @param envVariableEntity
     * @return
     */
    public String createEnvVariable(EnvVariableEntity envVariableEntity) {
        return jpaTemplate.save(envVariableEntity,String.class);
    }

    /**
     * 更新环境中变量
     * @param envVariableEntity
     */
    public void updateEnvVariable(EnvVariableEntity envVariableEntity){
        jpaTemplate.update(envVariableEntity);
    }

    /**
     * 删除环境中变量
     * @param id
     */
    public void deleteEnvVariable(String id){
        jpaTemplate.delete(EnvVariableEntity.class,id);
    }

    public void deleteEnvVariable(DeleteCondition deleteCondition){
        jpaTemplate.delete(deleteCondition);
    }

    /**
     * 查找环境中变量
     * @param id
     * @return
     */
    public EnvVariableEntity findEnvVariable(String id){
        return jpaTemplate.findOne(EnvVariableEntity.class,id);
    }

    /**
    * 查找所有环境中变量
    * @return
    */
    public List<EnvVariableEntity> findAllEnvVariable() {
        return jpaTemplate.findAll(EnvVariableEntity.class);
    }

    public List<EnvVariableEntity> findEnvVariableList(List<String> idList) {
        return jpaTemplate.findList(EnvVariableEntity.class,idList);
    }

    /**
     * 根据查询参数查找环境中变量
     * @param envVariableQuery
     * @return
     */
    public List<EnvVariableEntity> findEnvVariableList(EnvVariableQuery envVariableQuery) {
        QueryCondition queryCondition = QueryBuilders.createQuery(EnvVariableEntity.class)
                .eq("envId",envVariableQuery.getEnvId())
                .eq("workspaceId",envVariableQuery.getWorkspaceId())
                .orders(envVariableQuery.getOrderParams())
                .get();
        return jpaTemplate.findList(queryCondition, EnvVariableEntity.class);
    }

    /**
     * 根据查询参数按分页查找环境中变量
     * @param envVariableQuery
     * @return
     */
    public Pagination<EnvVariableEntity> findEnvVariablePage(EnvVariableQuery envVariableQuery) {
        QueryCondition queryCondition = QueryBuilders.createQuery(EnvVariableEntity.class)
                .eq("envId",envVariableQuery.getEnvId())
                .eq("workspaceId",envVariableQuery.getWorkspaceId())
                .pagination(envVariableQuery.getPageParam())
                .orders(envVariableQuery.getOrderParams())
                .get();
        return jpaTemplate.findPage(queryCondition, EnvVariableEntity.class);
    }
}