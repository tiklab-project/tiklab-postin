package com.doublekit.apibox.apimock.mapper;

import com.doublekit.apibox.apimock.entity.JsonResponseMockEntity;
import com.doublekit.apibox.apimock.model.JsonResponseMock;
import com.doublekit.beans.annotation.Mapper;

@Mapper(source = JsonResponseMock.class,target = JsonResponseMockEntity.class)
public class JsonResponseMockMapper {
}
