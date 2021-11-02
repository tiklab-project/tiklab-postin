package com.doublekit.apibox.apimock.mapper;


import com.doublekit.apibox.apimock.entity.FormParamMockPo;
import com.doublekit.apibox.apimock.model.FormParamMock;
import com.doublekit.apibox.apitest.entity.PreScriptCasePo;
import com.doublekit.apibox.apitest.model.PreScriptCase;
import com.doublekit.beans.annotation.Mapper;

@Mapper(source = FormParamMock.class,target = FormParamMockPo.class)
public class FormParamMockMapper {
}
