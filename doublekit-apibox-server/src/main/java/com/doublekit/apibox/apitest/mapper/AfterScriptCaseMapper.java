package com.doublekit.apibox.apitest.mapper;

import com.doublekit.apibox.apidef.entity.AfterScriptEntity;
import com.doublekit.apibox.apidef.model.AfterScript;
import com.doublekit.apibox.apitest.entity.AfterScriptCaseEntity;
import com.doublekit.apibox.apitest.model.AfterScriptCase;
import com.doublekit.apibox.node.entity.NodeEntity;
import com.doublekit.apibox.node.model.Node;
import com.doublekit.beans.annotation.Mapper;

@Mapper(source = AfterScriptCase.class,target = AfterScriptCaseEntity.class)
public class AfterScriptCaseMapper {
}
