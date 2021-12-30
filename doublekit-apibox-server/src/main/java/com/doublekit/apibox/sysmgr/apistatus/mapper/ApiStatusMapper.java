package com.doublekit.apibox.sysmgr.apistatus.mapper;

import com.doublekit.apibox.sysmgr.apistatus.entity.ApiStatusEntity;
import com.doublekit.beans.annotation.Mapper;
import com.doublekit.apibox.sysmgr.apistatus.model.ApiStatus;

@Mapper(source = ApiStatus.class,target = ApiStatusEntity.class)
public class ApiStatusMapper{
}
