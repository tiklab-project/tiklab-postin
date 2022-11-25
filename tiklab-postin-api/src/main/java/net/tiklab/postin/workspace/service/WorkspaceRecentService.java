package net.tiklab.postin.workspace.service;


import net.tiklab.postin.workspace.model.Workspace;
import net.tiklab.postin.workspace.model.WorkspaceRecent;
import net.tiklab.postin.workspace.model.WorkspaceRecentQuery;
import net.tiklab.core.page.Pagination;

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
    * 查询最近访问的列表，返回的是空间list
    * @param workspaceRecentQuery
    * @return
    */
    List<Workspace> findWorkspaceRecentList(WorkspaceRecentQuery workspaceRecentQuery);

    /**
     * 查询最近访问的列表，返回的是最近访问的list
     * @param workspaceRecentQuery
     * @return
     */
    List<WorkspaceRecent> findRecentList(WorkspaceRecentQuery workspaceRecentQuery);


    /**
    * 按分页查询
    * @param workspaceRecentQuery
    * @return
    */
    Pagination<WorkspaceRecent> findWorkspaceRecentPage(WorkspaceRecentQuery workspaceRecentQuery);

    /**
     * 设置最近浏览的空间
     * @param workspaceRecent
     */
    void workspaceRecent(@NotNull @Valid WorkspaceRecent workspaceRecent);

}