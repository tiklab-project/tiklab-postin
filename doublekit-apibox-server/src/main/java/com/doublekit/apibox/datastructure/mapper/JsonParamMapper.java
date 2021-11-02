package com.doublekit.apibox.datastructure.mapper;

import com.doublekit.apibox.datastructure.entity.JsonParamDSPo;
import com.doublekit.apibox.datastructure.model.JsonParamDS;
import com.doublekit.beans.annotation.Mapper;

@Mapper(source = JsonParamDS.class,target = JsonParamDSPo.class)
public class JsonParamMapper {
}
