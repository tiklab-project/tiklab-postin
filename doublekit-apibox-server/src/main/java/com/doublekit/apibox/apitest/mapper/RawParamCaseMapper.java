package com.doublekit.apibox.apitest.mapper;


import com.doublekit.apibox.apitest.entity.RawParamCasePo;
import com.doublekit.apibox.apitest.model.RawParamCase;
import com.doublekit.beans.annotation.Mapper;

@Mapper(source = RawParamCase.class,target = RawParamCasePo.class)
public class RawParamCaseMapper {
}
