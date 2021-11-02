package com.doublekit.apibox.apitest.mapper;

import com.doublekit.apibox.apidef.entity.AfterScriptPo;
import com.doublekit.apibox.apidef.model.AfterScript;
import com.doublekit.apibox.apitest.entity.AfterScriptCasePo;
import com.doublekit.apibox.apitest.model.AfterScriptCase;
import com.doublekit.beans.annotation.Mapper;

@Mapper(source = AfterScriptCase.class,target = AfterScriptCasePo.class)
public class QueryParamCaseMapper {
}
