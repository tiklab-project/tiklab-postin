package com.doublekit.apibox.apitest.apiinstance.mapper;


import com.doublekit.apibox.apitest.apiinstance.entity.RequestInstanceEntity;
import com.doublekit.apibox.apitest.apiinstance.model.RequestInstance;
import com.doublekit.beans.annotation.Mapper;

@Mapper(source = RequestInstance.class,target = RequestInstanceEntity.class)
public class RequestInstanceMapper {
}
