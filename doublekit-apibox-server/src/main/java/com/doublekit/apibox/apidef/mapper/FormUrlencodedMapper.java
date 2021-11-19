package com.doublekit.apibox.apidef.mapper;

import com.doublekit.apibox.apidef.entity.FormUrlencodedEntity;
import com.doublekit.apibox.apidef.model.FormUrlencoded;
import com.doublekit.beans.annotation.Mapper;

@Mapper(source = FormUrlencoded.class,target = FormUrlencodedEntity.class)
public class FormUrlencodedMapper {
}
