package com.doublekit.apiboxpiugin.version.service;


import com.doublekit.apibox.apidef.apix.model.Apix;
import com.doublekit.apibox.apidef.apix.model.ApixQuery;
import com.doublekit.apiboxpiugin.version.model.VersionMethod;
import com.doublekit.apiboxpiugin.version.model.VersionMethodQuery;
import com.doublekit.common.page.Pagination;
import com.doublekit.join.annotation.JoinProvider;

import java.util.Map;

/**
* 接口版本
*/
@JoinProvider(model = Apix.class)
public interface MethodVersionService {

    /**
     * 添加历史版本
     * @param methodEx
     * @return
     */
    String addHistoryVersion(Apix methodEx);
    /**
     * 版本对比
     * @param versionMethodQuery
     * @return
     */
    Map contrastVersion(VersionMethodQuery versionMethodQuery);
    /**
     * 版本查询
     * @param methodExQuery
     * @return
     */
    Pagination<Apix> findMethodVersionPage(ApixQuery methodExQuery);
    /**
     * 版本详情查询
     * @param versionId
     * @return
     */
    VersionMethod queryVersiondetail(String versionId);
}