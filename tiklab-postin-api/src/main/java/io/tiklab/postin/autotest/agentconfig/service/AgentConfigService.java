package io.tiklab.postin.autotest.agentconfig.service;

import io.tiklab.core.Result;
import io.tiklab.core.page.Pagination;
import io.tiklab.postin.autotest.agentconfig.model.AgentConfig;
import io.tiklab.postin.autotest.agentconfig.model.AgentConfigQuery;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
* agent配置 服务接口
*/
public interface AgentConfigService {

    /**
    * 创建agent配置
    * @param agentConfig
    * @return
    */
    String createAgentConfig(@NotNull @Valid AgentConfig agentConfig);

    /**
    * 更新agent配置
    * @param agentConfig
    */
    void updateAgentConfig(@NotNull @Valid AgentConfig agentConfig);

    /**
    * 删除agent配置
    * @param id
    */
    void deleteAgentConfig(@NotNull String id);

    AgentConfig findOne(@NotNull String id);

    List<AgentConfig> findList(List<String> idList);

    /**
    * 根据id查找agent配置
    * @param id
    * @return
    */
    AgentConfig findAgentConfig(@NotNull String id);

    /**
    * 查找所有agent配置
    * @return
    */
    List<AgentConfig> findAllAgentConfig();

    /**
    * 根据查询参数查询agent配置列表
    * @param agentConfigQuery
    * @return
    */
    List<AgentConfig> findAgentConfigList(AgentConfigQuery agentConfigQuery);

    /**
    * 根据查询参数按分页查询agent配置
    * @param agentConfigQuery
    * @return
    */
    Pagination<AgentConfig> findAgentConfigPage(AgentConfigQuery agentConfigQuery);


    /**
     * 获取单个agent
     */
    String getAgent();

    /**
     * 获取在线agent
     * @return
     */
    List<AgentConfig> getAgentList();

    /**
     * 获取将要连接的agent
     * @return
     */
    List getConnectList();

    /**
     * 创建连接
     * @param agentId
     * @return
     */
    String createConnect(@NotNull String agentId);


}