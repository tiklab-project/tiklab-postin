package io.tiklab.postin.api.http.test.cases.service;


import io.tiklab.postin.api.http.test.cases.model.RequestCase;
import io.tiklab.postin.api.http.test.cases.model.RequestCaseQuery;
import io.tiklab.core.page.Pagination;
import io.tiklab.toolkit.join.annotation.FindAll;
import io.tiklab.toolkit.join.annotation.FindList;
import io.tiklab.toolkit.join.annotation.FindOne;
import io.tiklab.toolkit.join.annotation.JoinProvider;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
* RequestCaseService
*/
@JoinProvider(model = RequestCase.class)
public interface RequestCaseService {

    /**
    * 创建
    * @param requestCase
    * @return
    */
    String createRequestCase(@NotNull @Valid RequestCase requestCase);

    /**
    * 更新
    * @param requestCase
    */
    void updateRequestCase(@NotNull @Valid RequestCase requestCase);

    /**
    * 删除
    * @param id
    */
    void deleteRequestCase(@NotNull String id);

    @FindOne
    RequestCase findOne(@NotNull String id);

    @FindList
    List<RequestCase> findList(List<String> idList);

    /**
    * 查找
    * @param id
    * @return
    */
    RequestCase findRequestCase(@NotNull String id);

    /**
    * 查找所有
    * @return
    */
    @FindAll
    List<RequestCase> findAllRequestCase();

    /**
    * 查询列表
    * @param requestCaseQuery
    * @return
    */
    List<RequestCase> findRequestCaseList(RequestCaseQuery requestCaseQuery);

    /**
    * 按分页查询
    * @param requestCaseQuery
    * @return
    */
    Pagination<RequestCase> findRequestCasePage(RequestCaseQuery requestCaseQuery);

}