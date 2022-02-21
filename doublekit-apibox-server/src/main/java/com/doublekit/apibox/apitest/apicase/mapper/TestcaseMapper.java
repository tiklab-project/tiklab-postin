package com.doublekit.apibox.apitest.apicase.mapper;


import com.doublekit.apibox.apitest.apicase.entity.TestcaseEntity;
import com.doublekit.apibox.apitest.apicase.model.Testcase;
import com.doublekit.beans.annotation.Mapper;

@Mapper(source = Testcase.class,target = TestcaseEntity.class)
public class TestcaseMapper {
}
