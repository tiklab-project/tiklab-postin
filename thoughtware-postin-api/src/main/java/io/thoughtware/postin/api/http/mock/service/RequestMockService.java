package io.thoughtware.postin.api.http.mock.service;

import io.thoughtware.core.page.Pagination;

import io.thoughtware.postin.api.http.mock.model.RequestMock;
import io.thoughtware.postin.api.http.mock.model.RequestMockQuery;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
* 用户服务接口
*/
public interface RequestMockService {

    /**
    * 创建用户
    * @param requestMock
    * @return
    */
    String createRequestMock(@NotNull @Valid RequestMock requestMock);

    /**
    * 更新用户
    * @param requestMock
    */
    void updateRequestMock(@NotNull @Valid RequestMock requestMock);

    /**
    * 删除用户
    * @param id
    */
    void deleteRequestMock(@NotNull String id);

    RequestMock findOne(@NotNull String id);

    List<RequestMock> findList(List<String> idList);

    /**
    * 查找用户
    * @param id
    * @return
    */
    RequestMock findRequestMock(@NotNull String id);

    /**
    * 查找所有
    * @return
    */
    List<RequestMock> findAllRequestMock();

    /**
    * 查询列表
    * @param requestMockQuery
    * @return
    */
    List<RequestMock> findRequestMockList(RequestMockQuery requestMockQuery);

    /**
    * 按分页查询
    * @param requestMockQuery
    * @return
    */
    Pagination<RequestMock> findRequestMockPage(RequestMockQuery requestMockQuery);

}