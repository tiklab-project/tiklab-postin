package net.tiklab.postin.workspace.service;


import net.tiklab.postin.workspace.model.WorkspaceFollow;
import net.tiklab.postin.workspace.model.WorkspaceFollowQuery;
import net.tiklab.core.page.Pagination;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
* WorkspaceFollowService
*/
public interface WorkspaceFollowService {

    /**
    * 创建
    * @param workspaceFollow
    * @return
    */
    String createWorkspaceFollow(@NotNull @Valid WorkspaceFollow workspaceFollow);

    /**
    * 更新
    * @param workspaceFollow
    */
    void updateWorkspaceFollow(@NotNull @Valid WorkspaceFollow workspaceFollow);

    /**
    * 删除
    * @param id
    */
    void deleteWorkspaceFollow(@NotNull String id);

    WorkspaceFollow findOne(@NotNull String id);

    List<WorkspaceFollow> findList(List<String> idList);

    /**
    * 查找
    * @param id
    * @return
    */
    WorkspaceFollow findWorkspaceFollow(@NotNull String id);

    /**
    * 查找所有
    * @return
    */
    List<WorkspaceFollow> findAllWorkspaceFollow();

    /**
    * 查询列表
    * @param workspaceFollowQuery
    * @return
    */
    List<WorkspaceFollow> findWorkspaceFollowList(WorkspaceFollowQuery workspaceFollowQuery);

    /**
    * 按分页查询
    * @param workspaceFollowQuery
    * @return
    */
    Pagination<WorkspaceFollow> findWorkspaceFollowPage(WorkspaceFollowQuery workspaceFollowQuery);

}