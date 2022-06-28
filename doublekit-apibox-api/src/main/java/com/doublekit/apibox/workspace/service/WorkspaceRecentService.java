package com.doublekit.apibox.workspace.service;


import com.doublekit.apibox.workspace.model.WorkspaceRecent;
import com.doublekit.apibox.workspace.model.WorkspaceRecentQuery;
import com.doublekit.core.page.Pagination;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
* WorkspaceRecentService
*/
public interface WorkspaceRecentService {

    /**
    * 创建
    * @param workspaceRecent
    * @return
    */
    String createWorkspaceRecent(@NotNull @Valid WorkspaceRecent workspaceRecent);

    /**
    * 更新
    * @param workspaceRecent
    */
    void updateWorkspaceRecent(@NotNull @Valid WorkspaceRecent workspaceRecent);

    /**
    * 删除
    * @param id
    */
    void deleteWorkspaceRecent(@NotNull String id);

    WorkspaceRecent findOne(@NotNull String id);

    List<WorkspaceRecent> findList(List<String> idList);

    /**
    * 查找
    * @param id
    * @return
    */
    WorkspaceRecent findWorkspaceRecent(@NotNull String id);

    /**
    * 查找所有
    * @return
    */
    List<WorkspaceRecent> findAllWorkspaceRecent();

    /**
    * 查询列表
    * @param workspaceRecentQuery
    * @return
    */
    List<WorkspaceRecent> findWorkspaceRecentList(WorkspaceRecentQuery workspaceRecentQuery);

    /**
    * 按分页查询
    * @param workspaceRecentQuery
    * @return
    */
    Pagination<WorkspaceRecent> findWorkspaceRecentPage(WorkspaceRecentQuery workspaceRecentQuery);

    /**
     * 删除
     * @param workspaceRecent
     */
    void workspaceRecent(@NotNull @Valid WorkspaceRecent workspaceRecent);

}