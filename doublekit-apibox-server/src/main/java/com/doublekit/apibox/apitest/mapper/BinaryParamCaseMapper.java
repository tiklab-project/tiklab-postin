package com.doublekit.apibox.apitest.mapper;

import com.doublekit.apibox.apitest.entity.BinaryParamCaseEntity;
import com.doublekit.apibox.apitest.model.BinaryParamCase;
import com.doublekit.beans.annotation.Mapper;

@Mapper(source = BinaryParamCase.class,target = BinaryParamCaseEntity.class)
public class BinaryParamCaseMapper {
}
