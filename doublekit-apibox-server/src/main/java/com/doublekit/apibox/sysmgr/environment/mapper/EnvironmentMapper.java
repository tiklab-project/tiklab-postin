package com.doublekit.apibox.sysmgr.environment.mapper;

import com.doublekit.apibox.sysmgr.environment.entity.EnvironmentEntity;
import com.doublekit.apibox.sysmgr.environment.model.Environment;
import com.doublekit.beans.annotation.Mapper;

@Mapper(source = Environment.class,target = EnvironmentEntity.class)
public class EnvironmentMapper {
}
