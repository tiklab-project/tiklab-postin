package com.doublekit.apibox.apimock.mapper;


import com.doublekit.apibox.apimock.entity.QueryParamMockPo;
import com.doublekit.apibox.apimock.model.QueryParamMock;
import com.doublekit.beans.annotation.Mapper;

@Mapper(source = QueryParamMock.class,target = QueryParamMockPo.class)
public class QueryParamMockMapper {
}
