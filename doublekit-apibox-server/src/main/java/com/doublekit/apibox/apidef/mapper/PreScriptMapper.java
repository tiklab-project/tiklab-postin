package com.doublekit.apibox.apidef.mapper;


import com.doublekit.apibox.apidef.entity.PreScriptEntity;
import com.doublekit.apibox.apidef.model.PreScript;
import com.doublekit.apibox.apimock.entity.FormParamMockEntity;
import com.doublekit.apibox.apimock.model.FormParamMock;
import com.doublekit.beans.annotation.Mapper;

@Mapper(source = PreScript.class,target = PreScriptEntity.class)
public class PreScriptMapper {
}
