package com.doublekit.apibox.apitest.apicase.service;

import com.doublekit.apibox.apitest.apicase.model.ApiAllData;
import com.doublekit.apibox.apitest.apicase.model.CaseApiAllData;

import javax.validation.constraints.NotNull;

public interface ApiAllDataService {


    /**
     * 查询接口定义中的所有数据
     * @param methodId
     * @return
     */
    ApiAllData findApiAllData(@NotNull String methodId);


    /**
     * 查询接口用例中的所有数据
     * @param testcaseId
     * @return
     */
    CaseApiAllData findCaseApiAllData(@NotNull String testcaseId);

}
