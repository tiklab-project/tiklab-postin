package com.doublekit.apibox.apidef.mapper;


import com.doublekit.apibox.apidef.entity.JsonResponsePo;
import com.doublekit.apibox.apidef.model.JsonResponse;
import com.doublekit.apibox.apimock.entity.FormParamMockPo;
import com.doublekit.apibox.apimock.model.FormParamMock;
import com.doublekit.beans.annotation.Mapper;

@Mapper(source = JsonResponse.class,target = JsonResponsePo.class)
public class JsonResponseMapper {
}
