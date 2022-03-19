package com.doublekit.apibox.apidef.http.service;

import com.doublekit.common.page.Pagination;

import com.doublekit.apibox.apidef.http.model.RequestHeader;
import com.doublekit.apibox.apidef.http.model.RequestHeaderQuery;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
* 用户服务接口
*/
public interface RequestHeaderService {

    /**
    * 创建用户
    * @param requestHeader
    * @return
    */
    String createRequestHeader(@NotNull @Valid RequestHeader requestHeader);

    /**
    * 更新用户
    * @param requestHeader
    */
    void updateRequestHeader(@NotNull @Valid RequestHeader requestHeader);

    /**
    * 删除用户
    * @param id
    */
    void deleteRequestHeader(@NotNull String id);

    /**
    * 查找用户
    * @param id
    * @return
    */
    RequestHeader findRequestHeader(@NotNull String id);

    /**
    * 查找所有
    * @return
    */
    List<RequestHeader> findAllRequestHeader();

    /**
    * 查询列表
    * @param requestHeaderQuery
    * @return
    */
    List<RequestHeader> findRequestHeaderList(RequestHeaderQuery requestHeaderQuery);

    /**
    * 按分页查询
    * @param requestHeaderQuery
    * @return
    */
    Pagination<RequestHeader> findRequestHeaderPage(RequestHeaderQuery requestHeaderQuery);

}