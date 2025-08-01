package io.tiklab.postin.autotest.agentconfig.dao;

import io.tiklab.postin.autotest.agentconfig.entity.AgentConfigEntity;
import io.tiklab.postin.autotest.agentconfig.model.AgentConfigQuery;
import io.tiklab.core.page.Pagination;
import io.tiklab.dal.jpa.criterial.condition.QueryCondition;
import io.tiklab.dal.jpa.criterial.conditionbuilder.QueryBuilders;
import io.tiklab.dal.jpa.JpaTemplate;
import io.tiklab.dal.jpa.criterial.condition.DeleteCondition;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * agent配置 数据访问
 */
@Repository
public class AgentConfigDao{

    private static Logger logger = LoggerFactory.getLogger(AgentConfigDao.class);

    @Autowired
    JpaTemplate jpaTemplate;

    /**
     * 创建agent配置
     * @param agentConfigEntity
     * @return
     */
    public String createAgentConfig(AgentConfigEntity agentConfigEntity) {
        return jpaTemplate.save(agentConfigEntity,String.class);
    }

    /**
     * 更新agent配置
     * @param agentConfigEntity
     */
    public void updateAgentConfig(AgentConfigEntity agentConfigEntity){
        jpaTemplate.update(agentConfigEntity);
    }

    /**
     * 删除agent配置
     * @param id
     */
    public void deleteAgentConfig(String id){
        jpaTemplate.delete(AgentConfigEntity.class,id);
    }

    public void deleteAgentConfig(DeleteCondition deleteCondition){
        jpaTemplate.delete(deleteCondition);
    }

    /**
     * 根据id查找agent配置
     * @param id
     * @return
     */
    public AgentConfigEntity findAgentConfig(String id){
        return jpaTemplate.findOne(AgentConfigEntity.class,id);
    }

    /**
    * 查找所有agent配置
    * @return
    */
    public List<AgentConfigEntity> findAllAgentConfig() {
        return jpaTemplate.findAll(AgentConfigEntity.class);
    }

    public List<AgentConfigEntity> findAgentConfigList(List<String> idList) {
        return jpaTemplate.findList(AgentConfigEntity.class,idList);
    }

    /**
     * 根据查询参数查询agent配置列表
     * @param agentConfigQuery
     * @return
     */
    public List<AgentConfigEntity> findAgentConfigList(AgentConfigQuery agentConfigQuery) {
        QueryCondition queryCondition = QueryBuilders.createQuery(AgentConfigEntity.class)
                .eq("status", agentConfigQuery.getStatus())
                .eq("enable",agentConfigQuery.getEnable())
                .orders(agentConfigQuery.getOrderParams())
                .get();
        return jpaTemplate.findList(queryCondition,AgentConfigEntity.class);
    }

    /**
     * 根据查询参数按分页查询agent配置
     * @param agentConfigQuery
     * @return
     */
    public Pagination<AgentConfigEntity> findAgentConfigPage(AgentConfigQuery agentConfigQuery) {
        QueryCondition queryCondition = QueryBuilders.createQuery(AgentConfigEntity.class)
                .eq("status", agentConfigQuery.getStatus())
                .eq("enable",agentConfigQuery.getEnable())
                .orders(agentConfigQuery.getOrderParams())
                .pagination(agentConfigQuery.getPageParam())
                .get();
        return jpaTemplate.findPage(queryCondition,AgentConfigEntity.class);
    }
}