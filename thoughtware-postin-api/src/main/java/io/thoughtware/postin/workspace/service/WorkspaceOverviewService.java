package io.thoughtware.postin.workspace.service;


import io.thoughtware.join.annotation.JoinProvider;
import io.thoughtware.postin.workspace.model.WorkspaceTotal;

import javax.validation.constraints.NotNull;

/**
* 空间概况 服务接口
*/
@JoinProvider(model = WorkspaceTotal.class)
public interface WorkspaceOverviewService {


    /**
     * 查找单个空间中概况
     * @param id
     * @return
     */
    WorkspaceTotal findWorkspaceOverview(@NotNull String id);

}