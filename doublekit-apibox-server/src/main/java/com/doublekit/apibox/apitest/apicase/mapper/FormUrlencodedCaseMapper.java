package com.doublekit.apibox.apitest.apicase.mapper;

import com.doublekit.apibox.apitest.apicase.entity.FormUrlencodedCaseEntity;
import com.doublekit.apibox.apitest.apicase.model.FormUrlencodedCase;
import com.doublekit.beans.annotation.Mapper;

@Mapper(source = FormUrlencodedCase.class,target = FormUrlencodedCaseEntity.class)
public class FormUrlencodedCaseMapper {
}
