package com.doublekit.apibox.apidef.mapper;


import com.doublekit.apibox.apidef.entity.RequestHeaderEntity;
import com.doublekit.apibox.apidef.model.RequestHeader;
import com.doublekit.apibox.apimock.entity.FormParamMockEntity;
import com.doublekit.apibox.apimock.model.FormParamMock;
import com.doublekit.beans.annotation.Mapper;

@Mapper(source = RequestHeader.class,target = RequestHeaderEntity.class)
public class RequestHeaderMapper {
}
