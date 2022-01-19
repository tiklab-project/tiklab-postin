package com.doublekit.apibox.apitest.mapper;

import com.doublekit.apibox.apitest.entity.QueryParamCaseEntity;
import com.doublekit.apibox.apitest.model.QueryParamCase;
import com.doublekit.beans.annotation.Mapper;

@Mapper(source = QueryParamCase.class,target = QueryParamCaseEntity.class)
public class QueryParamCaseMapper {
}
