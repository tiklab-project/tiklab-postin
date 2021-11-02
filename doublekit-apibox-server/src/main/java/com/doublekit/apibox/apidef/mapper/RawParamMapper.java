package com.doublekit.apibox.apidef.mapper;


import com.doublekit.apibox.apidef.entity.RawParamEntity;
import com.doublekit.apibox.apidef.model.RawParam;
import com.doublekit.apibox.apimock.entity.FormParamMockEntity;
import com.doublekit.apibox.apimock.model.FormParamMock;
import com.doublekit.beans.annotation.Mapper;

@Mapper(source = RawParam.class,target = RawParamEntity.class)
public class RawParamMapper {
}
