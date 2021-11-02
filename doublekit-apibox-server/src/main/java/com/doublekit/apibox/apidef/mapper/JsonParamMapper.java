package com.doublekit.apibox.apidef.mapper;

import com.doublekit.apibox.apidef.entity.AfterScriptPo;
import com.doublekit.apibox.apidef.entity.JsonParamPo;
import com.doublekit.apibox.apidef.model.AfterScript;
import com.doublekit.apibox.apidef.model.JsonParam;
import com.doublekit.beans.annotation.Mapper;

@Mapper(source = JsonParam.class,target = JsonParamPo.class)
public class JsonParamMapper {
}
