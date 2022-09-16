package net.tiklab.postin.apitest.http.httpcase.service;

import net.tiklab.core.page.Pagination;

import net.tiklab.postin.apitest.http.httpcase.model.RequestBodyCase;
import net.tiklab.postin.apitest.http.httpcase.model.RequestBodyCaseQuery;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
* 用户服务接口
*/
public interface RequestBodyCaseService {

    /**
    * 创建用户
    * @param requestBodyCase
    * @return
    */
    String createRequestBodyCase(@NotNull @Valid RequestBodyCase requestBodyCase);

    /**
    * 更新用户
    * @param requestBodyCase
    */
    void updateRequestBodyCase(@NotNull @Valid RequestBodyCase requestBodyCase);

    /**
    * 删除用户
    * @param id
    */
    void deleteRequestBodyCase(@NotNull String id);

    RequestBodyCase findOne(@NotNull String id);

    List<RequestBodyCase> findList(List<String> idList);

    /**
    * 查找用户
    * @param id
    * @return
    */
    RequestBodyCase findRequestBodyCase(@NotNull String id);

    /**
    * 查找所有
    * @return
    */
    List<RequestBodyCase> findAllRequestBodyCase();

    /**
    * 查询列表
    * @param requestBodyCaseQuery
    * @return
    */
    List<RequestBodyCase> findRequestBodyCaseList(RequestBodyCaseQuery requestBodyCaseQuery);

    /**
    * 按分页查询
    * @param requestBodyCaseQuery
    * @return
    */
    Pagination<RequestBodyCase> findRequestBodyCasePage(RequestBodyCaseQuery requestBodyCaseQuery);

}