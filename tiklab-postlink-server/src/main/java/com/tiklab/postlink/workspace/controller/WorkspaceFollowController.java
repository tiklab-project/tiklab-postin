package com.tiklab.postlink.workspace.controller;

import com.tiklab.postlink.annotation.Api;
import com.tiklab.postlink.workspace.model.WorkspaceFollow;
import com.tiklab.postlink.workspace.model.WorkspaceFollowQuery;
import com.tiklab.postlink.workspace.service.WorkspaceFollowService;
import com.tiklab.postlink.annotation.ApiMethod;
import com.tiklab.postlink.annotation.ApiParam;
import com.tiklab.core.Result;
import com.tiklab.core.page.Pagination;
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
 * WorkspaceFollowController
 */
@RestController
@RequestMapping("/workspaceFollow")
@Api(name = "WorkspaceFollowController",desc = "WorkspaceFollowController")
public class WorkspaceFollowController {

    private static Logger logger = LoggerFactory.getLogger(WorkspaceFollowController.class);

    @Autowired
    private WorkspaceFollowService workspaceFollowService;

    @RequestMapping(path="/createWorkspaceFollow",method = RequestMethod.POST)
    @ApiMethod(name = "createWorkspaceFollow",desc = "createWorkspaceFollow")
    @ApiParam(name = "workspaceFollow",desc = "workspaceFollow",required = true)
    public Result<String> createWorkspaceFollow(@RequestBody @NotNull @Valid WorkspaceFollow workspaceFollow){
        String id = workspaceFollowService.createWorkspaceFollow(workspaceFollow);

        return Result.ok(id);
    }

    @RequestMapping(path="/updateWorkspaceFollow",method = RequestMethod.POST)
    @ApiMethod(name = "updateWorkspaceFollow",desc = "updateWorkspaceFollow")
    @ApiParam(name = "workspaceFollow",desc = "workspaceFollow",required = true)
    public Result<Void> updateWorkspaceFollow(@RequestBody @NotNull @Valid WorkspaceFollow workspaceFollow){
        workspaceFollowService.updateWorkspaceFollow(workspaceFollow);

        return Result.ok();
    }

    @RequestMapping(path="/deleteWorkspaceFollow",method = RequestMethod.POST)
    @ApiMethod(name = "deleteWorkspaceFollow",desc = "deleteWorkspaceFollow")
    @ApiParam(name = "id",desc = "id",required = true)
    public Result<Void> deleteWorkspaceFollow(@NotNull String id){
        workspaceFollowService.deleteWorkspaceFollow(id);

        return Result.ok();
    }

    @RequestMapping(path="/findWorkspaceFollow",method = RequestMethod.POST)
    @ApiMethod(name = "findWorkspaceFollow",desc = "findWorkspaceFollow")
    @ApiParam(name = "id",desc = "id",required = true)
    public Result<WorkspaceFollow> findWorkspaceFollow(@NotNull String id){
        WorkspaceFollow workspaceFollow = workspaceFollowService.findWorkspaceFollow(id);

        return Result.ok(workspaceFollow);
    }

    @RequestMapping(path="/findAllWorkspaceFollow",method = RequestMethod.POST)
    @ApiMethod(name = "findAllWorkspaceFollow",desc = "findAllWorkspaceFollow")
    public Result<List<WorkspaceFollow>> findAllWorkspaceFollow(){
        List<WorkspaceFollow> workspaceFollowList = workspaceFollowService.findAllWorkspaceFollow();

        return Result.ok(workspaceFollowList);
    }

    @RequestMapping(path = "/findWorkspaceFollowList",method = RequestMethod.POST)
    @ApiMethod(name = "findWorkspaceFollowList",desc = "findWorkspaceFollowList")
    @ApiParam(name = "workspaceFollowQuery",desc = "workspaceFollowQuery",required = true)
    public Result<List<WorkspaceFollow>> findWorkspaceFollowList(@RequestBody @Valid @NotNull WorkspaceFollowQuery workspaceFollowQuery){
        List<WorkspaceFollow> workspaceFollowList = workspaceFollowService.findWorkspaceFollowList(workspaceFollowQuery);

        return Result.ok(workspaceFollowList);
    }

    @RequestMapping(path = "/findWorkspaceFollowPage",method = RequestMethod.POST)
    @ApiMethod(name = "findWorkspaceFollowPage",desc = "findWorkspaceFollowPage")
    @ApiParam(name = "workspaceFollowQuery",desc = "workspaceFollowQuery",required = true)
    public Result<Pagination<WorkspaceFollow>> findWorkspaceFollowPage(@RequestBody @Valid @NotNull WorkspaceFollowQuery workspaceFollowQuery){
        Pagination<WorkspaceFollow> pagination = workspaceFollowService.findWorkspaceFollowPage(workspaceFollowQuery);

        return Result.ok(pagination);
    }

}
