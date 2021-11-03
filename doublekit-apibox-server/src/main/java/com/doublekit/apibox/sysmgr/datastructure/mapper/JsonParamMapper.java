package com.doublekit.apibox.sysmgr.datastructure.mapper;

import com.doublekit.apibox.sysmgr.datastructure.entity.JsonParamDSEntity;
import com.doublekit.apibox.sysmgr.datastructure.model.JsonParamDS;
import com.doublekit.beans.annotation.Mapper;

@Mapper(source = JsonParamDS.class,target = JsonParamDSEntity.class)
public class JsonParamMapper {
}
