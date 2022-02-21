package com.doublekit.apibox.apitest.apicase.mapper;

import com.doublekit.apibox.apitest.apicase.entity.BinaryParamCaseEntity;
import com.doublekit.apibox.apitest.apicase.model.BinaryParamCase;
import com.doublekit.beans.annotation.Mapper;

@Mapper(source = BinaryParamCase.class,target = BinaryParamCaseEntity.class)
public class BinaryParamCaseMapper {
}
