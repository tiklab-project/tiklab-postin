package com.doublekit.apibox.apimock.mapper;

import com.doublekit.apibox.apidef.entity.AfterScriptEntity;
import com.doublekit.apibox.apidef.model.AfterScript;
import com.doublekit.apibox.apimock.entity.ResponseHeaderMockEntity;
import com.doublekit.apibox.apimock.model.ResponseHeaderMock;
import com.doublekit.beans.annotation.Mapper;

@Mapper(source = ResponseHeaderMock.class,target = ResponseHeaderMockEntity.class)
public class ResponseHeaderMockMapper {
}
