package com.doublekit.apibox.apidef.mapper;


import com.doublekit.apibox.apidef.entity.QueryParamEntity;
import com.doublekit.apibox.apidef.model.QueryParam;
import com.doublekit.apibox.apimock.entity.FormParamMockEntity;
import com.doublekit.apibox.apimock.model.FormParamMock;
import com.doublekit.beans.annotation.Mapper;

@Mapper(source = QueryParam.class,target = QueryParamEntity.class)
public class QueryParamMapper {
}
