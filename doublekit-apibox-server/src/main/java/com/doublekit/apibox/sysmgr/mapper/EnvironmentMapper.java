package com.doublekit.apibox.sysmgr.mapper;

import com.doublekit.apibox.sysmgr.entity.EnvironmentPo;
import com.doublekit.apibox.sysmgr.model.Environment;
import com.doublekit.beans.annotation.Mapper;

@Mapper(source = Environment.class,target = EnvironmentPo.class)
public class EnvironmentMapper {
}
