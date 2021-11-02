package com.doublekit.apibox.apidef.mapper;


import com.doublekit.apibox.apidef.entity.AfterScriptEntity;
import com.doublekit.apibox.apidef.entity.FormParamEntity;
import com.doublekit.apibox.apidef.model.AfterScript;
import com.doublekit.apibox.apidef.model.FormParam;
import com.doublekit.apibox.apimock.entity.FormParamMockEntity;
import com.doublekit.apibox.apimock.model.FormParamMock;
import com.doublekit.beans.annotation.Mapper;

@Mapper(source = AfterScript.class,target = AfterScriptEntity.class)
public class AfterScriptMapper {
}
