package com.doublekit.apibox.apidef.mapper;


import com.doublekit.apibox.apidef.entity.ResponseHeaderEntity;
import com.doublekit.apibox.apidef.model.ResponseHeader;
import com.doublekit.apibox.apimock.entity.FormParamMockEntity;
import com.doublekit.apibox.apimock.model.FormParamMock;
import com.doublekit.beans.annotation.Mapper;

@Mapper(source = ResponseHeader.class,target = ResponseHeaderEntity.class)
public class ResponseHeaderMapper {
}
