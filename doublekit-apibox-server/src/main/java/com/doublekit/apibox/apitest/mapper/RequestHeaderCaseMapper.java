package com.doublekit.apibox.apitest.mapper;


import com.doublekit.apibox.apitest.entity.PreScriptCasePo;
import com.doublekit.apibox.apitest.entity.RequestHeaderCasePo;
import com.doublekit.apibox.apitest.model.PreScriptCase;
import com.doublekit.apibox.apitest.model.RequestHeaderCase;
import com.doublekit.beans.annotation.Mapper;

@Mapper(source = RequestHeaderCase.class,target = RequestHeaderCasePo.class)
public class RequestHeaderCaseMapper {
}
