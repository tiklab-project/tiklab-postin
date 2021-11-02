package com.doublekit.apibox.apitest.mapper;

import com.doublekit.apibox.apitest.entity.AssertCasePo;
import com.doublekit.apibox.apitest.model.AssertCase;
import com.doublekit.beans.annotation.Mapper;

@Mapper(source = AssertCase.class,target = AssertCasePo.class)
public class AssertCaseMapper {
}
