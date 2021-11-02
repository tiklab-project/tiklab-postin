package com.doublekit.apibox.datastructure.mapper;

import com.doublekit.apibox.datastructure.entity.EnumParamEntity;
import com.doublekit.apibox.datastructure.model.EnumParam;
import com.doublekit.beans.annotation.Mapper;

@Mapper(source = EnumParam.class,target = EnumParamEntity.class)
public class EnumParamMapper {
}
