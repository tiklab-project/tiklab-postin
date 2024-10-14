package io.tiklab.postin.api.apix.service;

import io.tiklab.core.page.Pagination;

import io.tiklab.postin.api.apix.model.RequestHeader;
import io.tiklab.postin.api.apix.model.RequestHeaderQuery;
import io.tiklab.toolkit.join.annotation.FindAll;
import io.tiklab.toolkit.join.annotation.FindOne;
import io.tiklab.toolkit.join.annotation.JoinProvider;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
* 请求头服务接口
*/
@JoinProvider(model = RequestHeader.class)
public interface RequestHeaderService {

    /**
    * 创建请求头
    * @param requestHeader
    * @return
    */
    String createRequestHeader(@NotNull @Valid RequestHeader requestHeader);

    /**
    * 更新请求头
    * @param requestHeader
    */
    void updateRequestHeader(@NotNull @Valid RequestHeader requestHeader);

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
    RequestHeader findRequestHeader(@NotNull String id);

    /**
    * 查找请求头
    * @return
    */
    @FindAll
    List<RequestHeader> findAllRequestHeader();

    /**
    * 查询列表请求头
    * @param requestHeaderQuery
    * @return
    */
    List<RequestHeader> findRequestHeaderList(RequestHeaderQuery requestHeaderQuery);

    /**
    * 按分页查询请求头
    * @param requestHeaderQuery
    * @return
    */
    Pagination<RequestHeader> findRequestHeaderPage(RequestHeaderQuery requestHeaderQuery);

}