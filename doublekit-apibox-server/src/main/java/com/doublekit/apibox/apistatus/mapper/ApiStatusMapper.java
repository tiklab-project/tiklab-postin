package com.doublekit.apibox.apistatus.mapper;

import com.doublekit.apibox.apistatus.entity.ApiStatusEntity;
import com.doublekit.beans.annotation.Mapper;
import com.doublekit.apibox.apistatus.model.ApiStatus;

@Mapper(source = ApiStatus.class,target = ApiStatusEntity.class)
public class ApiStatusMapper{
}
