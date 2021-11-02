package com.doublekit.apibox.apimock.mapper;

import com.doublekit.apibox.apidef.entity.AfterScriptEntity;
import com.doublekit.apibox.apidef.model.AfterScript;
import com.doublekit.apibox.apimock.entity.RequestBodyMockEntity;
import com.doublekit.apibox.apimock.model.RequestBodyMock;
import com.doublekit.beans.annotation.Mapper;

@Mapper(source = RequestBodyMock.class,target = RequestBodyMockEntity.class)
public class RequestBodyMockMapper {
}
