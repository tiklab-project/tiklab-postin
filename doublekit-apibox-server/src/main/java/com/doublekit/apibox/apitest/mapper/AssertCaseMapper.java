package com.doublekit.apibox.apitest.mapper;

import com.doublekit.apibox.apitest.entity.AssertCaseEntity;
import com.doublekit.apibox.apitest.model.AssertCase;
import com.doublekit.beans.annotation.Mapper;

@Mapper(source = AssertCase.class,target = AssertCaseEntity.class)
public class AssertCaseMapper {
}
