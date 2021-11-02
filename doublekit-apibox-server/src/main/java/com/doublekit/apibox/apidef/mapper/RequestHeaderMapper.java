package com.doublekit.apibox.apidef.mapper;


import com.doublekit.apibox.apidef.entity.RequestHeaderPo;
import com.doublekit.apibox.apidef.model.RequestHeader;
import com.doublekit.apibox.apimock.entity.FormParamMockPo;
import com.doublekit.apibox.apimock.model.FormParamMock;
import com.doublekit.beans.annotation.Mapper;

@Mapper(source = RequestHeader.class,target = RequestHeaderPo.class)
public class RequestHeaderMapper {
}
