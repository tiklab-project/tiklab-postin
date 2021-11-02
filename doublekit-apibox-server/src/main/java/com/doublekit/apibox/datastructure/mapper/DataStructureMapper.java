package com.doublekit.apibox.datastructure.mapper;

import com.doublekit.apibox.datastructure.entity.DataStructurePo;
import com.doublekit.apibox.datastructure.model.DataStructure;
import com.doublekit.beans.annotation.Mapper;

@Mapper(source = DataStructure.class,target = DataStructurePo.class)
public class DataStructureMapper {
}
