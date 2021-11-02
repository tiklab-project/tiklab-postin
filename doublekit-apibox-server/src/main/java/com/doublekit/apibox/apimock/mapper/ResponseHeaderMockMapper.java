package com.doublekit.apibox.apimock.mapper;

import com.doublekit.apibox.apidef.entity.AfterScriptPo;
import com.doublekit.apibox.apidef.model.AfterScript;
import com.doublekit.apibox.apimock.entity.ResponseHeaderMockPo;
import com.doublekit.apibox.apimock.model.ResponseHeaderMock;
import com.doublekit.beans.annotation.Mapper;

@Mapper(source = ResponseHeaderMock.class,target = ResponseHeaderMockPo.class)
public class ResponseHeaderMockMapper {
}
