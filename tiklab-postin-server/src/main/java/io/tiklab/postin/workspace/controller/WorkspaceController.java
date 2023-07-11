package io.tiklab.postin.workspace.controller;

import io.tiklab.postin.annotation.Api;
import io.tiklab.postin.workspace.model.Workspace;
import io.tiklab.postin.workspace.model.WorkspaceQuery;
import io.tiklab.postin.workspace.service.WorkspaceService;
import io.tiklab.core.page.Pagination;
import io.tiklab.core.Result;
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
 * 空间 控制器
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
    public Result<String> createWorkspace(@RequestBody @NotNull @Valid @ApiParam Workspace workspace) throws Exception {
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


}
