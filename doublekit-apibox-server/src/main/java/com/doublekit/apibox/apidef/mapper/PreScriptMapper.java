package com.doublekit.apibox.apidef.mapper;


import com.doublekit.apibox.apidef.entity.PreScriptPo;
import com.doublekit.apibox.apidef.model.PreScript;
import com.doublekit.apibox.apimock.entity.FormParamMockPo;
import com.doublekit.apibox.apimock.model.FormParamMock;
import com.doublekit.beans.annotation.Mapper;

@Mapper(source = PreScript.class,target = PreScriptPo.class)
public class PreScriptMapper {
}
