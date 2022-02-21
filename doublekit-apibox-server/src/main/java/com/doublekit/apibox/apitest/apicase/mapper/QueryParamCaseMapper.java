package com.doublekit.apibox.apitest.apicase.mapper;

import com.doublekit.apibox.apitest.apicase.entity.QueryParamCaseEntity;
import com.doublekit.apibox.apitest.apicase.model.QueryParamCase;
import com.doublekit.beans.annotation.Mapper;

@Mapper(source = QueryParamCase.class,target = QueryParamCaseEntity.class)
public class QueryParamCaseMapper {
}
