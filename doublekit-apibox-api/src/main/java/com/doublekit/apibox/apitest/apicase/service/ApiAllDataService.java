package com.doublekit.apibox.apitest.apicase.service;

import com.doublekit.apibox.apitest.apicase.model.ApiAllData;

import javax.validation.constraints.NotNull;

public interface ApiAllDataService {


    ApiAllData findApiAllData(@NotNull String methodId) ;

}
