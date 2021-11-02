package com.doublekit.apibox.apimock.mapper;

import com.doublekit.apibox.apimock.entity.JsonParamMockPo;
import com.doublekit.apibox.apimock.model.JsonParamMock;
import com.doublekit.apibox.workspace.entity.WorkspacePo;
import com.doublekit.apibox.workspace.model.Workspace;
import com.doublekit.beans.annotation.Mapper;

@Mapper(source = JsonParamMock.class,target = JsonParamMockPo.class)
public class JsonParamMockMapper {
}
