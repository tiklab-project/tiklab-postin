package net.tiklab.postin.workspace.service;

import net.tiklab.postin.workspace.model.WorkspaceQuery;
import net.tiklab.core.page.Pagination;

import net.tiklab.postin.workspace.model.Workspace;
import net.tiklab.join.annotation.FindList;
import net.tiklab.join.annotation.JoinProvider;
import net.tiklab.join.annotation.FindAll;
import net.tiklab.join.annotation.FindOne;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
* 空间 服务接口
*/
@JoinProvider(model = Workspace.class)
public interface WorkspaceService {

    /**
    * 创建空间
    * @param workspace
    * @return
    */
    String createWorkspace(@NotNull @Valid Workspace workspace) throws Exception;

    /**
    * 更新空间
    * @param workspace
    */
    void updateWorkspace(@NotNull @Valid Workspace workspace);

    /**
    * 删除空间
    * @param id
    */
    void deleteWorkspace(@NotNull String id);

    @FindOne
    Workspace findOne(@NotNull String id);

    @FindList
    List<Workspace> findList(List<String> idList);

    /**
    * 查找空间
    * @param id
    * @return
    */
    Workspace findWorkspace(@NotNull String id);

    /**
    * 查找所有空间
    * @return
    */
    @FindAll
    List<Workspace> findAllWorkspace();

    /**
    * 查询列表空间
    * @param workspaceQuery
    * @return
    */
    List<Workspace> findWorkspaceList(WorkspaceQuery workspaceQuery);

    /**
    * 按分页查询空间
    * @param workspaceQuery
    * @return
    */
    Pagination<Workspace> findWorkspacePage(WorkspaceQuery workspaceQuery);


    /**
     * 查询我加入的空间列表
     * @param workspaceQuery
     * @return
     */
    List<Workspace> findWorkspaceJoinList(WorkspaceQuery workspaceQuery);



}