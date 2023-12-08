package io.thoughtware.postin.api.http.mock.service;

import io.thoughtware.core.page.Pagination;

import io.thoughtware.postin.api.http.mock.model.RequestHeaderMock;
import io.thoughtware.postin.api.http.mock.model.RequestHeaderMockQuery;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
* 用户服务接口
*/
public interface RequestHeaderMockService {

    /**
    * 创建
    * @param requestHeaderMock
    * @return
    */
    String createRequestHeaderMock(@NotNull @Valid RequestHeaderMock requestHeaderMock);

    /**
    * 更新
    * @param requestHeaderMock
    */
    void updateRequestHeaderMock(@NotNull @Valid RequestHeaderMock requestHeaderMock);

    /**
    * 删除
    * @param id
    */
    void deleteRequestHeaderMock(@NotNull String id);

    /**
     * 通过httpId删除所有
     * @param mockId
     */
    void deleteAllRequestHeaderMock(@NotNull String mockId);


    /**
    * 查找
    * @param id
    * @return
    */
    RequestHeaderMock findRequestHeaderMock(@NotNull String id);

    /**
    * 查找
    * @return
    */
    List<RequestHeaderMock> findAllRequestHeaderMock();

    /**
    * 查询列表
    * @param requestHeaderMockQuery
    * @return
    */
    List<RequestHeaderMock> findRequestHeaderMockList(RequestHeaderMockQuery requestHeaderMockQuery);

    /**
    * 按分页查询
    * @param requestHeaderMockQuery
    * @return
    */
    Pagination<RequestHeaderMock> findRequestHeaderMockPage(RequestHeaderMockQuery requestHeaderMockQuery);

}