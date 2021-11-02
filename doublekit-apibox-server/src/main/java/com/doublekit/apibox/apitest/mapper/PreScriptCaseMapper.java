package com.doublekit.apibox.apitest.mapper;


import com.doublekit.apibox.apitest.entity.PreScriptCaseEntity;
import com.doublekit.apibox.apitest.model.PreScriptCase;
import com.doublekit.beans.annotation.Mapper;

@Mapper(source = PreScriptCase.class,target = PreScriptCaseEntity.class)
public class PreScriptCaseMapper {
}
