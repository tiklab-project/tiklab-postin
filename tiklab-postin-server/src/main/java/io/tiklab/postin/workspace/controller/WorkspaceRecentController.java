package io.tiklab.postin.workspace.controller;

import io.tiklab.postin.annotation.Api;
import io.tiklab.postin.workspace.model.Workspace;
import io.tiklab.postin.workspace.model.WorkspaceRecent;
import io.tiklab.postin.workspace.model.WorkspaceRecentQuery;
import io.tiklab.postin.workspace.service.WorkspaceRecentService;
import io.tiklab.postin.annotation.ApiMethod;
import io.tiklab.postin.annotation.ApiParam;
import io.tiklab.core.Result;
import io.tiklab.core.page.Pagination;
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
 * 最近访问空间 控制器
 */
@RestController
@RequestMapping("/workspaceRecent")
//@Api(name = "WorkspaceRecentController",desc = "WorkspaceRecentController")
public class WorkspaceRecentController {

    private static Logger logger = LoggerFactory.getLogger(WorkspaceRecentController.class);

    @Autowired
    private WorkspaceRecentService workspaceRecentService;

    @RequestMapping(path="/createWorkspaceRecent",method = RequestMethod.POST)
//    @ApiMethod(name = "createWorkspaceRecent",desc = "创建最近访问空间")
//     @ApiParam(name = "workspaceRecent",desc = "workspaceRecent",required = true)
    public Result<String> createWorkspaceRecent(@RequestBody @NotNull @Valid WorkspaceRecent workspaceRecent){
        String id = workspaceRecentService.createWorkspaceRecent(workspaceRecent);

        return Result.ok(id);
    }

    @RequestMapping(path="/updateWorkspaceRecent",method = RequestMethod.POST)
//    @ApiMethod(name = "updateWorkspaceRecent",desc = "更新最近访问空间")
//     @ApiParam(name = "workspaceRecent",desc = "workspaceRecent",required = true)
    public Result<Void> updateWorkspaceRecent(@RequestBody @NotNull @Valid WorkspaceRecent workspaceRecent){
        workspaceRecentService.updateWorkspaceRecent(workspaceRecent);

        return Result.ok();
    }

    @RequestMapping(path="/deleteWorkspaceRecent",method = RequestMethod.POST)
//    @ApiMethod(name = "deleteWorkspaceRecent",desc = "删除最近访问空间")
//     @ApiParam(name = "id",desc = "id",required = true)
    public Result<Void> deleteWorkspaceRecent(@NotNull String id){
        workspaceRecentService.deleteWorkspaceRecent(id);

        return Result.ok();
    }

    @RequestMapping(path="/findWorkspaceRecent",method = RequestMethod.POST)
//    @ApiMethod(name = "findWorkspaceRecent",desc = "通过id查找最近访问空间")
//     @ApiParam(name = "id",desc = "id",required = true)
    public Result<WorkspaceRecent> findWorkspaceRecent(@NotNull String id){
        WorkspaceRecent workspaceRecent = workspaceRecentService.findWorkspaceRecent(id);

        return Result.ok(workspaceRecent);
    }

    @RequestMapping(path="/findAllWorkspaceRecent",method = RequestMethod.POST)
//    @ApiMethod(name = "findAllWorkspaceRecent",desc = "查找所有最近访问空间")
    public Result<List<WorkspaceRecent>> findAllWorkspaceRecent(){
        List<WorkspaceRecent> workspaceRecentList = workspaceRecentService.findAllWorkspaceRecent();

        return Result.ok(workspaceRecentList);
    }

    @RequestMapping(path = "/findWorkspaceRecentList",method = RequestMethod.POST)
//    @ApiMethod(name = "findWorkspaceRecentList",desc = "根据查询参数查找最近访问空间")
//     @ApiParam(name = "workspaceRecentQuery",desc = "workspaceRecentQuery",required = true)
    public Result<List<WorkspaceRecent>> findWorkspaceRecentList(@RequestBody @Valid @NotNull WorkspaceRecentQuery workspaceRecentQuery){
        List<WorkspaceRecent> workspaceRecentList = workspaceRecentService.findWorkspaceRecentList(workspaceRecentQuery);

        return Result.ok(workspaceRecentList);
    }

    @RequestMapping(path = "/findWorkspaceRecentPage",method = RequestMethod.POST)
//    @ApiMethod(name = "findWorkspaceRecentPage",desc = "根据查询参数按分页查找最近访问空间")
//     @ApiParam(name = "workspaceRecentQuery",desc = "workspaceRecentQuery",required = true)
    public Result<Pagination<WorkspaceRecent>> findWorkspaceRecentPage(@RequestBody @Valid @NotNull WorkspaceRecentQuery workspaceRecentQuery){
        Pagination<WorkspaceRecent> pagination = workspaceRecentService.findWorkspaceRecentPage(workspaceRecentQuery);

        return Result.ok(pagination);
    }

    @RequestMapping(path="/workspaceRecent",method = RequestMethod.POST)
//    @ApiMethod(name = "workspaceRecent",desc = "设置最近访问空间")
//     @ApiParam(name = "workspaceRecent",desc = "workspaceRecent",required = true)
    public Result<Void> workspaceRecent(@RequestBody @NotNull @Valid WorkspaceRecent workspaceRecent){
        workspaceRecentService.workspaceRecent(workspaceRecent);

        return Result.ok();
    }

}
