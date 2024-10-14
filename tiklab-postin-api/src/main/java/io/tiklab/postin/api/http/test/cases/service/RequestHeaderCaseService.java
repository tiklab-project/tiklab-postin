package io.tiklab.postin.api.http.test.cases.service;

import io.tiklab.postin.api.http.test.cases.model.RequestHeaderCase;
import io.tiklab.postin.api.http.test.cases.model.RequestHeaderCaseQuery;
import io.tiklab.core.page.Pagination;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
* 用户服务接口
*/
public interface RequestHeaderCaseService {

    /**
    * 创建用户
    * @param requestHeaderCase
    * @return
    */
    String createRequestHeaderCase(@NotNull @Valid RequestHeaderCase requestHeaderCase);

    /**
    * 更新用户
    * @param requestHeaderCase
    */
    void updateRequestHeaderCase(@NotNull @Valid RequestHeaderCase requestHeaderCase);

    /**
    * 删除用户
    * @param id
    */
    void deleteRequestHeaderCase(@NotNull String id);

    RequestHeaderCase findOne(@NotNull String id);

    List<RequestHeaderCase> findList(List<String> idList);

    /**
    * 查找用户
    * @param id
    * @return
    */
    RequestHeaderCase findRequestHeaderCase(@NotNull String id);

    /**
    * 查找所有
    * @return
    */
    List<RequestHeaderCase> findAllRequestHeaderCase();

    /**
    * 查询列表
    * @param requestHeaderCaseQuery
    * @return
    */
    List<RequestHeaderCase> findRequestHeaderCaseList(RequestHeaderCaseQuery requestHeaderCaseQuery);

    /**
    * 按分页查询
    * @param requestHeaderCaseQuery
    * @return
    */
    Pagination<RequestHeaderCase> findRequestHeaderCasePage(RequestHeaderCaseQuery requestHeaderCaseQuery);

}