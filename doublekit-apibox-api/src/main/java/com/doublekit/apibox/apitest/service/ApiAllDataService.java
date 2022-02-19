package com.doublekit.apibox.apitest.service;

import com.doublekit.apibox.apitest.model.ApiAllData;

import javax.validation.constraints.NotNull;

public interface ApiAllDataService {


    ApiAllData findApiAllData(@NotNull String methodId) ;

}
