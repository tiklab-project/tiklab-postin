package com.doublekit.apibox.apimock.mapper;

import com.doublekit.apibox.apidef.entity.AfterScriptPo;
import com.doublekit.apibox.apidef.model.AfterScript;
import com.doublekit.apibox.apimock.entity.RequestHeaderMockPo;
import com.doublekit.apibox.apimock.model.RequestHeaderMock;
import com.doublekit.beans.annotation.Mapper;

@Mapper(source = RequestHeaderMock.class,target = RequestHeaderMockPo.class)
public class RequestHeaderMockMapper {
}
