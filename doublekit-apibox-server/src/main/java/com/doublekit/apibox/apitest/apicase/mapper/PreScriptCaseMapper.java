package com.doublekit.apibox.apitest.apicase.mapper;


import com.doublekit.apibox.apitest.apicase.entity.PreScriptCaseEntity;
import com.doublekit.apibox.apitest.apicase.model.PreScriptCase;
import com.doublekit.beans.annotation.Mapper;

@Mapper(source = PreScriptCase.class,target = PreScriptCaseEntity.class)
public class PreScriptCaseMapper {
}
