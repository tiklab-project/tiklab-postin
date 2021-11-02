package com.doublekit.apibox.apitest.mapper;


import com.doublekit.apibox.apitest.entity.PreScriptCasePo;
import com.doublekit.apibox.apitest.entity.TestcasePo;
import com.doublekit.apibox.apitest.model.PreScriptCase;
import com.doublekit.apibox.apitest.model.Testcase;
import com.doublekit.beans.annotation.Mapper;

@Mapper(source = Testcase.class,target = TestcasePo.class)
public class TestcaseMapper {
}
