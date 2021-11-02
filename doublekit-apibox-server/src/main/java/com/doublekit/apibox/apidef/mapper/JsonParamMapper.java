package com.doublekit.apibox.apidef.mapper;

import com.doublekit.apibox.apidef.entity.AfterScriptEntity;
import com.doublekit.apibox.apidef.entity.JsonParamEntity;
import com.doublekit.apibox.apidef.model.AfterScript;
import com.doublekit.apibox.apidef.model.JsonParam;
import com.doublekit.beans.annotation.Mapper;

@Mapper(source = JsonParam.class,target = JsonParamEntity.class)
public class JsonParamMapper {
}
