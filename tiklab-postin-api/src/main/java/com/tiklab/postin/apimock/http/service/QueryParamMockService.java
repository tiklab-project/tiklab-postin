package com.tiklab.postin.apimock.http.service;



import com.tiklab.core.page.Pagination;
import com.tiklab.postin.apimock.http.model.QueryParamMock;
import com.tiklab.postin.apimock.http.model.QueryParamMockQuery;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
* 用户服务接口
*/
public interface QueryParamMockService {

    /**
    * 创建用户
    * @param queryParamMock
    * @return
    */
    String createQueryParamMock(@NotNull @Valid QueryParamMock queryParamMock);

    /**
    * 更新用户
    * @param queryParamMock
    */
    void updateQueryParamMock(@NotNull @Valid QueryParamMock queryParamMock);

    /**
    * 删除用户
    * @param id
    */
    void deleteQueryParamMock(@NotNull String id);

    /**
    * 查找用户
    * @param id
    * @return
    */
    QueryParamMock findQueryParamMock(@NotNull String id);

    /**
    * 查找所有
    * @return
    */
    List<QueryParamMock> findAllQueryParamMock();

    /**
    * 查询列表
    * @param queryParamMockQuery
    * @return
    */
    List<QueryParamMock> findQueryParamMockList(QueryParamMockQuery queryParamMockQuery);

    /**
    * 按分页查询
    * @param queryParamMockQuery
    * @return
    */
    Pagination<QueryParamMock> findQueryParamMockPage(QueryParamMockQuery queryParamMockQuery);

}