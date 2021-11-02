package com.doublekit.apibox.sysmgr.mapper;

import com.doublekit.apibox.sysmgr.entity.EnvironmentEntity;
import com.doublekit.apibox.sysmgr.model.Environment;
import com.doublekit.beans.annotation.Mapper;

@Mapper(source = Environment.class,target = EnvironmentEntity.class)
public class EnvironmentMapper {
}
