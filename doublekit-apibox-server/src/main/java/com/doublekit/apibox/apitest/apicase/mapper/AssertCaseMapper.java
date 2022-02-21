package com.doublekit.apibox.apitest.apicase.mapper;

import com.doublekit.apibox.apitest.apicase.entity.AssertCaseEntity;
import com.doublekit.apibox.apitest.apicase.model.AssertCase;
import com.doublekit.beans.annotation.Mapper;

@Mapper(source = AssertCase.class,target = AssertCaseEntity.class)
public class AssertCaseMapper {
}
