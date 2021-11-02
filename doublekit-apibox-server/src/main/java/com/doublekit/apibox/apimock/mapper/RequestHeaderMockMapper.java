package com.doublekit.apibox.apimock.mapper;

import com.doublekit.apibox.apidef.entity.AfterScriptEntity;
import com.doublekit.apibox.apidef.model.AfterScript;
import com.doublekit.apibox.apimock.entity.RequestHeaderMockEntity;
import com.doublekit.apibox.apimock.model.RequestHeaderMock;
import com.doublekit.beans.annotation.Mapper;

@Mapper(source = RequestHeaderMock.class,target = RequestHeaderMockEntity.class)
public class RequestHeaderMockMapper {
}
