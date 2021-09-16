package com.doublekit.apibox.apidef.service;

import com.doublekit.common.Pagination;

import com.doublekit.apibox.apidef.model.RequestBodyEx;
import com.doublekit.apibox.apidef.model.RequestBodyExQuery;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
* 用户服务接口
*/
public interface RequestBodyService {

    /**
    * 创建用户
    * @param requestBody
    * @return
    */
    String createRequestBody(@NotNull @Valid RequestBodyEx requestBody);

    /**
    * 更新用户
    * @param requestBody
    */
    void updateRequestBody(@NotNull @Valid RequestBodyEx requestBody);

    /**
    * 删除用户
    * @param id
    */
    void deleteRequestBody(@NotNull String id);

    /**
    * 查找用户
    * @param id
    * @return
    */
    RequestBodyEx findRequestBody(@NotNull String id);

    /**
    * 查找所有
    * @return
    */
    List<RequestBodyEx> findAllRequestBody();

    /**
    * 查询列表
    * @param requestBodyQuery
    * @return
    */
    List<RequestBodyEx> findRequestBodyList(RequestBodyExQuery requestBodyQuery);

    /**
    * 按分页查询
    * @param requestBodyQuery
    * @return
    */
    Pagination<RequestBodyEx> findRequestBodyPage(RequestBodyExQuery requestBodyQuery);

}