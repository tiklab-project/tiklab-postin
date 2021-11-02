package com.doublekit.apibox.apidef.mapper;


import com.doublekit.apibox.apidef.entity.ResponseResultPo;
import com.doublekit.apibox.apidef.model.ResponseResult;
import com.doublekit.apibox.apimock.entity.FormParamMockPo;
import com.doublekit.apibox.apimock.model.FormParamMock;
import com.doublekit.beans.annotation.Mapper;

@Mapper(source = ResponseResult.class,target = ResponseResultPo.class)
public class ResponseResultMapper {
}
