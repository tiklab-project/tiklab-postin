package io.thoughtware.postin.workspace.service;


import io.thoughtware.join.annotation.FindAll;
import io.thoughtware.join.annotation.FindList;
import io.thoughtware.join.annotation.FindOne;
import io.thoughtware.join.annotation.JoinProvider;
import io.thoughtware.postin.workspace.model.Workspace;
import io.thoughtware.postin.workspace.model.WorkspaceRecent;
import io.thoughtware.postin.workspace.model.WorkspaceRecentQuery;
import io.thoughtware.core.page.Pagination;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
* 最近访问空间 服务接口
*/
@JoinProvider(model = WorkspaceRecent.class)
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

    @FindOne
    WorkspaceRecent findOne(@NotNull String id);

    @FindList
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
    @FindAll
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
    Pagination<Workspace> findWorkspaceRecentPage(WorkspaceRecentQuery workspaceRecentQuery);

    /**
     * 设置最近浏览的空间
     * @param workspaceRecent
     */
    void workspaceRecent(@NotNull @Valid WorkspaceRecent workspaceRecent);

}