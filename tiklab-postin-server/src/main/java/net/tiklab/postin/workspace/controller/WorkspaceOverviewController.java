package net.tiklab.postin.workspace.controller;

import net.tiklab.core.Result;
import net.tiklab.postin.annotation.Api;
import net.tiklab.postin.annotation.ApiMethod;
import net.tiklab.postin.annotation.ApiParam;
import net.tiklab.postin.workspace.model.WorkspaceTotal;
import net.tiklab.postin.workspace.service.WorkspaceOverviewService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotNull;

/**
 * ManagerController
 * Created by Zhangzhihua on 2017/9/25.
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
