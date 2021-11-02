package com.doublekit.apibox.apitest.mapper;


import com.doublekit.apibox.apitest.entity.PreScriptCasePo;
import com.doublekit.apibox.apitest.entity.TestInstancePo;
import com.doublekit.apibox.apitest.model.PreScriptCase;
import com.doublekit.apibox.apitest.model.TestInstance;
import com.doublekit.beans.annotation.Mapper;

@Mapper(source = TestInstance.class,target = TestInstancePo.class)
public class TestInstanceMapper {
}
