package com.doublekit.apibox.apitest.apiinstance.mapper;


import com.doublekit.apibox.apitest.apiinstance.entity.ResponseInstanceEntity;
import com.doublekit.apibox.apitest.apiinstance.model.ResponseInstance;
import com.doublekit.beans.annotation.Mapper;

@Mapper(source = ResponseInstance.class,target = ResponseInstanceEntity.class)
public class ResponseInstanceMapper {
}
