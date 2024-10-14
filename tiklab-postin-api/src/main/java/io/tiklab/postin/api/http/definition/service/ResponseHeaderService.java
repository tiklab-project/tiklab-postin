package io.tiklab.postin.api.http.definition.service;

import io.tiklab.core.page.Pagination;

import io.tiklab.postin.api.http.definition.model.ResponseHeader;
import io.tiklab.postin.api.http.definition.model.ResponseHeaderQuery;
import io.tiklab.toolkit.join.annotation.FindAll;
import io.tiklab.toolkit.join.annotation.FindOne;
import io.tiklab.toolkit.join.annotation.JoinProvider;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
* 响应头服务接口
*/
@JoinProvider(model = ResponseHeader.class)
public interface ResponseHeaderService {

    /**
    * 创建响应头
    * @param responseHeader
    * @return
    */
    String createResponseHeader(@NotNull @Valid ResponseHeader responseHeader);

    /**
    * 更新响应头
    * @param responseHeader
    */
    void updateResponseHeader(@NotNull @Valid ResponseHeader responseHeader);

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
    ResponseHeader findResponseHeader(@NotNull String id);

    /**
    * 查找所有响应头
    * @return
    */
    @FindAll
    List<ResponseHeader> findAllResponseHeader();

    /**
    * 查询列表响应头
    * @param responseHeaderQuery
    * @return
    */
    List<ResponseHeader> findResponseHeaderList(ResponseHeaderQuery responseHeaderQuery);

    /**
    * 按分页查询响应头
    * @param responseHeaderQuery
    * @return
    */
    Pagination<ResponseHeader> findResponseHeaderPage(ResponseHeaderQuery responseHeaderQuery);

}