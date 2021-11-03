package com.doublekit.apibox.sysmgr.datastructure.mapper;

import com.doublekit.apibox.sysmgr.datastructure.entity.DataStructureEntity;
import com.doublekit.apibox.sysmgr.datastructure.model.DataStructure;
import com.doublekit.beans.annotation.Mapper;

@Mapper(source = DataStructure.class,target = DataStructureEntity.class)
public class DataStructureMapper {
}
