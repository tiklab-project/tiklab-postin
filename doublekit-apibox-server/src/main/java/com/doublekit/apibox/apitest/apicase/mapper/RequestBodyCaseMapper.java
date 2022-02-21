package com.doublekit.apibox.apitest.apicase.mapper;


import com.doublekit.apibox.apitest.apicase.entity.RequestBodyCaseEntity;
import com.doublekit.apibox.apitest.apicase.model.RequestBodyCase;
import com.doublekit.beans.annotation.Mapper;

@Mapper(source = RequestBodyCase.class,target = RequestBodyCaseEntity.class)
public class RequestBodyCaseMapper {
}
