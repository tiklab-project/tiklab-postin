package com.doublekit.apibox.apidef.mapper;


import com.doublekit.apibox.apidef.entity.MethodPo;
import com.doublekit.apibox.apidef.model.MethodEx;
import com.doublekit.apibox.apimock.entity.FormParamMockPo;
import com.doublekit.apibox.apimock.model.FormParamMock;
import com.doublekit.beans.annotation.Mapper;

@Mapper(source = MethodEx.class,target = MethodPo.class)
public class MethodMapper {
}
