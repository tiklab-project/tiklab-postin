package com.doublekit.apibox.apimock.mapper;

import com.doublekit.apibox.apidef.entity.AfterScriptEntity;
import com.doublekit.apibox.apidef.model.AfterScript;
import com.doublekit.apibox.apimock.entity.ResponseResultMockEntity;
import com.doublekit.apibox.apimock.model.ResponseResultMock;
import com.doublekit.beans.annotation.Mapper;

@Mapper(source = ResponseResultMock.class,target = ResponseResultMockEntity.class)
public class ResponseResultMockMapper {
}
