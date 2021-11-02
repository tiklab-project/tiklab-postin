package com.doublekit.apibox.apimock.mapper;

import com.doublekit.apibox.apidef.entity.AfterScriptPo;
import com.doublekit.apibox.apidef.model.AfterScript;
import com.doublekit.apibox.apimock.entity.ResponseResultMockPo;
import com.doublekit.apibox.apimock.model.ResponseResultMock;
import com.doublekit.beans.annotation.Mapper;

@Mapper(source = ResponseResultMock.class,target = ResponseResultMockPo.class)
public class ResponseResultMockMapper {
}
