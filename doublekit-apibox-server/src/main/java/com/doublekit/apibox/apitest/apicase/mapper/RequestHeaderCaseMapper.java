package com.doublekit.apibox.apitest.apicase.mapper;


import com.doublekit.apibox.apitest.apicase.entity.RequestHeaderCaseEntity;
import com.doublekit.apibox.apitest.apicase.model.RequestHeaderCase;
import com.doublekit.beans.annotation.Mapper;

@Mapper(source = RequestHeaderCase.class,target = RequestHeaderCaseEntity.class)
public class RequestHeaderCaseMapper {
}
