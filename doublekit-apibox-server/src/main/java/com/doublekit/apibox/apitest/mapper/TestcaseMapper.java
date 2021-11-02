package com.doublekit.apibox.apitest.mapper;


import com.doublekit.apibox.apitest.entity.PreScriptCaseEntity;
import com.doublekit.apibox.apitest.entity.TestcaseEntity;
import com.doublekit.apibox.apitest.model.PreScriptCase;
import com.doublekit.apibox.apitest.model.Testcase;
import com.doublekit.beans.annotation.Mapper;

@Mapper(source = Testcase.class,target = TestcaseEntity.class)
public class TestcaseMapper {
}
