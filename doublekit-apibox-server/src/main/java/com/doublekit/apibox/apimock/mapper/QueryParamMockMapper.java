package com.doublekit.apibox.apimock.mapper;


import com.doublekit.apibox.apimock.entity.QueryParamMockEntity;
import com.doublekit.apibox.apimock.model.QueryParamMock;
import com.doublekit.beans.annotation.Mapper;

@Mapper(source = QueryParamMock.class,target = QueryParamMockEntity.class)
public class QueryParamMockMapper {
}
