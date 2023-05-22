package io.tiklab.postin.api.http.definition.service;

import io.tiklab.core.page.Pagination;

import io.tiklab.postin.api.http.definition.model.RequestHeaders;
import io.tiklab.postin.api.http.definition.model.RequestHeaderQuery;
import io.tiklab.join.annotation.FindAll;
import io.tiklab.join.annotation.FindOne;
import io.tiklab.join.annotation.JoinProvider;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
* 请求头服务接口
*/
@JoinProvider(model = RequestHeaders.class)
public interface RequestHeaderService {

    /**
    * 创建请求头
    * @param requestHeaders
    * @return
    */
    String createRequestHeader(@NotNull @Valid RequestHeaders requestHeaders);

    /**
    * 更新请求头
    * @param requestHeaders
    */
    void updateRequestHeader(@NotNull @Valid RequestHeaders requestHeaders);

    /**
    * 删除请求头
    * @param id
    */
    void deleteRequestHeader(@NotNull String id);


    /**
     * 通过httpId删除所有请求头
     * @param id
     */
    void deleteAllRequestHeader(@NotNull String id);


    /**
    * 查找请求头
    * @param id
    * @return
    */
    @FindOne
    RequestHeaders findRequestHeader(@NotNull String id);

    /**
    * 查找请求头
    * @return
    */
    @FindAll
    List<RequestHeaders> findAllRequestHeader();

    /**
    * 查询列表请求头
    * @param requestHeaderQuery
    * @return
    */
    List<RequestHeaders> findRequestHeaderList(RequestHeaderQuery requestHeaderQuery);

    /**
    * 按分页查询请求头
    * @param requestHeaderQuery
    * @return
    */
    Pagination<RequestHeaders> findRequestHeaderPage(RequestHeaderQuery requestHeaderQuery);

}