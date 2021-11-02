package com.doublekit.apibox.apidef.mapper;


import com.doublekit.apibox.apidef.entity.RawParamPo;
import com.doublekit.apibox.apidef.model.RawParam;
import com.doublekit.apibox.apimock.entity.FormParamMockPo;
import com.doublekit.apibox.apimock.model.FormParamMock;
import com.doublekit.beans.annotation.Mapper;

@Mapper(source = RawParam.class,target = RawParamPo.class)
public class RawParamMapper {
}
