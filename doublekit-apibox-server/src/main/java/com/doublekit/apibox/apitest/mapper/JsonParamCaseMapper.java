package com.doublekit.apibox.apitest.mapper;


import com.doublekit.apibox.apitest.entity.JsonParamCaseEntity;
import com.doublekit.apibox.apitest.model.JsonParamCase;
import com.doublekit.beans.annotation.Mapper;

@Mapper(source = JsonParamCase.class,target = JsonParamCaseEntity.class)
public class JsonParamCaseMapper {
}
