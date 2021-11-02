package com.doublekit.apibox.apimock.mapper;

import com.doublekit.apibox.apimock.entity.JsonResponseMockPo;
import com.doublekit.apibox.apimock.entity.MockPo;
import com.doublekit.apibox.apimock.model.JsonResponseMock;
import com.doublekit.apibox.apimock.model.Mock;
import com.doublekit.apibox.workspace.entity.WorkspacePo;
import com.doublekit.apibox.workspace.model.Workspace;
import com.doublekit.beans.annotation.Mapper;

@Mapper(source = Mock.class,target = MockPo.class)
public class MockMapper {
}
