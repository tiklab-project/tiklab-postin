package com.doublekit.apibox.apimock.mapper;

import com.doublekit.apibox.apimock.entity.JsonResponseMockEntity;
import com.doublekit.apibox.apimock.entity.MockEntity;
import com.doublekit.apibox.apimock.model.JsonResponseMock;
import com.doublekit.apibox.apimock.model.Mock;
import com.doublekit.apibox.workspace.entity.WorkspaceEntity;
import com.doublekit.apibox.workspace.model.Workspace;
import com.doublekit.beans.annotation.Mapper;

@Mapper(source = Mock.class,target = MockEntity.class)
public class MockMapper {
}
