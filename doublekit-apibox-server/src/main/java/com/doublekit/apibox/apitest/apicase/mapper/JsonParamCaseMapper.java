package com.doublekit.apibox.apitest.apicase.mapper;


import com.doublekit.apibox.apitest.apicase.entity.JsonParamCaseEntity;
import com.doublekit.apibox.apitest.apicase.model.JsonParamCase;
import com.doublekit.beans.annotation.Mapper;

@Mapper(source = JsonParamCase.class,target = JsonParamCaseEntity.class)
public class JsonParamCaseMapper {
}
