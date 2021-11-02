package com.doublekit.apibox.datastructure.mapper;

import com.doublekit.apibox.datastructure.entity.JsonParamDSEntity;
import com.doublekit.apibox.datastructure.model.JsonParamDS;
import com.doublekit.beans.annotation.Mapper;

@Mapper(source = JsonParamDS.class,target = JsonParamDSEntity.class)
public class JsonParamMapper {
}
