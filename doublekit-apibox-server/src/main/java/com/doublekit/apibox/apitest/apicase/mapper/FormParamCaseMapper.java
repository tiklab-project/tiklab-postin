package com.doublekit.apibox.apitest.apicase.mapper;

import com.doublekit.apibox.apitest.apicase.entity.FormParamCaseEntity;
import com.doublekit.apibox.apitest.apicase.model.FormParamCase;
import com.doublekit.beans.annotation.Mapper;

@Mapper(source = FormParamCase.class,target = FormParamCaseEntity.class)
public class FormParamCaseMapper {
}
