package com.doublekit.apibox.apitest.mapper;


import com.doublekit.apibox.apitest.entity.RequestHeaderCaseEntity;
import com.doublekit.apibox.apitest.model.RequestHeaderCase;
import com.doublekit.beans.annotation.Mapper;

@Mapper(source = RequestHeaderCase.class,target = RequestHeaderCaseEntity.class)
public class RequestHeaderCaseMapper {
}
