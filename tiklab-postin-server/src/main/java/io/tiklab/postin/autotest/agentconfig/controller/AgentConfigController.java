package io.tiklab.postin.autotest.agentconfig.controller;


import io.tiklab.postin.autotest.agentconfig.model.AgentConfig;
import io.tiklab.postin.autotest.agentconfig.model.AgentConfigQuery;
import io.tiklab.postin.autotest.agentconfig.service.AgentConfigService;
import io.tiklab.core.Result;
import io.tiklab.core.page.Pagination;
import io.tiklab.postin.annotation.Api;
import io.tiklab.postin.annotation.ApiMethod;
import io.tiklab.postin.annotation.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import java.util.List;

/**
 * agent配置 控制器
 */
@RestController
@RequestMapping("/agentConfig")
//@Api(name = "AgentConfigController",desc = "agent配置 管理")
public class AgentConfigController {

    private static Logger logger = LoggerFactory.getLogger(AgentConfigController.class);

    @Autowired
    private AgentConfigService agentConfigService;

    @RequestMapping(path="/getConnectList",method = RequestMethod.POST)
    public Result<List> getConnectList(){
        List connectList = agentConfigService.getConnectList();
        return Result.ok(connectList);
    }


    @RequestMapping(path="/createConnect",method = RequestMethod.POST)
    public Result<List> createConnect(@NotNull String agentId){
        String connect = agentConfigService.createConnect(agentId);
        return Result.ok(connect);
    }


    @RequestMapping(path="/createAgentConfig",method = RequestMethod.POST)
//    @ApiMethod(name = "createAgentConfig",desc = "创建agent配置")
//    @ApiParam(name = "agentConfig",desc = "agentConfig",required = true)
    public Result<String> createAgentConfig(@RequestBody @NotNull @Valid AgentConfig agentConfig){
        String id = agentConfigService.createAgentConfig(agentConfig);

        return Result.ok(id);
    }

    @RequestMapping(path="/updateAgentConfig",method = RequestMethod.POST)
//    @ApiMethod(name = "updateAgentConfig",desc = "更新agent配置")
//    @ApiParam(name = "agentConfig",desc = "agentConfig",required = true)
    public Result<Void> updateAgentConfig(@RequestBody @NotNull @Valid AgentConfig agentConfig){
        agentConfigService.updateAgentConfig(agentConfig);

        return Result.ok();
    }

    @RequestMapping(path="/deleteAgentConfig",method = RequestMethod.POST)
//    @ApiMethod(name = "deleteAgentConfig",desc = "删除agent配置")
//    @ApiParam(name = "id",desc = "id",required = true)
    public Result<Void> deleteAgentConfig(@NotNull String id){
        agentConfigService.deleteAgentConfig(id);

        return Result.ok();
    }

    @RequestMapping(path="/findAgentConfig",method = RequestMethod.POST)
//    @ApiMethod(name = "findAgentConfig",desc = "根据id查找agent配置")
//    @ApiParam(name = "id",desc = "id",required = true)
    public Result<AgentConfig> findAgentConfig(@NotNull String id){
        AgentConfig agentConfig = agentConfigService.findAgentConfig(id);

        return Result.ok(agentConfig);
    }

    @RequestMapping(path="/findAllAgentConfig",method = RequestMethod.POST)
//    @ApiMethod(name = "findAllAgentConfig",desc = "查找所有agent配置")
    public Result<List<AgentConfig>> findAllAgentConfig(){
        List<AgentConfig> agentConfigList = agentConfigService.findAllAgentConfig();

        return Result.ok(agentConfigList);
    }

    @RequestMapping(path = "/findAgentConfigList",method = RequestMethod.POST)
//    @ApiMethod(name = "findAgentConfigList",desc = "根据查询参数查询agent配置列表")
//    @ApiParam(name = "agentConfigQuery",desc = "agentConfigQuery",required = true)
    public Result<List<AgentConfig>> findAgentConfigList(@RequestBody @Valid @NotNull AgentConfigQuery agentConfigQuery){
        List<AgentConfig> agentConfigList = agentConfigService.findAgentConfigList(agentConfigQuery);

        return Result.ok(agentConfigList);
    }

    @RequestMapping(path = "/findAgentConfigPage",method = RequestMethod.POST)
//    @ApiMethod(name = "findAgentConfigPage",desc = "根据查询参数按分页查询agent配置")
//    @ApiParam(name = "agentConfigQuery",desc = "agentConfigQuery",required = true)
    public Result<Pagination<AgentConfig>> findAgentConfigPage(@RequestBody @Valid @NotNull AgentConfigQuery agentConfigQuery){
        Pagination<AgentConfig> pagination = agentConfigService.findAgentConfigPage(agentConfigQuery);

        return Result.ok(pagination);
    }





}
