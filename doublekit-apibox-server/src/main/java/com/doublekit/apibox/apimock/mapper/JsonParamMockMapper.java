package com.doublekit.apibox.apimock.mapper;

import com.doublekit.apibox.apimock.entity.JsonParamMockEntity;
import com.doublekit.apibox.apimock.model.JsonParamMock;
import com.doublekit.apibox.workspace.entity.WorkspaceEntity;
import com.doublekit.apibox.workspace.model.Workspace;
import com.doublekit.beans.annotation.Mapper;

@Mapper(source = JsonParamMock.class,target = JsonParamMockEntity.class)
public class JsonParamMockMapper {
}
