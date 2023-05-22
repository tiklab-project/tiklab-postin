package io.tiklab.postin.api.http.definition.service;

import io.tiklab.core.page.Pagination;

import io.tiklab.postin.api.http.definition.model.ResponseHeaders;
import io.tiklab.postin.api.http.definition.model.ResponseHeaderQuery;
import io.tiklab.join.annotation.FindAll;
import io.tiklab.join.annotation.FindOne;
import io.tiklab.join.annotation.JoinProvider;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
* 响应头服务接口
*/
@JoinProvider(model = ResponseHeaders.class)
public interface ResponseHeaderService {

    /**
    * 创建响应头
    * @param responseHeaders
    * @return
    */
    String createResponseHeader(@NotNull @Valid ResponseHeaders responseHeaders);

    /**
    * 更新响应头
    * @param responseHeaders
    */
    void updateResponseHeader(@NotNull @Valid ResponseHeaders responseHeaders);

    /**
    * 删除响应头
    * @param id
    */
    void deleteResponseHeader(@NotNull String id);

    /**
     * 通过httpId删除所有响应头
     * @param id
     */
    void deleteAllResponseHeader(@NotNull String id);

    /**
    * 查找响应头
    * @param id
    * @return
    */
    @FindOne
    ResponseHeaders findResponseHeader(@NotNull String id);

    /**
    * 查找所有响应头
    * @return
    */
    @FindAll
    List<ResponseHeaders> findAllResponseHeader();

    /**
    * 查询列表响应头
    * @param responseHeaderQuery
    * @return
    */
    List<ResponseHeaders> findResponseHeaderList(ResponseHeaderQuery responseHeaderQuery);

    /**
    * 按分页查询响应头
    * @param responseHeaderQuery
    * @return
    */
    Pagination<ResponseHeaders> findResponseHeaderPage(ResponseHeaderQuery responseHeaderQuery);

}