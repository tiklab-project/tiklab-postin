package io.tiklab.postin.workspace.service;


import io.tiklab.join.annotation.JoinProvider;
import io.tiklab.postin.workspace.model.Workspace;
import io.tiklab.postin.workspace.model.WorkspaceTotal;

import javax.validation.constraints.NotNull;

/**
* 空间概况 服务接口
*/
@JoinProvider(model = Workspace.class)
public interface WorkspaceOverviewService {


    /**
     * 查找单个空间中概况
     * @param id
     * @return
     */
    WorkspaceTotal findWorkspaceOverview(@NotNull String id);

}