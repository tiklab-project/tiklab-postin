package com.doublekit.apibox.apitest.mapper;


import com.doublekit.apibox.apitest.entity.RawParamCaseEntity;
import com.doublekit.apibox.apitest.model.RawParamCase;
import com.doublekit.apibox.apitest.model.RequestBodyCase;
import com.doublekit.beans.annotation.Mapper;

@Mapper(source = RequestBodyCase.class,target = RawParamCaseEntity.class)
public class RequestBodyCaseMapper {
}