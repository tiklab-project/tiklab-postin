package com.doublekit.apibox.apitest.mapper;


import com.doublekit.apibox.apitest.entity.PreScriptCaseEntity;
import com.doublekit.apibox.apitest.entity.ResponseInstanceEntity;
import com.doublekit.apibox.apitest.model.PreScriptCase;
import com.doublekit.apibox.apitest.model.ResponseInstance;
import com.doublekit.beans.annotation.Mapper;

@Mapper(source = ResponseInstance.class,target = ResponseInstanceEntity.class)
public class ResponseInstanceMapper {
}
