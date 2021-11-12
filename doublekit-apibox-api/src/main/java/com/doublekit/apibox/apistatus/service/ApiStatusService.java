package com.doublekit.apibox.apistatus.service;

import com.doublekit.common.page.Pagination;

import com.doublekit.apibox.apistatus.model.ApiStatus;
import com.doublekit.apibox.apistatus.model.ApiStatusQuery;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
* ApiStatusService
*/
public interface ApiStatusService {

    /**
    * 创建
    * @param apiStatus
    * @return
    */
    String createApiStatus(@NotNull @Valid ApiStatus apiStatus);

    /**
    * 更新
    * @param apiStatus
    */
    void updateApiStatus(@NotNull @Valid ApiStatus apiStatus);

    /**
    * 删除
    * @param id
    */
    void deleteApiStatus(@NotNull String id);

    ApiStatus findOne(@NotNull String id);

    List<ApiStatus> findList(List<String> idList);

    /**
    * 查找
    * @param id
    * @return
    */
    ApiStatus findApiStatus(@NotNull String id);

    /**
    * 查找所有
    * @return
    */
    List<ApiStatus> findAllApiStatus();

    /**
    * 查询列表
    * @param apiStatusQuery
    * @return
    */
    List<ApiStatus> findApiStatusList(ApiStatusQuery apiStatusQuery);

    /**
    * 按分页查询
    * @param apiStatusQuery
    * @return
    */
    Pagination<ApiStatus> findApiStatusPage(ApiStatusQuery apiStatusQuery);

}