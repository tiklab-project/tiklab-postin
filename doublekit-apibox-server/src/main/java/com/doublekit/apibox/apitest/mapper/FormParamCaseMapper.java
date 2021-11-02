package com.doublekit.apibox.apitest.mapper;

import com.doublekit.apibox.apidef.entity.AfterScriptEntity;
import com.doublekit.apibox.apidef.model.AfterScript;
import com.doublekit.apibox.apidef.model.FormParam;
import com.doublekit.apibox.apitest.entity.FormParamCaseEntity;
import com.doublekit.apibox.apitest.model.FormParamCase;
import com.doublekit.beans.annotation.Mapper;

@Mapper(source = FormParamCase.class,target = FormParamCaseEntity.class)
public class FormParamCaseMapper {
}
