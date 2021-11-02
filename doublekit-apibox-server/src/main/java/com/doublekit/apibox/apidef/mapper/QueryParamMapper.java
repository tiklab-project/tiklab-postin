package com.doublekit.apibox.apidef.mapper;


import com.doublekit.apibox.apidef.entity.QueryParamPo;
import com.doublekit.apibox.apidef.model.QueryParam;
import com.doublekit.apibox.apimock.entity.FormParamMockPo;
import com.doublekit.apibox.apimock.model.FormParamMock;
import com.doublekit.beans.annotation.Mapper;

@Mapper(source = QueryParam.class,target = QueryParamPo.class)
public class QueryParamMapper {
}
