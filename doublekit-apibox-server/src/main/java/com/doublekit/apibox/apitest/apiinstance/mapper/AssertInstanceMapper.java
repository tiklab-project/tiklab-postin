package com.doublekit.apibox.apitest.apiinstance.mapper;

import com.doublekit.apibox.apitest.apiinstance.entity.AssertInstanceEntity;
import com.doublekit.apibox.apitest.apiinstance.model.AssertInstance;
import com.doublekit.beans.annotation.Mapper;

@Mapper(source = AssertInstance.class,target = AssertInstanceEntity.class)
public class AssertInstanceMapper {
}
