package io.tiklab.postin.workspace.service;


import io.tiklab.postin.workspace.model.Workspace;
import io.tiklab.postin.workspace.model.WorkspaceRecent;
import io.tiklab.postin.workspace.model.WorkspaceRecentQuery;
import io.tiklab.core.page.Pagination;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
* 最近访问空间 服务接口
*/
public interface WorkspaceRecentService {

    /**
    * 创建最近访问空间
    * @param workspaceRecent
    * @return
    */
    String createWorkspaceRecent(@NotNull @Valid WorkspaceRecent workspaceRecent);

    /**
    * 更新最近访问空间
    * @param workspaceRecent
    */
    void updateWorkspaceRecent(@NotNull @Valid WorkspaceRecent workspaceRecent);

    /**
    * 删除最近访问空间
    * @param id
    */
    void deleteWorkspaceRecent(@NotNull String id);

    WorkspaceRecent findOne(@NotNull String id);

    List<WorkspaceRecent> findList(List<String> idList);

    /**
    * 查找最近访问空间
    * @param id
    * @return
    */
    WorkspaceRecent findWorkspaceRecent(@NotNull String id);

    /**
    * 查找所有最近访问空间
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
    * 按分页查询最近访问空间
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