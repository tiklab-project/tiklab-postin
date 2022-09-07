package com.tiklab.postin.apidef.http.service;

import com.tiklab.core.page.Pagination;

import com.tiklab.postin.apidef.http.model.RequestBodyEx;
import com.tiklab.postin.apidef.http.model.RequestBodyExQuery;
import com.tiklab.join.annotation.FindAll;
import com.tiklab.join.annotation.FindOne;
import com.tiklab.join.annotation.JoinProvider;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
* 用户服务接口
*/
@JoinProvider(model = RequestBodyEx.class)
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
    @FindOne
    RequestBodyEx findRequestBody(@NotNull String id);

    /**
    * 查找所有
    * @return
    */
    @FindAll
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