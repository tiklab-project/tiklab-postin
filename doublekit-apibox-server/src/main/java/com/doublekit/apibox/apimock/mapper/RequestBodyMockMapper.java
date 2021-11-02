package com.doublekit.apibox.apimock.mapper;

import com.doublekit.apibox.apidef.entity.AfterScriptPo;
import com.doublekit.apibox.apidef.model.AfterScript;
import com.doublekit.apibox.apimock.entity.RequestBodyMockPo;
import com.doublekit.apibox.apimock.model.RequestBodyMock;
import com.doublekit.beans.annotation.Mapper;

@Mapper(source = RequestBodyMock.class,target = RequestBodyMockPo.class)
public class RequestBodyMockMapper {
}
