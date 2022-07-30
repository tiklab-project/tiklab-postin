package com.tiklab.postin.workspace.controller;

import com.tiklab.postin.annotation.Api;
import com.tiklab.postin.workspace.model.Workspace;
import com.tiklab.postin.workspace.model.WorkspaceHomeTotal;
import com.tiklab.postin.workspace.model.WorkspaceQuery;
import com.tiklab.postin.workspace.model.WorkspaceTotal;
import com.tiklab.postin.workspace.service.WorkspaceService;
import com.tiklab.core.page.Pagination;
import com.tiklab.core.Result;
import com.tiklab.postin.annotation.ApiMethod;
import com.tiklab.postin.annotation.ApiParam;
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
 * ManagerController
 * Created by Zhangzhihua on 2017/9/25.
 */
@RestController
@RequestMapping("/workspace")
@Api(name = "WorkspaceController",desc = "空间管理")
public class WorkspaceController {

    private static Logger logger = LoggerFactory.getLogger(WorkspaceController.class);

    @Autowired
    private WorkspaceService workspaceService;

    @RequestMapping(path="/createWorkspace",method = RequestMethod.POST)
    @ApiMethod(name = "createWorkspace",desc = "创建空间")
    @ApiParam(name = "workspace",desc = "空间DTO",required = true)
    public Result<String> createWorkspace(@RequestBody @NotNull @Valid @ApiParam Workspace workspace){
        String id = workspaceService.createWorkspace(workspace);

        return Result.ok(id);
    }

    @RequestMapping(path="/updateWorkspace",method = RequestMethod.POST)
    @ApiMethod(name = "updateWorkspace",desc = "更新空间")
    @ApiParam(name = "workspace",desc = "空间DTO",required = true)
    public Result<Void> updateWorkspace(@RequestBody @NotNull @Valid @ApiParam Workspace workspace){
        workspaceService.updateWorkspace(workspace);

        return Result.ok();
    }

    @RequestMapping(path="/deleteWorkspace",method = RequestMethod.POST)
    @ApiMethod(name = "deleteWorkspace",desc = "根据空间ID删除空间")
    @ApiParam(name = "id",desc = "空间ID",required = true)
    public Result<Void> deleteWorkspace(@NotNull String id){
        workspaceService.deleteWorkspace(id);

        return Result.ok();
    }

    @RequestMapping(path="/findWorkspace",method = RequestMethod.POST)
    @ApiMethod(name = "findWorkspace",desc = "根据空间ID查找空间")
    @ApiParam(name = "id",desc = "空间ID",required = true)
    public Result<Workspace> findWorkspace(@NotNull String id){
        Workspace workspace = workspaceService.findWorkspace(id);

        return Result.ok(workspace);
    }

    @RequestMapping(path="/findAllWorkspace",method = RequestMethod.POST)
    @ApiMethod(name = "findAllWorkspace",desc = "查找所有空间")
    public Result<List<Workspace>> findAllWorkspace(){
        List<Workspace> workspaceList = workspaceService.findAllWorkspace();

        return Result.ok(workspaceList);
    }


    @RequestMapping(path = "/findWorkspaceList",method = RequestMethod.POST)
    @ApiMethod(name = "findWorkspaceList",desc = "根据查询对象查询空间列表")
    @ApiParam(name = "workspaceQuery",desc = "查询对象",required = true)
    public Result<List<Workspace>> findWorkspaceList(@RequestBody @Valid @NotNull WorkspaceQuery workspaceQuery){
        List<Workspace> workspaceJoinList = workspaceService.findWorkspaceList(workspaceQuery);

        return Result.ok(workspaceJoinList);
    }


    @RequestMapping(path = "/findWorkspacePage",method = RequestMethod.POST)
    @ApiMethod(name = "findWorkspacePage",desc = "根据查询对象按分页查询空间")
    @ApiParam(name = "workspaceQuery",desc = "查询对象",required = true)
    public Result<Pagination<Workspace>> findWorkspacePage(@RequestBody @Valid @NotNull WorkspaceQuery workspaceQuery){
        Pagination<Workspace> pagination = workspaceService.findWorkspacePage(workspaceQuery);

        return Result.ok(pagination);
    }

    @RequestMapping(path = "/findWorkspaceJoinList",method = RequestMethod.POST)
    @ApiMethod(name = "findWorkspaceJoinList",desc = "根据查询对象查询空间我参加的列表")
    @ApiParam(name = "workspaceQuery",desc = "查询对象",required = true)
    public Result<List<Workspace>> findWorkspaceJoinList(@RequestBody @Valid @NotNull WorkspaceQuery workspaceQuery){
        List<Workspace> workspaceList = workspaceService.findWorkspaceJoinList(workspaceQuery);

        return Result.ok(workspaceList);
    }

    @RequestMapping(path="/findWorkspaceHomeTotal",method = RequestMethod.POST)
    @ApiMethod(name = "findWorkspaceHomeTotal",desc = "根据userID查找首页中的空间概要")
    @ApiParam(name = "userId",desc = "userID",required = true)
    public Result<WorkspaceHomeTotal> findWorkspaceHomeTotal(@NotNull String userId){
        WorkspaceHomeTotal workspace = workspaceService.findWorkspaceHomeTotal(userId);

        return Result.ok(workspace);
    }

    @RequestMapping(path="/findWorkspaceTotal",method = RequestMethod.POST)
    @ApiMethod(name = "findWorkspaceTotal",desc = "根据空间ID查找单个空间中概要")
    @ApiParam(name = "id",desc = "空间ID",required = true)
    public Result<WorkspaceTotal> findWorkspaceTotal(@NotNull String id){
        WorkspaceTotal workspace = workspaceService.findWorkspaceTotal(id);

        return Result.ok(workspace);
    }

}
