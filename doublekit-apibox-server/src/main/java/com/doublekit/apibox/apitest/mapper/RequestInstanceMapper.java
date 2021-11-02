package com.doublekit.apibox.apitest.mapper;


import com.doublekit.apibox.apitest.entity.PreScriptCaseEntity;
import com.doublekit.apibox.apitest.entity.RequestInstanceEntity;
import com.doublekit.apibox.apitest.model.PreScriptCase;
import com.doublekit.apibox.apitest.model.RequestInstance;
import com.doublekit.beans.annotation.Mapper;

@Mapper(source = RequestInstance.class,target = RequestInstanceEntity.class)
public class RequestInstanceMapper {
}
