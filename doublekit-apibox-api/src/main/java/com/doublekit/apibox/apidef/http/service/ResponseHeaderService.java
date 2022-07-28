package com.doublekit.apibox.apidef.http.service;

import com.doublekit.core.page.Pagination;

import com.doublekit.apibox.apidef.http.model.ResponseHeader;
import com.doublekit.apibox.apidef.http.model.ResponseHeaderQuery;
import com.doublekit.join.annotation.FindAll;
import com.doublekit.join.annotation.FindList;
import com.doublekit.join.annotation.FindOne;
import com.doublekit.join.annotation.JoinProvider;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
* 用户服务接口
*/
@JoinProvider(model = ResponseHeader.class)
public interface ResponseHeaderService {

    /**
    * 创建用户
    * @param responseHeader
    * @return
    */
    String createResponseHeader(@NotNull @Valid ResponseHeader responseHeader);

    /**
    * 更新用户
    * @param responseHeader
    */
    void updateResponseHeader(@NotNull @Valid ResponseHeader responseHeader);

    /**
    * 删除用户
    * @param id
    */
    void deleteResponseHeader(@NotNull String id);

    /**
    * 查找用户
    * @param id
    * @return
    */
    @FindOne
    ResponseHeader findResponseHeader(@NotNull String id);

    /**
    * 查找所有
    * @return
    */
    @FindAll
    List<ResponseHeader> findAllResponseHeader();

    /**
    * 查询列表
    * @param responseHeaderQuery
    * @return
    */
    List<ResponseHeader> findResponseHeaderList(ResponseHeaderQuery responseHeaderQuery);

    /**
    * 按分页查询
    * @param responseHeaderQuery
    * @return
    */
    Pagination<ResponseHeader> findResponseHeaderPage(ResponseHeaderQuery responseHeaderQuery);

}