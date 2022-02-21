package com.doublekit.apibox.apitest.apiinstance.mapper;


import com.doublekit.apibox.apitest.apiinstance.entity.TestInstanceEntity;
import com.doublekit.apibox.apitest.apiinstance.model.TestInstance;
import com.doublekit.beans.annotation.Mapper;

@Mapper(source = TestInstance.class,target = TestInstanceEntity.class)
public class TestInstanceMapper {
}
