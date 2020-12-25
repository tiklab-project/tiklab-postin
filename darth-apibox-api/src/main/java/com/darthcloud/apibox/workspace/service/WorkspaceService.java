package com.darthcloud.apibox.workspace.service;

import com.darthcloud.apibox.workspace.model.WorkspaceQuery;
import com.darthcloud.common.Pagination;

import com.darthcloud.apibox.workspace.model.Workspace;
import com.darthcloud.join.annotation.Provider;
import com.darthcloud.join.annotation.QueryAll;
import com.darthcloud.join.annotation.QueryOne;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
* 用户服务接口
*/
@Provider(model = Workspace.class)
public interface WorkspaceService {

    /**
    * 创建用户
    * @param workspace
    * @return
    */
    String createWorkspace(@NotNull @Valid Workspace workspace);

    /**
    * 更新用户
    * @param workspace
    */
    void updateWorkspace(@NotNull @Valid Workspace workspace);

    /**
    * 删除用户
    * @param id
    */
    void deleteWorkspace(@NotNull String id);

    /**
    * 查找用户
    * @param id
    * @return
    */
    @QueryOne
    Workspace findWorkspace(@NotNull String id);

    /**
    * 查找所有
    * @return
    */
    @QueryAll
    List<Workspace> findAllWorkspace();

    /**
    * 查询列表
    * @param workspaceQuery
    * @return
    */
    List<Workspace> findWorkspaceList(WorkspaceQuery workspaceQuery);

    /**
    * 按分页查询
    * @param workspaceQuery
    * @return
    */
    Pagination<List<Workspace>> findWorkspacePage(WorkspaceQuery workspaceQuery);

}