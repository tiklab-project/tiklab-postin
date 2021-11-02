package com.doublekit.apibox.apimock.mapper;

import com.doublekit.apibox.apidef.entity.AfterScriptEntity;
import com.doublekit.apibox.apidef.model.AfterScript;
import com.doublekit.apibox.apimock.entity.ResponseMockEntity;
import com.doublekit.apibox.apimock.model.ResponseMock;
import com.doublekit.beans.annotation.Mapper;

@Mapper(source = ResponseMock.class,target = ResponseMockEntity.class)
public class ResponseMockMapper {
}
