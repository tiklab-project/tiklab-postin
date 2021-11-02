package com.doublekit.apibox.apidef.mapper;


import com.doublekit.apibox.apidef.entity.ResponseResultEntity;
import com.doublekit.apibox.apidef.model.ResponseResult;
import com.doublekit.apibox.apimock.entity.FormParamMockEntity;
import com.doublekit.apibox.apimock.model.FormParamMock;
import com.doublekit.beans.annotation.Mapper;

@Mapper(source = ResponseResult.class,target = ResponseResultEntity.class)
public class ResponseResultMapper {
}
