package com.doublekit.apibox.apitest.mapper;


import com.doublekit.apibox.apitest.entity.PreScriptCasePo;
import com.doublekit.apibox.apitest.entity.ResponseInstancePo;
import com.doublekit.apibox.apitest.model.PreScriptCase;
import com.doublekit.apibox.apitest.model.ResponseInstance;
import com.doublekit.beans.annotation.Mapper;

@Mapper(source = ResponseInstance.class,target = ResponseInstancePo.class)
public class ResponseInstanceMapper {
}
