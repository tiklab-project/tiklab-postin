package com.doublekit.apibox.apitest.mapper;

import com.doublekit.apibox.apidef.entity.AfterScriptPo;
import com.doublekit.apibox.apidef.model.AfterScript;
import com.doublekit.apibox.apidef.model.FormParam;
import com.doublekit.apibox.apitest.entity.FormParamCasePo;
import com.doublekit.apibox.apitest.model.FormParamCase;
import com.doublekit.beans.annotation.Mapper;

@Mapper(source = FormParamCase.class,target = FormParamCasePo.class)
public class FormParamCaseMapper {
}
