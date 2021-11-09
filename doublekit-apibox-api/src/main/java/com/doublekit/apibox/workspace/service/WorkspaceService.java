package com.doublekit.apibox.workspace.service;

import com.doublekit.apibox.workspace.model.WorkspaceQuery;
import com.doublekit.common.Pagination;

import com.doublekit.apibox.workspace.model.Workspace;
import com.doublekit.join.annotation.FindList;
import com.doublekit.join.annotation.Provider;
import com.doublekit.join.annotation.FindAll;
import com.doublekit.join.annotation.FindOne;

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

    @FindOne
    Workspace findOne(@NotNull String id);

    @FindList
    List<Workspace> findList(List<String> idList);

    /**
    * 查找用户
    * @param id
    * @return
    */
    Workspace findWorkspace(@NotNull String id);

    /**
    * 查找所有
    * @return
    */
    @FindAll
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
    Pagination<Workspace> findWorkspacePage(WorkspaceQuery workspaceQuery);


    /**
     * 查询我加入的列表
     * @param workspaceQuery
     * @return
     */
    List<Workspace> findWorkspaceJoinList(WorkspaceQuery workspaceQuery);


}