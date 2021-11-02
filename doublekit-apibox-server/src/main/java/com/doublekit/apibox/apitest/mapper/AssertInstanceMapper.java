package com.doublekit.apibox.apitest.mapper;

import com.doublekit.apibox.apidef.entity.AfterScriptEntity;
import com.doublekit.apibox.apidef.model.AfterScript;
import com.doublekit.apibox.apitest.entity.AssertInstanceEntity;
import com.doublekit.apibox.apitest.model.AssertInstance;
import com.doublekit.beans.annotation.Mapper;

@Mapper(source = AssertInstance.class,target = AssertInstanceEntity.class)
public class AssertInstanceMapper {
}
