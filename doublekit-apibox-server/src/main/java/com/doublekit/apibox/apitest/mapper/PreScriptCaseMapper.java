package com.doublekit.apibox.apitest.mapper;


import com.doublekit.apibox.apitest.entity.PreScriptCasePo;
import com.doublekit.apibox.apitest.model.PreScriptCase;
import com.doublekit.beans.annotation.Mapper;

@Mapper(source = PreScriptCase.class,target = PreScriptCasePo.class)
public class PreScriptCaseMapper {
}
