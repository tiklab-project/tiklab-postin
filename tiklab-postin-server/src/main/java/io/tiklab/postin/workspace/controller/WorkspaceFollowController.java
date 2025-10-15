package io.tiklab.postin.workspace.controller;

import io.tiklab.postin.workspace.model.WorkspaceFollow;
import io.tiklab.postin.workspace.model.WorkspaceFollowQuery;
import io.tiklab.postin.workspace.service.WorkspaceFollowService;
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
 * @pi.path:/workspaceFollow
 */
@RestController
@RequestMapping("/workspaceFollow")
public class WorkspaceFollowController {

    private static Logger logger = LoggerFactory.getLogger(WorkspaceFollowController.class);

    @Autowired
    private WorkspaceFollowService workspaceFollowService;

    /**
     * @pi.name:创建空间关注123
     * @pi.url:/createWorkspaceFollow
     * @pi.methodType:post
     * @pi.request-type:json
     * @pi.param: model=io.tiklab.postin.workspace.model.WorkspaceFollow
     */
    @RequestMapping(path="/createWorkspaceFollow",method = RequestMethod.POST)
    public Result<String> createWorkspaceFollow(@RequestBody @NotNull @Valid WorkspaceFollow workspaceFollow){
        String id = workspaceFollowService.createWorkspaceFollow(workspaceFollow);

        return Result.ok(id);
    }


    /**
     * @pi.name:删除空间关注123
     * @pi.url:/deleteWorkspaceFollow
     * @pi.methodType:post
     * @pi.request-type:formdata
     * @pi.param: name=id;dataType=string;value=workspaceId;desc=当前删除的id
     * @pi.param: name=id2;dataType=string;value=workspaceId2;desc=当前删除的id2
     */
    @RequestMapping(path="/deleteWorkspaceFollow",method = RequestMethod.POST)
    public Result<Void> deleteWorkspaceFollow(@NotNull String id){
        workspaceFollowService.deleteWorkspaceFollow(id);

        return Result.ok();
    }

    /**
     * @pi.name:更新空间关注2
     * @pi.url:/updateWorkspaceFollow
     * @pi.methodType:post
     * @pi.request-type:json
     * @pi.param: model=io.tiklab.postin.workspace.model.WorkspaceFollow
     */
    @RequestMapping(path="/updateWorkspaceFollow",method = RequestMethod.POST)
    public Result<Void> updateWorkspaceFollow(@RequestBody @NotNull @Valid WorkspaceFollow workspaceFollow){
        workspaceFollowService.updateWorkspaceFollow(workspaceFollow);

        return Result.ok();
    }

    /**
     * @pi.name:根据id查询空间关注3
     * @pi.url:/findWorkspaceFollow
     * @pi.methodType:post
     * @pi.request-type:formdata
     * @pi.param: name=id;dataType=string;value=workspaceId;
     */
    @RequestMapping(path="/findWorkspaceFollow",method = RequestMethod.POST)
    public Result<WorkspaceFollow> findWorkspaceFollow(@NotNull String id){
        WorkspaceFollow workspaceFollow = workspaceFollowService.findWorkspaceFollow(id);

        return Result.ok(workspaceFollow);
    }

    /**
     * @pi.name:查询所有空间关注
     * @pi.url:/findAllWorkspaceFollow
     * @pi.methodType:post
     * @pi.request-type:none
     */
    @RequestMapping(path="/findAllWorkspaceFollow",method = RequestMethod.POST)
    public Result<List<WorkspaceFollow>> findAllWorkspaceFollow(){
        List<WorkspaceFollow> workspaceFollowList = workspaceFollowService.findAllWorkspaceFollow();

        return Result.ok(workspaceFollowList);
    }

    /**
     * @pi.name:根据查询参数查找空间关注list
     * @pi.url:/findWorkspaceFollowList
     * @pi.methodType:post
     * @pi.request-type:json
     * @pi.param: model=io.tiklab.postin.workspace.model.WorkspaceFollowQuery
     */
    @RequestMapping(path = "/findWorkspaceFollowList",method = RequestMethod.POST)
    public Result<List<WorkspaceFollow>> findWorkspaceFollowList(@RequestBody @Valid @NotNull WorkspaceFollowQuery workspaceFollowQuery){
        List<WorkspaceFollow> workspaceFollowList = workspaceFollowService.findWorkspaceFollowList(workspaceFollowQuery);

        return Result.ok(workspaceFollowList);
    }

    /**
     * @pi.name:根据查询参数按分页查找空间关注
     * @pi.url:/findWorkspaceFollowPage
     * @pi.methodType:post
     * @pi.request-type:json
     * @pi.param: model=io.tiklab.postin.workspace.model.WorkspaceFollowQuery
     */
    @RequestMapping(path = "/findWorkspaceFollowPage",method = RequestMethod.POST)
    public Result<Pagination<WorkspaceFollow>> findWorkspaceFollowPage(@RequestBody @Valid @NotNull WorkspaceFollowQuery workspaceFollowQuery){
        Pagination<WorkspaceFollow> pagination = workspaceFollowService.findWorkspaceFollowPage(workspaceFollowQuery);

        return Result.ok(pagination);
    }

}
