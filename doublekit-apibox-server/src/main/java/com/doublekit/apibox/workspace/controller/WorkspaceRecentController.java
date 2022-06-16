package com.doublekit.apibox.workspace.controller;

import com.doublekit.apibox.annotation.Api;
import com.doublekit.apibox.workspace.model.WorkspaceRecent;
import com.doublekit.apibox.workspace.model.WorkspaceRecentQuery;
import com.doublekit.apibox.workspace.service.WorkspaceRecentService;
import com.doublekit.apibox.annotation.ApiMethod;
import com.doublekit.apibox.annotation.ApiParam;
import com.doublekit.core.Result;
import com.doublekit.core.page.Pagination;
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
 * WorkspaceRecentController
 */
@RestController
@RequestMapping("/workspaceRecent")
@Api(name = "WorkspaceRecentController",desc = "WorkspaceRecentController")
public class WorkspaceRecentController {

    private static Logger logger = LoggerFactory.getLogger(WorkspaceRecentController.class);

    @Autowired
    private WorkspaceRecentService workspaceRecentService;

    @RequestMapping(path="/createWorkspaceRecent",method = RequestMethod.POST)
    @ApiMethod(name = "createWorkspaceRecent",desc = "createWorkspaceRecent")
    @ApiParam(name = "workspaceRecent",desc = "workspaceRecent",required = true)
    public Result<String> createWorkspaceRecent(@RequestBody @NotNull @Valid WorkspaceRecent workspaceRecent){
        String id = workspaceRecentService.createWorkspaceRecent(workspaceRecent);

        return Result.ok(id);
    }

    @RequestMapping(path="/updateWorkspaceRecent",method = RequestMethod.POST)
    @ApiMethod(name = "updateWorkspaceRecent",desc = "updateWorkspaceRecent")
    @ApiParam(name = "workspaceRecent",desc = "workspaceRecent",required = true)
    public Result<Void> updateWorkspaceRecent(@RequestBody @NotNull @Valid WorkspaceRecent workspaceRecent){
        workspaceRecentService.updateWorkspaceRecent(workspaceRecent);

        return Result.ok();
    }

    @RequestMapping(path="/deleteWorkspaceRecent",method = RequestMethod.POST)
    @ApiMethod(name = "deleteWorkspaceRecent",desc = "deleteWorkspaceRecent")
    @ApiParam(name = "id",desc = "id",required = true)
    public Result<Void> deleteWorkspaceRecent(@NotNull String id){
        workspaceRecentService.deleteWorkspaceRecent(id);

        return Result.ok();
    }

    @RequestMapping(path="/findWorkspaceRecent",method = RequestMethod.POST)
    @ApiMethod(name = "findWorkspaceRecent",desc = "findWorkspaceRecent")
    @ApiParam(name = "id",desc = "id",required = true)
    public Result<WorkspaceRecent> findWorkspaceRecent(@NotNull String id){
        WorkspaceRecent workspaceRecent = workspaceRecentService.findWorkspaceRecent(id);

        return Result.ok(workspaceRecent);
    }

    @RequestMapping(path="/findAllWorkspaceRecent",method = RequestMethod.POST)
    @ApiMethod(name = "findAllWorkspaceRecent",desc = "findAllWorkspaceRecent")
    public Result<List<WorkspaceRecent>> findAllWorkspaceRecent(){
        List<WorkspaceRecent> workspaceRecentList = workspaceRecentService.findAllWorkspaceRecent();

        return Result.ok(workspaceRecentList);
    }

    @RequestMapping(path = "/findWorkspaceRecentList",method = RequestMethod.POST)
    @ApiMethod(name = "findWorkspaceRecentList",desc = "findWorkspaceRecentList")
    @ApiParam(name = "workspaceRecentQuery",desc = "workspaceRecentQuery",required = true)
    public Result<List<WorkspaceRecent>> findWorkspaceRecentList(@RequestBody @Valid @NotNull WorkspaceRecentQuery workspaceRecentQuery){
        List<WorkspaceRecent> workspaceRecentList = workspaceRecentService.findWorkspaceRecentList(workspaceRecentQuery);

        return Result.ok(workspaceRecentList);
    }

    @RequestMapping(path = "/findWorkspaceRecentPage",method = RequestMethod.POST)
    @ApiMethod(name = "findWorkspaceRecentPage",desc = "findWorkspaceRecentPage")
    @ApiParam(name = "workspaceRecentQuery",desc = "workspaceRecentQuery",required = true)
    public Result<Pagination<WorkspaceRecent>> findWorkspaceRecentPage(@RequestBody @Valid @NotNull WorkspaceRecentQuery workspaceRecentQuery){
        Pagination<WorkspaceRecent> pagination = workspaceRecentService.findWorkspaceRecentPage(workspaceRecentQuery);

        return Result.ok(pagination);
    }

    @RequestMapping(path="/workspaceRecent",method = RequestMethod.POST)
    @ApiMethod(name = "workspaceRecent",desc = "workspaceRecent")
    @ApiParam(name = "workspaceRecent",desc = "workspaceRecent",required = true)
    public Result<Void> workspaceRecent(@RequestBody @NotNull @Valid WorkspaceRecent workspaceRecent){
        workspaceRecentService.workspaceRecent(workspaceRecent);

        return Result.ok();
    }

}
