package com.doublekit.apibox.apitest.mapper;


import com.doublekit.apibox.apitest.entity.PreScriptCaseEntity;
import com.doublekit.apibox.apitest.entity.TestInstanceEntity;
import com.doublekit.apibox.apitest.model.PreScriptCase;
import com.doublekit.apibox.apitest.model.TestInstance;
import com.doublekit.beans.annotation.Mapper;

@Mapper(source = TestInstance.class,target = TestInstanceEntity.class)
public class TestInstanceMapper {
}
