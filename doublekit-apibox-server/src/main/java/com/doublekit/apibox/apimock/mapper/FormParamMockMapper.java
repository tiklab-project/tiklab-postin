package com.doublekit.apibox.apimock.mapper;


import com.doublekit.apibox.apimock.entity.FormParamMockEntity;
import com.doublekit.apibox.apimock.model.FormParamMock;
import com.doublekit.beans.annotation.Mapper;

@Mapper(source = FormParamMock.class,target = FormParamMockEntity.class)
public class FormParamMockMapper {
}
