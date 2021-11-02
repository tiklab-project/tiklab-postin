package com.doublekit.apibox.apidef.mapper;


import com.doublekit.apibox.apidef.entity.AfterScriptPo;
import com.doublekit.apibox.apidef.entity.FormParamPo;
import com.doublekit.apibox.apidef.model.AfterScript;
import com.doublekit.apibox.apidef.model.FormParam;
import com.doublekit.apibox.apimock.entity.FormParamMockPo;
import com.doublekit.apibox.apimock.model.FormParamMock;
import com.doublekit.beans.annotation.Mapper;

@Mapper(source = FormParam.class,target = FormParamPo.class)
public class FormParamMapper {
}
