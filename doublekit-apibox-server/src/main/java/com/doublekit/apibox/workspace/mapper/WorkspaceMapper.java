package com.doublekit.apibox.workspace.mapper;

import com.doublekit.apibox.workspace.entity.WorkspacePo;
import com.doublekit.apibox.workspace.model.Workspace;
import com.doublekit.beans.annotation.Mapper;

@Mapper(source = Workspace.class,target = WorkspacePo.class)
public class WorkspaceMapper {
}
