package io.tiklab.postin.workspace.controller;

import io.tiklab.core.Result;
import io.tiklab.postin.annotation.Api;
import io.tiklab.postin.annotation.ApiMethod;
import io.tiklab.postin.annotation.ApiParam;
import io.tiklab.postin.workspace.model.WorkspaceTotal;
import io.tiklab.postin.workspace.service.WorkspaceOverviewService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotNull;

/**
 * 空间里概况 控制器
 */
@RestController
@RequestMapping("/workspace")
@Api(name = "WorkspaceController",desc = "空间管理")
public class WorkspaceOverviewController {

    private static Logger logger = LoggerFactory.getLogger(WorkspaceOverviewController.class);

    @Autowired
    private WorkspaceOverviewService workspaceOverviewService;


    @RequestMapping(path="/findWorkspaceTotal",method = RequestMethod.POST)
    @ApiMethod(name = "findWorkspaceTotal",desc = "根据空间ID查找单个空间中概要")
    @ApiParam(name = "id",desc = "空间ID",required = true)
    public Result<WorkspaceTotal> findWorkspaceTotal(@NotNull String id){
        WorkspaceTotal workspace = workspaceOverviewService.findWorkspaceOverview(id);

        return Result.ok(workspace);
    }

}
