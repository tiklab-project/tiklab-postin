package com.doublekit.apibox.apidef.mapper;


import com.doublekit.apibox.apidef.entity.MethodEntity;
import com.doublekit.apibox.apidef.model.MethodEx;
import com.doublekit.apibox.apimock.entity.FormParamMockEntity;
import com.doublekit.apibox.apimock.model.FormParamMock;
import com.doublekit.beans.annotation.Mapper;

@Mapper(source = MethodEx.class,target = MethodEntity.class)
public class MethodMapper {
}
