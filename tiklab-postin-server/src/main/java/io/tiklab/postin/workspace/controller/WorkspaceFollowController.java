package io.tiklab.postin.workspace.controller;

import io.tiklab.postin.annotation.Api;
import io.tiklab.postin.workspace.model.WorkspaceFollow;
import io.tiklab.postin.workspace.model.WorkspaceFollowQuery;
import io.tiklab.postin.workspace.service.WorkspaceFollowService;
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
 * @pi.protocol: http
 * @pi.groupName: 空间关注
 */
@RestController
@RequestMapping("/workspaceFollow")
@Api(name = "WorkspaceFollowController",desc = "WorkspaceFollowController")
public class WorkspaceFollowController {

    private static Logger logger = LoggerFactory.getLogger(WorkspaceFollowController.class);

    @Autowired
    private WorkspaceFollowService workspaceFollowService;

    /**
     * @pi.name:创建空间关注
     * @pi.url:/workspaceFollow/createWorkspaceFollow
     * @pi.method:post
     * @pi.request-type:json
     */
    @RequestMapping(path="/createWorkspaceFollow",method = RequestMethod.POST)
    @ApiMethod(name = "createWorkspaceFollow",desc = "创建空间关注")
    @ApiParam(name = "workspaceFollow",desc = "workspaceFollow",required = true)
    public Result<String> createWorkspaceFollow(@RequestBody @NotNull @Valid WorkspaceFollow workspaceFollow){
        String id = workspaceFollowService.createWorkspaceFollow(workspaceFollow);

        return Result.ok(id);
    }

    /**
     * @pi.name:删除空间关注
     * @pi.url:/workspaceFollow/deleteWorkspaceFollow
     * @pi.method:post
     * @pi.request-type:formdata
     */
    @RequestMapping(path="/deleteWorkspaceFollow",method = RequestMethod.POST)
    @ApiMethod(name = "deleteWorkspaceFollow",desc = "删除空间关注")
    @ApiParam(name = "id",desc = "id",required = true)
    public Result<Void> deleteWorkspaceFollow(@NotNull String id){
        workspaceFollowService.deleteWorkspaceFollow(id);

        return Result.ok();
    }

    @RequestMapping(path="/updateWorkspaceFollow",method = RequestMethod.POST)
    @ApiMethod(name = "updateWorkspaceFollow",desc = "更新空间关注")
    @ApiParam(name = "workspaceFollow",desc = "workspaceFollow",required = true)
    public Result<Void> updateWorkspaceFollow(@RequestBody @NotNull @Valid WorkspaceFollow workspaceFollow){
        workspaceFollowService.updateWorkspaceFollow(workspaceFollow);

        return Result.ok();
    }


    @RequestMapping(path="/findWorkspaceFollow",method = RequestMethod.POST)
    @ApiMethod(name = "findWorkspaceFollow",desc = "根据id查询空间关注")
    @ApiParam(name = "id",desc = "id",required = true)
    public Result<WorkspaceFollow> findWorkspaceFollow(@NotNull String id){
        WorkspaceFollow workspaceFollow = workspaceFollowService.findWorkspaceFollow(id);

        return Result.ok(workspaceFollow);
    }

    @RequestMapping(path="/findAllWorkspaceFollow",method = RequestMethod.POST)
    @ApiMethod(name = "findAllWorkspaceFollow",desc = "查询所有空间关注")
    public Result<List<WorkspaceFollow>> findAllWorkspaceFollow(){
        List<WorkspaceFollow> workspaceFollowList = workspaceFollowService.findAllWorkspaceFollow();

        return Result.ok(workspaceFollowList);
    }

    @RequestMapping(path = "/findWorkspaceFollowList",method = RequestMethod.POST)
    @ApiMethod(name = "findWorkspaceFollowList",desc = "根据查询参数查找空间关注")
    @ApiParam(name = "workspaceFollowQuery",desc = "workspaceFollowQuery",required = true)
    public Result<List<WorkspaceFollow>> findWorkspaceFollowList(@RequestBody @Valid @NotNull WorkspaceFollowQuery workspaceFollowQuery){
        List<WorkspaceFollow> workspaceFollowList = workspaceFollowService.findWorkspaceFollowList(workspaceFollowQuery);

        return Result.ok(workspaceFollowList);
    }

    @RequestMapping(path = "/findWorkspaceFollowPage",method = RequestMethod.POST)
    @ApiMethod(name = "findWorkspaceFollowPage",desc = "根据查询参数按分页查找空间关注")
    @ApiParam(name = "workspaceFollowQuery",desc = "workspaceFollowQuery",required = true)
    public Result<Pagination<WorkspaceFollow>> findWorkspaceFollowPage(@RequestBody @Valid @NotNull WorkspaceFollowQuery workspaceFollowQuery){
        Pagination<WorkspaceFollow> pagination = workspaceFollowService.findWorkspaceFollowPage(workspaceFollowQuery);

        return Result.ok(pagination);
    }

}
