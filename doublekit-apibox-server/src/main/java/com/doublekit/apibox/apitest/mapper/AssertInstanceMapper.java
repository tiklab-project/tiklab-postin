package com.doublekit.apibox.apitest.mapper;

import com.doublekit.apibox.apidef.entity.AfterScriptPo;
import com.doublekit.apibox.apidef.model.AfterScript;
import com.doublekit.apibox.apitest.entity.AssertInstancePo;
import com.doublekit.apibox.apitest.model.AssertInstance;
import com.doublekit.beans.annotation.Mapper;

@Mapper(source = AssertInstance.class,target = AssertInstancePo.class)
public class AssertInstanceMapper {
}
