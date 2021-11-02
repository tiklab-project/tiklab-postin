package com.doublekit.apibox.apidef.mapper;


import com.doublekit.apibox.apidef.entity.ResponseHeaderPo;
import com.doublekit.apibox.apidef.model.ResponseHeader;
import com.doublekit.apibox.apimock.entity.FormParamMockPo;
import com.doublekit.apibox.apimock.model.FormParamMock;
import com.doublekit.beans.annotation.Mapper;

@Mapper(source = ResponseHeader.class,target = ResponseHeaderPo.class)
public class ResponseHeaderMapper {
}
