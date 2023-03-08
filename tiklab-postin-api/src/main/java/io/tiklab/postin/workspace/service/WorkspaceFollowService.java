package io.tiklab.postin.workspace.service;


import io.tiklab.postin.workspace.model.WorkspaceFollow;
import io.tiklab.postin.workspace.model.WorkspaceFollowQuery;
import io.tiklab.core.page.Pagination;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
* 空间关注 服务接口
*/
public interface WorkspaceFollowService {

    /**
    * 创建空间关注
    * @param workspaceFollow
    * @return
    */
    String createWorkspaceFollow(@NotNull @Valid WorkspaceFollow workspaceFollow);

    /**
    * 更新空间关注
    * @param workspaceFollow
    */
    void updateWorkspaceFollow(@NotNull @Valid WorkspaceFollow workspaceFollow);

    /**
    * 删除空间关注
    * @param id
    */
    void deleteWorkspaceFollow(@NotNull String id);

    WorkspaceFollow findOne(@NotNull String id);

    List<WorkspaceFollow> findList(List<String> idList);

    /**
    * 查找空间关注
    * @param id
    * @return
    */
    WorkspaceFollow findWorkspaceFollow(@NotNull String id);

    /**
    * 查找所有空间关注
    * @return
    */
    List<WorkspaceFollow> findAllWorkspaceFollow();

    /**
    * 查询列表空间关注
    * @param workspaceFollowQuery
    * @return
    */
    List<WorkspaceFollow> findWorkspaceFollowList(WorkspaceFollowQuery workspaceFollowQuery);

    /**
    * 按分页查询空间关注
    * @param workspaceFollowQuery
    * @return
    */
    Pagination<WorkspaceFollow> findWorkspaceFollowPage(WorkspaceFollowQuery workspaceFollowQuery);

}