package com.doublekit.apibox.apitest.mapper;


import com.doublekit.apibox.apitest.entity.PreScriptCasePo;
import com.doublekit.apibox.apitest.entity.RequestInstancePo;
import com.doublekit.apibox.apitest.model.PreScriptCase;
import com.doublekit.apibox.apitest.model.RequestInstance;
import com.doublekit.beans.annotation.Mapper;

@Mapper(source = RequestInstance.class,target = RequestInstancePo.class)
public class RequestInstanceMapper {
}
