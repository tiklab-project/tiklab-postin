package com.doublekit.apibox.apitest.apicase.mapper;


import com.doublekit.apibox.apitest.apicase.entity.RawParamCaseEntity;
import com.doublekit.apibox.apitest.apicase.model.RawParamCase;
import com.doublekit.beans.annotation.Mapper;

@Mapper(source = RawParamCase.class,target = RawParamCaseEntity.class)
public class RawParamCaseMapper {
}
